import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:xyl_platform_interface/bean/contact_bean.dart';
import 'package:xyl_platform_interface/bean/liveness_result.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';
import 'dart:js' as js;

class XylPlugin extends XylPlatform {
  static void registerWith(Registrar registrar) {
    XylPlatform.instance = XylPlugin();
    // ui.platformViewRegistry.registerViewFactory(linkViewType, linkViewFactory);
  }

  @override
  Future<String> testPlugin() {
    return Future.value("web");
  }

  @override
  Future<void> livenessInit(String key, String secrit) async {
    //调用js的device.liveness_init()方法
    js.context["device"].callMethod("liveness_init", [key, secrit]);
  }

  @override
  Future<LivenessResult> goLiveness() async {
    return js.context["device"].callMethod("goLiveness");
  }

  @override
  Future<List> getContacts(dynamic params) async {
    return js.context["device"].callMethod("getContacts");
  }

  @override
  Future<List> getInstallAppList(dynamic params) async {
    return js.context["device"].callMethod("getInstallAppList");
  }

  @override
  Future<List> getSmsList(dynamic params) async {
    return js.context["device"].callMethod("getSmsList");
  }

  @override
  Future<ContactBean> selectContact() async {
    return js.context["device"].callMethod("selectContact");
  }
}
