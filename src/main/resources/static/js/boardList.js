

$(document).ready(function () {
    getArticle()
});
function getArticle() {
    $.ajax({
        type: "GET",
        url: `/board`,
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                num = response.length - i;
                makeListPost(response[i],num);
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