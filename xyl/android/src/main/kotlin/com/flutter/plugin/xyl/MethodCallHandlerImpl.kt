package com.flutter.plugin.xyl

import ai.advance.common.utils.FileUtil
import ai.advance.liveness.lib.GuardianLivenessDetectionSDK
import ai.advance.liveness.lib.LivenessResult
import ai.advance.liveness.lib.Market
import ai.advance.liveness.sdk.activity.LivenessActivity
import ai.advance.liveness.sdk.activity.ResultActivity
import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.src.main.kotlin.com.flutter.plugin.xyl.FileUtils
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry


/**
 * Description:
 * @author: xuyunlong
 * Date: 2021/3/11 10:17
 * @version 2.2
 */
class MethodCallHandlerImpl(private val pickContext: PickContext) : MethodCallHandler, PluginRegistry.ActivityResultListener {
    private var channel: MethodChannel? = null
    private var mResult: MethodChannel.Result? = null

    /**
     * 打开人脸识别
     */
    private val REQUEST_CODE_LIVENESS = 1000

    /**
     * 打开人脸识别结果
     */
    private val REQUEST_CODE_RESULT_PAGE = 1001

    /**
     * 选择联系人
     */
    private val PICK_CONTACT = 1002

    /**
     * 人脸识别累计识别次数
     */
    private var count: Int = 0
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "testPlugin" -> {
                result.success("android result")
            }
            "liveness_init" -> {
                (call.arguments as Map<String, String>).let {
                    GuardianLivenessDetectionSDK.init(pickContext.activity.application, it["key"], it["secrit"], Market.India)
                    GuardianLivenessDetectionSDK.letSDKHandleCameraPermission()
                }
            }
            "goLiveness" -> {
                count = 0
                mResult = result
                val intent = Intent(pickContext.activity, LivenessActivity::class.java)
                pickContext.addActivityResultListener(this)
                pickContext.activity.startActivityForResult(intent, REQUEST_CODE_LIVENESS)
            }
            "get_contacts" -> {
                result.success(null)
            }
            "select_contact" -> {
                mResult = result
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                pickContext.activity.startActivityForResult(intent, PICK_CONTACT)
            }
            "get_install_app_list" -> {
                result.success(null)
            }
            "get_sms_list" -> {
                result.success(null)
            }
            else -> {
            }

        }
    }


    fun startListening(messenger: BinaryMessenger?) {
        if (channel != null) {
            stopListening()
        }
        channel = MethodChannel(messenger, Constant.PLUGIN_CHANNEL)
        channel?.setMethodCallHandler(this)
    }

    fun stopListening() {
        channel?.setMethodCallHandler(null)
        channel = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (data == null) {
            pickContext.removeActivityResultListener(this)
            return false
        }
        when (requestCode) {
            REQUEST_CODE_LIVENESS -> {
                val result = HashMap<String, Any?>()
                if (LivenessResult.isSuccess()) {
                    result["livenessId"] = LivenessResult.getLivenessId()
                    result["transactionId"] = LivenessResult.getTransactionId()
                    result["livenessScore"] = LivenessResult.getLivenessScore()
                    result["filePath"] = FileUtils.saveBitmapFile(LivenessResult.getLivenessBitmap(), pickContext.context)
                    result["result"] = true
                    mResult?.success(result)
                    mResult = null
                } else {
                    count++
                    if (count < 3) {
                        pickContext.activity.startActivityForResult(Intent(pickContext.activity, ResultActivity::class.java), REQUEST_CODE_RESULT_PAGE)
                    } else {
                        result["result"] = false
                        mResult?.success(result)
                        mResult = null
                    }

                }
            }
            REQUEST_CODE_RESULT_PAGE -> {
                val intent = Intent(pickContext.activity, LivenessActivity::class.java)
                pickContext.activity.startActivityForResult(intent, REQUEST_CODE_LIVENESS)
            }
            PICK_CONTACT -> {
                formatContact(data)
            }
            else -> {
            }
        }
        return true
    }

    private fun formatContact(data: Intent?) {
        data?.let {
            pickContext.context.apply {
                val uri = it.data ?: return
                var fullName: String? = null
                var phoneNumber: String? = null
                val cursor = contentResolver.query(uri, null, null, null, null) ?: return
                if (cursor.moveToFirst()) {
                    fullName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    if (hasPhone == "1") {
                        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = " + id, null, null) ?: return
                        while (phones.moveToNext()) {
                            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        }
                        phones.close()
                    }
                    cursor.close()
                    val contact = HashMap<String, Any?>()
                    contact["name"] = fullName
                    contact["phone"] = phoneNumber
                    mResult?.success(contact)
                    mResult = null
                }
                mResult?.success(null)
                mResult = null
            }
        }
    }
}