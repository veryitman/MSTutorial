

function login_req_name(username, passwd) {
	if (username.length <= 0) {
	    console.log('The username\'s length must not be zero');
	    return;
    }

	if (passwd.length <= 0) {
        console.log('The password\'s length must not be zero');
	    return;
    }

    var url = "http://localhost:8080/signin/name?username=" + username + "&userpwd=" + passwd;
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'application/json'
    }).done(function (result) {
        console.log("success");
        console.log(result);
    }).fail(function () {
        console.log("login error.")
    })
}

function signup_req_name(username, passwd, again_passwd) {
    if (username.length <= 0) {
        console.log('The username\'s length must not be zero');
        return;
    }

    if (passwd.length <= 0) {
        console.log('The password\'s length must not be zero');
        return;
    }

    if (passwd != again_passwd) {
        console.log('The password is not same');
        return;
    }

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
        console.log(result);
    }).fail(function () {
        console.log("error");
    })
}