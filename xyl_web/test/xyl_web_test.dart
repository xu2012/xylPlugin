import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:xyl_web/xyl_web.dart';

void main() {
  const MethodChannel channel = MethodChannel('xyl_web');

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
    expect(await XylPlugin().testPlugin(), '42');
  });
}
