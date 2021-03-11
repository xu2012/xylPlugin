import 'dart:async';

import 'package:xyl_platform_interface/xyl_platform_interface.dart';

class Xyl {
  /// 测试插件
  static Future<String> testPlugin() async {
    return XylPlatform.instance.testPlugin().then((value) => value);
  }

}
