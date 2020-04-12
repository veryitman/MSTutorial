function login_req_name(username, passwd, cb) {
    var url = "http://localhost:8080/signin/name?username=" + username + "&userpwd=" + passwd;
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json'
    }).done(function (result) {
        console.log("success");
        console.log("result/data: ", result);
        console.log("result.results, result.code：", result.results, result.code);
        user_obj = result.results;
        if ((null != user_obj) && (0 != user_obj.userID.length)) {
            cb(0);
            console.log('user_obj.accountName: ', user_obj.accountName);
            alert("登录成功");
        } else {
            cb(-1);
            alert("登录失败");
        }
    }).fail(function (data) {
        console.log("login error.", data)
    }).always(function (t) {
        console.log("always./complete.", t);
    });
}

function signup_req_name(username, passwd, again_passwd, cb) {
    var url = "http://localhost:8080/signup/name";
    var data = {
        "username": username,
        "userpwd": passwd
    };
    $.ajax({
        url: url,
        type: 'POST',
        data: data
    }).done(function (result) {
        console.log("success");
        console.log("result/data: ", result);
        console.log("result.results, result.code：", result.results, result.code);
        user_obj = result.results;
        if ((null != user_obj) && (0 != user_obj.userID.length)) {
            cb(0);
            alert("注册成功");
            console.log('user_obj.accountName: ', user_obj.accountName);
        } else {
            cb(-1);
            alert("注册失败");
        }
    }).fail(function (data) {
        console.log("error", data);
    }).always(function (t) {
        console.log("always./complete.", t);
    });
}