import 'package:get/get.dart';
import 'package:op_admin_tool/ui/page/home_page.dart';
import 'package:op_admin_tool/ui/page/setting_page.dart';

abstract class AppRoute {
  static const HOME = '/home';
  static const SETTING = '/setting';

  static final routes = [
    GetPage(name: HOME, page: () => HomePage()),
    GetPage(name: SETTING, page: () => SettingPage()),
  ];
}
