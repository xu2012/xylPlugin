
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';
class XylPlugin extends XylPlatform{
 /* static const MethodChannel _channel =
      const MethodChannel('xyl_web');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }*/
  static void registerWith(Registrar registrar) {
    XylPlatform.instance = XylPlugin();
    // ui.platformViewRegistry.registerViewFactory(linkViewType, linkViewFactory);
  }

  @override
  Future<String> testPlugin() {
    return Future.value("web");
  }
}