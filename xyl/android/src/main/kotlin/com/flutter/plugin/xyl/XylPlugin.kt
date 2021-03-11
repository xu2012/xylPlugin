package com.flutter.plugin.xyl

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry

/** XylPlugin */
class XylPlugin : FlutterPlugin, ActivityAware {

    private var methodCallHandler: MethodCallHandlerImpl? = null

    companion object {
        fun registerWith(registrar: PluginRegistry.Registrar) {
            val handler = MethodCallHandlerImpl(registrar.context())
            handler.startListening(registrar.messenger())
        }
    }

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        methodCallHandler = MethodCallHandlerImpl(flutterPluginBinding.applicationContext);
        methodCallHandler?.startListening(flutterPluginBinding.binaryMessenger)
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        methodCallHandler?.stopListening()
        methodCallHandler = null

    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        methodCallHandler?.setActivity(binding.activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(p0: ActivityPluginBinding) {
        onAttachedToActivity(p0)
    }

    override fun onDetachedFromActivity() {
        methodCallHandler?.setActivity(null)
    }
}
