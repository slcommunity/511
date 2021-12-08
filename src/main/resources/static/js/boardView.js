

let idx = '';
$(document).ready(function () {
    idx = getParam();
    $('#edit')[0].href = `boardEdit.html?boardIdx=${idx}`
    getArticle(idx);
    setArticleComment(idx);
});

function getParam() {
    return location.search.split("=").pop()
}

function getArticle(idx) {
    $.ajax({
        type: "GET",
        url: `/board/${idx}`,
        success: function (response) {
            $("#title").text(response['title']);
            $("#content").text(response['content']);
            $("#boardIdx").text(response['boardIdx']);
            $("#createdAt").text(response['createdAt']);
            $("#username").text(response['username']);
        }
    })
}

function setArticleComment(idx) {
    $.ajax({
        type: "GET",
        url: `/board/comment/${idx}`,

        success: function (response) {
            $("#comment-list").empty();
            for (let i = 0; i < response.length; i++) {
                num = response.length - i;
                makeListPost(response[i], idx);
            }
        }
    })
}

function makeListPost(comment, boardIdx) {
    let content = comment.content;
    let commentIdx = comment.idx;

    //html 댓글리스트 클리어 한번

    let tempHtml = `<div class="comment-item-wrap" >

                            <div class="comment-item">${content}</div>
                           <div class="actions"> <button class="btn btn-primary" onclick="updateComment('${boardIdx}', '${content}', '${commentIdx}')">수정</button>
                            <button class = "btn btn-primary" onclick="deleteComment(idx, '${commentIdx}')">삭제</button> </div>

                        </div>`;
    $("#comment-list").append(tempHtml);

}


function saveComment() {
    let idx = getParam();
    let data = {
        "boardIdx": idx,
        "content": $("#comment").val()
    };


    $.ajax({
        type: "POST",
        url: "/board/comment",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('작성 완료!');
            setArticleComment(idx);
        }
    })

}

$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    if (localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});


function updateComment(boardIdx, content, commentIdx) {
    let fix = prompt("수정할 내용을 입력해주세요!\n\n기존 내용\n[" + content + "]")

    if (fix == null || fix === '')
        return

    let data = {"boardIdx": boardIdx, "content": fix, "commentIdx": commentIdx};
    $.ajax({
        type: "put",
        url: `/boards/comment`,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function (response) {
            alert("수정 완료~");
            setArticleComment(idx)
        }
    })
}

function deleteComment(boardId, commentId) {
    $.ajax({
        type: "delete",
        url: `/board/${boardId}/comment/${commentId}`,
        success: function (response) {
            alert("삭제 완료~");
            setArticleComment(idx)
        }
    })
}