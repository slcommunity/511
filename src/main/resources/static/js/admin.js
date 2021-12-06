let userTurnInfo = "1기"
let turnInfo = "1기";
let curPage = 1;
let searchTitle = "";

$(document).ready(function () {
    setListType('user');
    showByTurn(1);
    showUsers(1);

    // showArticles(1);
    $("#turnUrlSelect").change(function () {
        turnInfo = $(this).val();
        showUrls(turnInfo);
    });

    $("#turnUserSelect").change(function () {
        userTurnInfo = $(this).val();
        searchTitle = "";
        showUsers(1);
    });
});


let Dashboard = (() => {
    let global = {
        tooltipOptions: {
            placement: "right"
        },
        menuClass: ".c-menu"
    };

    let menuChangeActive = el => {
        let hasSubmenu = ($(el).hasClass("has-submenu"));
        $(global.menuClass + " .is-active").removeClass("is-active");
        $(el).addClass("is-active");

        // if (hasSubmenu) {
        // 	$(el).find("ul").slideDown();
        // }
    };

    let sidebarChangeWidth = () => {
        let $menuItemsTitle = $("li .menu-item__title");

        $("body").toggleClass("sidebar-is-reduced sidebar-is-expanded");
        $(".hamburger-toggle").toggleClass("is-opened");

        if ($("body").hasClass("sidebar-is-expanded")) {
            $('[data-toggle="tooltip"]').tooltip("destroy");
        } else {
            $('[data-toggle="tooltip"]').tooltip(global.tooltipOptions);
        }

    };

    return {
        init: () => {
            $(".js-hamburger").on("click", sidebarChangeWidth);

            $(".js-menu li").on("click", e => {
                menuChangeActive(e.currentTarget);
            });

            // $('[data-toggle="tooltip"]').tooltip(global.tooltipOptions);
        }
    };
})();

Dashboard.init();


function setListType(type) {
    let data = ["user", "turn", "time", "board"];
    item = `#${type}`;
    tag = `#${type}-tag`;
    for (let i = 0; i < 4; i++) {
        items = `#${data[i]}`;
        tags = `#${data[i]}-tag`;
        if (items == item) {
            $(items).removeClass("d-none");
            $(tag).addClass("is-active");
        } else {
            if (!$(items).hasClass("d-none")) {
                $(items).addClass("d-none");
                $(tags).removeClass("is-active");
            }
        }
    }
}

function showUsers(curPage) {
    $.ajax({
        type: "GET",
        url: `/admin/user?userTurnInfo=${userTurnInfo}&curPage=${curPage}&searchWord=${searchTitle}`,
        success: function (response) {
            $('#list-user').empty();
            for (let i = 0; i < response.data.length; i++) {
                makeUsers(response.data[i], i + 1);
            }
            makePages(response.count);
        }
    })
}

function searchingName(){
    searchTitle = $("#searchUser").val();
    showUsers(1);
}


function makePages(count) {
    let tempHtml = ``;
    for(let i = 0; i < count; i++){
        if(i + 1 == curPage){
            tempHtml += `<li class="page-item"><a class="page-link" href="#">${i + 1}</a></li>`;
        }
        else{
            tempHtml += `<li class="page-item"><a class="page-link" href="#" onclick="showUsers(${i + 1})">${i + 1}</a></li>`
        }
        curPage = i + 1;
    }
    $('#pagination').html(tempHtml);
}

function makeUsers(data, idx) {
    let userId = data.userName;
    let name = data.name;
    let blog = data.blog;
    let github = data.github;

    let temphtml = `<tr>
                    <td>${idx}</td>
                    <td>${name}</td>
                    <td>${userId}</td>
                    <td>${blog}</td>
                    <td>${github}</td>
                    <td>recent</td>
                    <td>comment</td>
                    <td>19개</td>
                    <td>
                        <button class="btn btn-outline-secondary" onclick="deleteUser('${userId}')">삭제</button>
                    </td>
                </tr>`;
    $('#list-user').append(temphtml);
}

function deleteUser(userId){
    $.ajax({
        type: "DELETE",
        url: `/admin/user/${userId}`,
        success: function (response){
            showUsers(1)
        }
    })
}


//기수 불러오기
function showTurn() {

    $.ajax({
        type: "GET",
        url: `/admin/turn`,
        success: function (response) {
            $("#turns").empty();
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                makeTurnList(list[i]);
            }
        }
    })
}


//기수 html 붙이기
function makeTurnList(name) {
    let tempHtml = `<div class="input-group mb-3" id="TurnTitle-${name['turn']}">
                        <input type="text" readonly class="form-control" aria-label="기수"
                           aria-describedby="basic-addon2" value="${name['turn']}">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="modifyTurn('${name['turn']}')">수정</button>
                            <button class="btn btn-outline-secondary" type="button" onclick="deleteTurn('${name['turn']}')">삭제</button>
                            <!--저장을 누르지 않고 나가도 알아서 저장.-->
                        </div>
                    </div>`;
    $("#turns").append(tempHtml);
}

//기수 수정하기
function modifyTurn(name) {
    let tempHtml = `<div class="input-group mb-3">
                        <input type="text" class="form-control" aria-label="기수" aria-describedby="basic-addon2" value="${name}" id="Turn-${name}">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="completeModifyTurn('${name}')">완료</button>
                            <!--지금나옴-->
                        </div>
                    </div>`;
    let tag = '#TurnTitle-' + name;
    $(tag).html(tempHtml);
}

//기수 수정 완료하기, 기수추가
function completeModifyTurn(fromName) {
    let turnTag = '#Turn-' + fromName;
    let toName = $(turnTag).val();
    let data = {
        "oldTurn": fromName,
        "newTurn": toName
    }
    if (turnTag.indexOf('Turn-new') > -1) {
        $.ajax({
            type: "POST",
            url: `/admin/turn/${toName}`,
            success: function (response) {
                alert("추가 성공!");
                showTurn();
            }
        })
    } else {
        $.ajax({
            type: "PUT",
            url: `/admin/turn`,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (response) {
                alert("변경 성공!");
                showTurn();
            }
        })
    }
}

//기수 삭제하기
function deleteTurn(name) {
    $.ajax({
        type: "DELETE",
        url: `/admin/turn/${name}`,
        success: function (response) {
            alert("삭제 성공!");
            showTurn();
        }
    })
}

// 기수 추가
function addTurn() {
    let tempHtml = `<div class="input-group mb-3">
                        <input type="text" class="form-control" aria-label="기수" aria-describedby="basic-addon2" id="Turn-new">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="completeModifyTurn('new')">완료</button>
                        </div>
                    </div>`;
    $("#turns").append(tempHtml);
}


//왼쪽 탭 클릭시 모달에 기수 데이터 넣기
function showByTurn(turn) {
    $.ajax({
        type: "GET",
        url: `/admin/turn`,
        success: function (response) {
            let list = response.data;
            if (turn == 1) {
                $("#turnUserSelect").empty();
                for (let i = 0; i < list.length; i++) {
                    callUsersInTurn(list[i]);
                }
            } else if (turn == 2) {
                $("#turnUrlSelect").empty();
                for (let i = 0; i < list.length; i++) {
                    callUrlsInTurn(list[i]);
                }
            }
        }
    })
}

//기수 데이터 html
function callUrlsInTurn(value) {
    let tempHtml = `<option value="${value['turn']}">${value['turn']}</option>`;
    $("#turnUrlSelect").append(tempHtml);
}

function callUsersInTurn(value) {
    let tempHtml = `<option value="${value['turn']}">${value['turn']}</option>`;
    $("#turnUserSelect").append(tempHtml);
}

//기수 선택시 url 출력
function showUrls(perPage) {
    $.ajax({
        type: "GET",
        url: `/admin/url/${perPage}`,
        success: function (response) {
            $("#PRESENTATIONSection").empty();
            $("#TIMEATTACKSection").empty();
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                makeUrl(list[i], perPage);
            }
        }
    })
}

function makeUrl(list, turn) {
    let url = list.url;
    let urlName = list.urlName;
    let urlSection = list.urlSection;
    let target = '#' + urlSection + 'Section';
    let tempHtml = `<div class="input-group mb-3" id="${urlSection}-${urlName}" style="width: 40%; margin: 0% 30%;">
                        <input type="text" readonly class="form-control" value="${urlName}">
                        <input type="text" readonly class="form-control" value="${url}">
                        <button class="btn btn-outline-secondary" type="button" onclick="modifyUrl('${url}', '${urlName}', '${urlSection}', '${turn}')">수정</button>
                        <button class="btn btn-outline-secondary" type="button" onclick="deleteUrl('${url}', '${urlName}', '${urlSection}', '${turn}')">삭제</button>
                    </div>`;
    $(target).append(tempHtml);
}

function modifyUrl(url, urlName, urlSection, turn) {
    let tempHtml = `<div class="input-group mb-3" id="url-${urlName}">
                        <input type="text" class="form-control" value="${urlName}" id="urlName-${urlName}">
                        <input type="text" class="form-control" value="${url}" id="urlN-${urlName}">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="completeModifyUrl('${url}', '${urlName}', '${urlSection}', '${turn}')">완료</button>
                        </div>
                    </div>`;
    let tag = '#' + urlSection + '-' + urlName;
    $(tag).html(tempHtml);
}

function deleteUrl(url, urlName, urlSection, turn) {
    $.ajax({
        type: "DELETE",
        url: `/admin/url?urlName=${urlName}&url=${url}&turn=${turn}&urlSection=${urlSection}`,
        success: function (response) {
            showUrls(turnInfo);
        }
    })
}

function completeModifyUrl(url, urlName, urlSection, turn) {
    let urlNameTag = '#urlName-' + urlName;
    let urlTag = '#urlN-' + urlName;
    let toUrlName = $(urlNameTag).val();
    let toUrl = $(urlTag).val();
    if (url == 'PNew' || url == 'TNew') {
        let urlNameTag2 = '#url-' + url;
        let urlTag2 = '#urlName-' + url;
        let toUrlName2 = $(urlNameTag2).val();
        let toUrl2 = $(urlTag2).val();
        if (url == 'PNew') {
            urlSection = "PRESENTATION";
        } else {
            urlSection = "TIMEATTACK"
        }
        $.ajax({
            type: "POST",
            url: `/admin/url?url=${toUrl2}&urlName=${toUrlName2}&turn=${turnInfo}&urlSection=${urlSection}`,
            success: function (response) {
                showUrls(turnInfo);
            }
        })
    } else {
        $.ajax({
            type: "PUT",
            url: `/admin/url?urlname=${urlName}&url=${url}&tourl=${toUrl}&tourlname=${toUrlName}&turn=${turn}`,
            success: function (response) {
                showUrls(turnInfo);
            }
        })
    }
}


function addPre() {
    let tempHtml = `<div class="input-group mb-3" style="width: 40%; margin: 0% 30%;">
                        <input type="text" class="form-control" id="url-PNew">
                        <input type="text" class="form-control" id="urlName-PNew">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="completeModifyUrl('PNew')">완료</button>
                        </div>
                    </div>`;
    $("#PRESENTATIONSection").append(tempHtml);
}

function addTimeAttack() {
    let tempHtml = `<div class="input-group mb-3" style="width: 40%; margin: 0% 30%;">
                        <input type="text" class="form-control" id="url-TNew">
                        <input type="text" class="form-control" id="urlName-TNew">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="completeModifyUrl('TNew')">완료</button>
                        </div>
                    </div>`;
    $("#TIMEATTACKSection").append(tempHtml);
}

$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
    if(localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});