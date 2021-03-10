import 'dart:async';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:xyl_platform_interface/method_channel_xyl.dart';

abstract class XylPlatform extends PlatformInterface {
  XylPlatform() : super(token: _token);
  static final Object _token = Object();
  static XylPlatform _instance = MethodChannelXyl();

  static XylPlatform get instance => _instance;

  static set instance(XylPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String> testPlugin() {
    throw UnimplementedError("testPlugin has not been implemented.");
  }
}
