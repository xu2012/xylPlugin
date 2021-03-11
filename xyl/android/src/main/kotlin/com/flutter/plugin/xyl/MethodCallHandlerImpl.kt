package com.flutter.plugin.xyl

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


/**
 * Description:
 * @author: xuyunlong
 * Date: 2021/3/11 10:17
 * @version 2.2
 */
class MethodCallHandlerImpl(private val context: Context) : MethodCallHandler {
    private var channel: MethodChannel? = null
    private var activity: Activity? = null
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "testPlugin" -> {
                result.success("android result")
            }
            else -> {
            }
        }
    }

    fun setActivity(activity: Activity?) {
        this.activity = activity
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
}