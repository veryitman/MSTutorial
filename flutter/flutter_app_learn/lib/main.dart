import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_app_learn/NewRoute.dart';

void main() {
  FlutterError.onError = (FlutterErrorDetails details) {
    print("Catch errors: " + details.stack.toString());
    reportErrorAndLog(details);
  };

  // runZoned(() => runApp(MyApp()), zoneSpecification: ZoneSpecification(
  //   print: (Zone self, ZoneDelegate parasent, Zone zone, String line) {
  //     collectLog(line);
  //   },
  // ), onError: (Object object, StackTrace stackTrace) {
  //   FlutterErrorDetails errorDetails = makeDetails(object, stackTrace);
  //   reportErrorAndLog(errorDetails);
  // });

  runZonedGuarded(() => runApp(MyApp()), (Object error, StackTrace stack) {
    //Crashlytics.instance.recordError(error, stack);
    FlutterErrorDetails errorDetails = makeDetails(error, stack);
    reportErrorAndLog(errorDetails);
  }, zoneSpecification: ZoneSpecification(
      print: (Zone self, ZoneDelegate parasent, Zone zone, String line) {
    collectLog(line);
  }));

  runApp(MyApp());
  // runApp(Container(
  //   color: Colors.red[200],
  //   width: 10,
  //   height: 10,
  //   child: Center(
  //     child: Container(
  //       color: Colors.blue,
  //       width: 100,
  //       height: 100,
  //       child: Align(
  //         alignment: Alignment(1, -1),
  //         child: FlutterLogo(
  //           size: 20,
  //         ),
  //       ),
  //     ),
  //   ),
  // ));
}

///收集日志
void collectLog(String line) {}

///上报错误和日志逻辑
void reportErrorAndLog(FlutterErrorDetails details) {}

/// 构建错误信息
FlutterErrorDetails makeDetails(Object obj, StackTrace stack) {
  FlutterErrorDetails details;
  details = FlutterErrorDetails(exception: obj, stack: stack);

  return details;
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Mark"),
      ),
      body: Center(

      ),
    );
  }

  // @override
  Widget build1(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Container(
          color: Colors.green[200],
          child: Stack(
            // fit: StackFit.expand,
            clipBehavior: Clip.hardEdge,
            alignment: Alignment(0, 0),
            children: [
              // Text("data1--stack1"),
              Positioned(top: 0, left: 0, child: FlutterLogo(size: 150)),
              FlutterLogo(size: 150),
              Text("data2--stack2"),
              Positioned(
                right: 0,
                top: 0,
                child: Container(
                  width: 30,
                  height: 30,
                  color: Colors.red,
                ),
              ),
              Positioned(
                  top: 0,
                  left: 10,
                  width: 120,
                  child: Transform.translate(
                    offset: Offset(-50, -20),
                    child: ElevatedButton(
                      onPressed: () => {},
                      child: Text("click me"),
                    ),
                  )),
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
