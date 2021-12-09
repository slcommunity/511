let searchTitle = "";

$(document).ready(function () {
    getArticle(1)
});

function getArticle(curpage) {
    $.ajax({
        type: "GET",
        url: `/api/boards/${curpage}`,
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            let list = response.data;
            let fullCount = response.count;
            $("#board-list").empty();
            for (let i = 0; i < list.length; i++) {
                num = i + 1;
                makeListPost(list[i], num);
            }
            makePagination(fullCount, curpage);
        }
    })
}

function makePagination(count, curpage) {
    let tempHtml = ``;
    for(let i = 0; i < count; i++){
        if(i + 1 == curpage) {
            tempHtml += `<a href="#" class="num on">${i + 1}</a>`;
        }else{
            tempHtml += `<a href="#" class="num" onclick="getArticle(${i + 1})">${i + 1}</a>`;
        }
    }
    $('#board_pages').html(tempHtml);
}

function searchBoard(){
    let title = $("#searchBoard").val()
    $.ajax({
        type: "GET",
        url: `/api/boards/title/${title}`,
        success: function (response){
            let list = response.data;
            let fullCount = response.count;
            if(list.length == 0) {
                alert("검색값이 없어요!");
            }
            else {
                $("#board-list").empty();
                for (let i = 0; i < list.length; i++) {
                    num = i + 1;
                    makeListPost(list[i], num);
                }
                makePagination(fullCount, 1);
            }
        }
    })
}

function makeListPost(board, num) {
    let title = board.title;
    let content = board.content;
    let modi = board.modifiedAt;
    let mode = modi.substr(0,10);
    let idx = board.boardIdx;
    let name = board.user.username;

    let tempHtml = `<div onclick="window.location.href='boardView.html?boardIdx=${idx}'">
                            <div class="num">${num}</div>
                            <div class="title">${title}</a></div>
                            <div class="writer">${name}</div>
                            <div class="date">${mode}</div>
                        </div>`;
    $("#board-list").append(tempHtml);

}

function postButton(){
    if(!localStorage.getItem("token")){
        alert("로그인 후 이용해주세요!")
    }else{
        location.href="boardWrite.html"
    }
}