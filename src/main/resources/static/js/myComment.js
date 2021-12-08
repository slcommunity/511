$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: `/my-comments`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let comment = response[i]
                console.log(comment)
                makeTable(comment)
            }
        }
    })
});

//boardIdx href 구현 필요
//idx 삭제 href API
function makeTable(comment){
    let temphtml = `<tr>
                        <td>${comment['idx']}</td>
                        <td class="board-title" onclick="location.href='boardView.html?boardIdx=${comment['boardIdx']}'">${comment['boardTitle']}</td>
                        <td class="comment">${comment['content']}</td>
                        <td>${comment['username']}</td>
                        <td>${comment['createdAt']}</td>
                        <td><input type="button" class="btn-sm btn-outline-danger" onclick="deleteComment(${comment['boardIdx']}, ${comment['idx']})" value="삭제"></td>
                    </tr>`
    $("#boardList").append(temphtml)
}

function deleteComment(boardId, commentId) {
    $.ajax({
        type: "delete",
        url: `/board/${boardId}/comment/${commentId}`,
        success: function (response) {
            alert("삭제 완료!");
            location.reload();
        }
    })
}

$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    if (localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});