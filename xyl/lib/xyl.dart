import 'dart:async';

import 'package:xyl_platform_interface/bean/contact_bean.dart';
import 'package:xyl_platform_interface/bean/liveness_result.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';

class Xyl {
  /// 测试插件
  static Future<String> testPlugin() async {
    return XylPlatform.instance.testPlugin().then((value) => value);
  }

  static Future<void> livenessInit(String key, String secrit) {
    XylPlatform.instance.livenessInit(key, secrit);
  }

  static Future<LivenessResult> goLiveness() {
    return XylPlatform.instance.goLiveness().then((value) => value);
  }

  static Future<List> getContacts(dynamic params) {
    return XylPlatform.instance.getContacts(params).then((value) => value);
  }

  static Future<ContactBean> selectContact() {
    return XylPlatform.instance.selectContact().then((value) => value);
  }

  static Future<List> getSmsList(dynamic params) {
    return XylPlatform.instance.getSmsList(params).then((value) => value);
  }


  static Future<List> getInstallAppList(dynamic params) {
    return XylPlatform.instance
        .getInstallAppList(params)
        .then((value) => value);
  }
}
