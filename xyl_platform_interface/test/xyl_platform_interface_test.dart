import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:xyl_platform_interface/xyl_platform_interface.dart';

void main() {
  const MethodChannel channel = MethodChannel('xyl_platform_interface');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    // expect(await XylPlatformInterface.platformVersion, '42');
  });
}
