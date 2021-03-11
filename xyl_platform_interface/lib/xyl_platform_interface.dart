import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:xyl_platform_interface/bean/contact_bean.dart';
import 'package:xyl_platform_interface/bean/liveness_result.dart';
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

  ///选择联系人
  Future<ContactBean> selectContact() {
    throw UnimplementedError("selectContact has not been implemented.");
  }

  ///抓取通讯录
  ///[orderId]订单号
  Future<List> getContacts(dynamic params) {
    throw UnimplementedError("getContacts has not been implemented.");
  }

  ///人脸sdk初始化
  Future<void> livenessInit(String key, String secrit) {
    throw UnimplementedError("livenessInit has not been implemented.");
  }

  ///打开人脸识别
  Future<LivenessResult> goLiveness() {
    throw UnimplementedError("goLiveness has not been implemented.");
  }

  Future<List> getInstallAppList( dynamic params){
    throw UnimplementedError("getInstallAppList has not been implemented.");
  }

  Future<List> getSmsList(dynamic params){
    throw UnimplementedError("getSmsList has not been implemented.");
  }
}
