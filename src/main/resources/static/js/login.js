function login() {
    let info = {
        username: $("#user").val(),
        password: $("#pass").val()
    }

    $.ajax({
        type: 'POST',
        url: `/sign-in`,
        contentType: "application/json",
        data: JSON.stringify(info),
        success: function (response) {
            localStorage.setItem("token", response['token']);
            localStorage.setItem("userId", response['username']);
            if(response['role'] === "user"){
                location.href = '/index.html';
            }
            else{
                location.href = '/admin.html'
            }
        },
        error: function (response) {
            alert("로그인이 불가합니다." + response.responseJSON.error)
        },
    })
}

function register(){
    let info = {
        username: $("#userID").val(),
        password: $("#password").val(),
        name : $("#name").val(),
        blog : $("#blog").val(),
        github : $("#github").val(),
        turn : $("#turn option:selected").text()
    }
    $.ajax({
        type: 'POST',
        url: `/signup`,
        contentType: "application/json",
        data: JSON.stringify(info),
        success: function (response) {
            location.href = '/login.html';
        },
        error: function (response) {
            alert("회원가입이 불가합니다." + response.responseJSON.error)
        },
    })
}