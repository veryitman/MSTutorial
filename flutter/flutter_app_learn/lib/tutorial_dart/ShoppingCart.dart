class Meta {
  double price;
  String name;

  Meta(this.name, this.price);
}

abstract class PrintHelper {
  String getInfo();

  printInfo() => print(getInfo());
}

/// 商品
class Item extends Meta {
  Item(name, price) : super(name, price);

  Item operator +(Item item) => Item(name + item.name, price + item.price);
}

/// 购物车
class ShoppingCart extends Meta with PrintHelper {
  DateTime dateTime;
  String code;
  List<Item> bookings;

  /*
  double get price {
    double sum = 0.0;
    for (var item in bookings) {
      sum += item.price;
    }
    return sum;
  }
  */

  double get price {
    return bookings.reduce((value, element) {
      return value + element;
    }).price;
  }

  ShoppingCart.withCode({name, this.code})
      : dateTime = DateTime.now(),
        super(name, 0);

  ShoppingCart({name}) : this.withCode(name: name, code: null);

  @override
  String getInfo() {
    return '''
        购物车信息:
        --------------------------------
        用户名: $name
        优惠码: ${code ?? 'Nothing'}
        总价: $price 
        日期: $dateTime
        --------------------------------;
  ''';
  }
}
