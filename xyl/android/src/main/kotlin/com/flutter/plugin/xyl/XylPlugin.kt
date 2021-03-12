package com.flutter.plugin.xyl

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry

/** XylPlugin */
class XylPlugin : FlutterPlugin, ActivityAware {

    private var methodCallHandler: MethodCallHandlerImpl? = null
    private var activity: ActivityPluginBinding? = null
    private var pluginBinding: FlutterPlugin.FlutterPluginBinding? = null
    private val context: PickContext = V2Context()
/*    companion object {
        fun registerWith(registrar: PluginRegistry.Registrar) {
            val handler = MethodCallHandlerImpl(context)
            handler.startListening(registrar.messenger())
        }
    }*/

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        methodCallHandler = MethodCallHandlerImpl(context)
        methodCallHandler?.startListening(flutterPluginBinding.binaryMessenger)
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        methodCallHandler?.stopListening()
        methodCallHandler = null
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding
        binding.addActivityResultListener(context)

    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(p0: ActivityPluginBinding) {
        onAttachedToActivity(p0)
    }

    override fun onDetachedFromActivity() {
        activity?.removeActivityResultListener(context)
    }
    private inner class V2Context : AbstractPickContext() {
        override val activity: Activity
            get() = this@XylPlugin.activity?.activity ?: error("No Activity")

        override val context: Context
            get() = this@XylPlugin.pluginBinding?.applicationContext
                    ?: error("No context")

    }

}
