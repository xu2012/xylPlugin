import 'package:flutter/services.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';

const MethodChannel _channel = MethodChannel('plugins/xyl');

class MethodChannelXyl extends XylPlatform {
  @override
  Future<String> testPlugin() {
    return _channel.invokeMethod<String>("testPlugin").then((value) => value);
  }
}
