import 'package:flutter/services.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';

import 'bean/contact_bean.dart';
import 'bean/liveness_result.dart';

const MethodChannel _channel = MethodChannel('plugins/xyl');

class MethodChannelXyl extends XylPlatform {
  @override
  Future<String> testPlugin() async {
    return _channel.invokeMethod<String>("testPlugin").then((value) => value);
  }

  @override
  Future<void> livenessInit(String key, String secrit) async {
    _channel.invokeMethod("liveness_init", {"key": key, "secrit": secrit});
  }

  @override
  Future<LivenessResult> goLiveness() async {
    return await LivenessResult.fromMap(
        await _channel.invokeMethod("goLiveness"));
  }

  @override
  Future<List> getContacts(dynamic params) async {
    return await _channel.invokeListMethod("get_contacts", params);
  }

  @override
  Future<List> getInstallAppList(dynamic params) async {
    return await _channel.invokeListMethod("get_install_app_list", params);
  }

  @override
  Future<List> getSmsList(dynamic params) async {
    return await _channel.invokeListMethod("get_sms_list", params);
  }

  @override
  Future<ContactBean> selectContact() async {
    return ContactBean.fromMap(await _channel.invokeMethod("selectContact"));
  }
}
