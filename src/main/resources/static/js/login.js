$(document).ready(function () {
    loginTurn();
});

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
            alert("아이디 혹은 비밀번호를 확인해주세요.")
        },
    })
}

function register() {
    let userId = $("#userID").val()
    let password = $("#password").val()
    let name = $("#name").val()
    let blog = $("#blog").val()
    let github = $("#github").val()
    let turn = $("#turnSelect option:selected").text()

    if(!userId){
        alert("아이디는 필수 입력값 입니다.")
        $("#userID").focus()
        return;
    }
    if(!password){
        alert("비밀번호는 필수 입력값 입니다.")
        $("#password").focus()
        return;
    }
    if(!name){
        alert("이름은 필수 입력값 입니다.")
        $("#name").focus()
        return;
    }
    if(!blog){
        alert("블로그 주소는 필수 입력값 입니다.")
        $("#blog").focus()
        return;
    }
    if(!github){
        alert("깃헙 주소는 필수 입력값 입니다.")
        $("#github").focus()
        return;
    }
    if(!turn) {
        alert("기수를 선택해주세요.")
        $("#turnSelect").focus()
        return;
    }
    if(!$("#userID").hasClass("is-safe")){
        alert("ID 중복확인을 해주세요.")
        return;
    }

    let info = {
        username: userId,
        password: password,
        name: name,
        blog: blog,
        github: github,
        turn: turn
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
            alert("회원가입이 불가합니다. 관리자에게 문의하세요.")
        },
    })
}

function loginTurn() {
    $.ajax({
        type: "GET",
        url: `/admin/turn`,
        success: function (response) {
            let list = response.data;
            $("#turnSelect").empty();
            for (let i = 0; i < list.length; i++) {
                callTurns(list[i]);
            }
        }
    })
}

function callTurns(value) {
    let tempHtml = `<option value="${value['turn']}">${value['turn']}</option>`;
    $("#turnSelect").append(tempHtml);
}

function check(){
    let userId = $("#userID").val()

    if(userId == null){
        alert("ID를 입력해주세요.")
        return;
    }

    $.ajax({
        type: "GET",
        url: `/user/check/${userId}`,
        success: function (response) {
            if(response === "ok"){
                alert("사용가능한 ID 입니다.")
                $("#userID").addClass("is-safe").removeClass("is-danger")
            }else{
                alert("중복된 ID가 이미 존재합니다.")
                $("#userID").addClass("is-danger").removeClass("is-safe")
            }
        }
    })
}