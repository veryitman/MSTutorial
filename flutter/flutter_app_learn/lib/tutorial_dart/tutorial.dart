import 'package:flutter_app_learn/tutorial_dart/ShoppingCart.dart';

void main() {
  {
    ShoppingCart.withCode(name: 'mark', code: '1001002')
      ..bookings = [Item('Apple', 1.0), Item('Pear', 2.0), Item('Banana', 3.0)]
      ..printInfo();
  }

  {
    ShoppingCart.withCode(name: 'zhangsan')
      ..bookings = [Item('Apple', 3.0), Item('Pear', 2.0), Item('Banana', 3.0)]
      ..printInfo();
  }
}