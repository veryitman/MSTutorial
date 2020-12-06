import 'dart:core';

var blogSite = 'www.veryitman.com';

void main() {
  String blogName = 'veryitman';
  print('The blog ' + blogSite + ' belongs ' + blogName);
  print(sayHello());
  print(addWithNum(1, 2));

  test(name: 'mark', gender: 15, isAudlt: false);
  test(name: 'mark', isAudlt: false); //No `gender`

  // 可选参数
  print(say('mark', 'bye'));
  print(say('mark', 'bye', 'iPhone12 Pro Max'));

  Animal animal = new Cat();
  animal.say();
  animal.eat();
  animal = new Person();
  animal.say();
  animal.eat();

  animal = new Worker();
  animal.eat();
  animal.say();

  //download();

  task();
}

String sayHello() => 'Hello Dart';

int addWithNum(a, b) => a + b;

/// 可选参数名使用 {}
void test({String name, int gender, bool isAudlt}) {
  if (null == gender) {
    print(name + ' isAudlt: ' + isAudlt.toString());
  } else {
    print(name + ' is '
        + gender.toString() + ' years, but isAudlt: '
        + isAudlt.toString());
  }
}

/// 可选参数
String say(String from, String msg, [String device]) {
  var results = '$from say $msg';
  if (null != device) {
    results = '$results with $device';
  }
  return results;
}

abstract class Animal {
  void say();

  void eat() {
    print('Animal eat');
  }
}

class Cat extends Animal {
  @override
  void say() {
    print('Cat say miaomiao');
  }
}

class Person extends Animal {
  @override
  void say() {
    print('Person say hi');
  }
}

class Worker implements Animal {
  @override
  void say() {
    print('Worker say work');
  }

  @override
  void eat() {
    print('Worker eat');
  }
}

void download() async {
  Future.delayed(new Duration(seconds: 2), (){
    //throw AssertionError("Error"); //发生错误
    return "delay data";
  }).then((value) => print(value)).catchError((e){

  }).whenComplete(() => print('completed.')); //无论如何都会走到这里，即使发生错误
}

void task() async {
  try {
    String loginInfo = await login('mark', '123');
    print('task:loginInfo' + loginInfo);
    String userInfo = await getUserInfo(loginInfo);
    print('task:userInfo' + userInfo);
    await setUserInfo(userInfo).then((value) {
      print('task:setUserInfo:' + value);
    }).whenComplete(() => print('task:completed'));
  } catch(e) {
    print(e);
  }
}

Future<String> login(String name, String pwd) {
  return Future.delayed(new Duration(seconds: 2), (){
    return (name + ',' + pwd);
  });
}

Future<String> getUserInfo(String name) {
  return Future.delayed(new Duration(seconds: 3), (){
    return('getUserInfo:' + name);
  });
}

Future<String> setUserInfo(String name) {
  return Future.delayed(new Duration(seconds: 3), (){
    return('setUserInfo:' + name);
  });
}

