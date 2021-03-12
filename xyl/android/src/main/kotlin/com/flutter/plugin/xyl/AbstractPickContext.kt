package com.flutter.plugin.xyl

import android.content.Intent
import io.flutter.plugin.common.PluginRegistry

/**
 * Description:
 * @author: xuyunlong
 * Date: 2021/3/11 16:15
 * @version 2.2
 */
abstract class AbstractPickContext : PickContext {

    private val listeners = mutableListOf<PluginRegistry.ActivityResultListener>()

    override fun removeActivityResultListener(listener: PluginRegistry.ActivityResultListener) = listeners.remove(listener).run { Unit }

    override fun addActivityResultListener(listener: PluginRegistry.ActivityResultListener) = listeners.add(listener).run { Unit }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return listeners.any { it.onActivityResult(requestCode, resultCode, data) }
    }
}