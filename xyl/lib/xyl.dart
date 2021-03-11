import 'dart:async';

import 'package:xyl_platform_interface/xyl_platform_interface.dart';

class Xyl {
/*  static const MethodChannel _channel =
  const MethodChannel('xyl');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }*/

  static Future<String> testPlugin() async {
    return await XylPlatform.instance.testPlugin();
  }
}
/*
Future<String> testPlugin() async {
  return await XylPlatform.instance.testPlugin();
}*/
