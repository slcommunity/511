// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function() {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: `/api/user/boards`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let board = response[i]
                console.log(board)
                makeTable(board)
            }
        }
    })
});

//상세정보 및 삭제 href 추가 필요
function makeTable(board){
    let temphtml = `<tr>
                        <td>${board['idx']}</td>
                        <td class="title" onclick="location.href='boardView.html?boardIdx=${board['idx']}'">${board['title']}</td>
                        <td class="board-content">${board['content']}</td>
                        <td>${board['username']}</td>
                        <td>${board['createdAt']}</td>
                        <td><input type="button" class="btn-sm btn-outline-danger" onclick="deleteArticle(${board['idx']})" value="삭제"></td>
                    </tr>`
    $("#boardList").append(temphtml)
}

function deleteArticle(idx) {
    $.ajax({
        type: "DELETE",
        url: `/api/boards/${idx}`,
        data: {},
        success: function (response) { // 성공하면
            if(response === "success"){
                alert("삭제 되었습니다.")
                location.reload()
            }
            else{
                alert("자신의 글만 삭제가 가능합니다.")
            }
        }
    })
}

$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    if (localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});