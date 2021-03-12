package com.flutter.plugin.xyl

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.PluginRegistry

/**
 * Description:
 * @author: xuyunlong
 * Date: 2021/3/11 16:14
 * @version 2.2
 */
interface PickContext : PluginRegistry.ActivityResultListener {

    val activity: Activity
    val context: Context

    fun removeActivityResultListener(listener: PluginRegistry.ActivityResultListener)

    fun addActivityResultListener(listener: PluginRegistry.ActivityResultListener)

}