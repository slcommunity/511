let order = "asc";
let perPage = "1기";//수정 필요
let listType = "all";

$(document).ready(function () {
    setListType('user');
    // showArticles(1);
    $("#turnUrlSelect").change(function () {
        perPage = $(this).val();
        showUrls(perPage)
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


// function showArticles(number) {
//     let searchTitme = $("searchTitle").val();
//     $.ajax({
//         type: "GET",
//         url: `/admin/articles/${listType}?perPage=${perPage}&curPage=${curPage}&order=${order}&searchTitle=${searchTitle}`,
//         success: function (response){
//
//         }
//     })
// }

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

//기수 수정 완료하기
function completeModifyTurn(fromName) {
    Turntag = '#Turn-' + fromName;
    let toName = $(Turntag).val();
    let data = {
        "oldTurn": fromName,
        "newTurn": toName
    }
    if (Turntag.indexOf('Turn-new') > -1) {
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
function showUrlsByTurn() {
    $.ajax({
        type: "GET",
        url: `/admin/turn/`,
        success: function (response) {
            $("#turnUrlSelect").empty();
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                callUrlsInTurn(list[i]);
            }
        }
    })
}

//기수 데이터 html
function callUrlsInTurn(value) {
    let tempHtml = `<option value="${value['turn']}">${value['turn']}</option>`;
    $("#turnUrlSelect").append(tempHtml);
}

function makeUrl(list, turn) {
    let url = list.url;
    let urlName = list.urlName;
    let urlSection = list.urlSection;
    let target = '#' + urlSection + 'Section';
    let tempHtml = `<div class="input-group mb-3" id="${urlSection}-${urlName}" style="width: 40%; margin: 0% 30%;">
                        <input type="text" readonly class="form-control" value="${urlName}">
                        <input type="text" readonly class="form-control" value="${url}">
                        <button class="btn btn-outline-secondary" type="button" onclick="modifyUrl('${list}', '${turn}')">수정</button>
                        <button class="btn btn-outline-secondary" type="button" onclick="deleteUrl('${list}', '${turn}')">삭제</button>
                    </div>`;
    $(target).append(tempHtml);
}

function modifyUrl(list, turn){
    let url = list.url;
    let urlName = list.urlName;
    let urlSection = list.urlSection;
    let tempHtml = `<div class="input-group mb-3" style="width: 40%; margin: 0% 30%;">
                        <input type="text" class="form-control" value="">
`
}

function deleteUrl(list, turn){

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