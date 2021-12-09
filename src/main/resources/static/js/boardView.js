

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
        url: `/api/board/${idx}`,
        success: function (response) {
            $("#title").text(response['title']);
            $("#content").text(response['content']);
            $("#boardIdx").text(response['boardIdx']);
            $("#createdAt").text(response['createdAt']);
            $("#username").text(response['user'].name);
        }
    })
}

function setArticleComment(idx) {
    $.ajax({
        type: "GET",
        url: `/api/comment/${idx}`,
        success: function (response) {
            $("#comment-list").empty();
            for (let i = 0; i < response.length; i++) {
                console.log(response[i])
                makeListPost(response[i], idx);
            }
        }
    })
}

function makeListPost(comment, boardIdx) {
    let content = comment['content'];
    let commentIdx = comment.idx;
    let name = comment['username']
    let createdAt = comment['createdAt']
    let tempHtml = `<div class="comment-item-wrap" >
                            <div style="width: 50%">${content}</div>
                            <div>${name}</div>
                            <div>${createdAt}</div>
                           <div class="actions"> <button class="btn btn-outline-primary" onclick="updateComment('${boardIdx}', '${content}', '${commentIdx}')">수정</button>
                            <button class = "btn btn-outline-danger" onclick="deleteComment(idx, '${commentIdx}')">삭제</button> </div>
                        </div>`;
    $("#comment-list").append(tempHtml);

}


function saveComment() {
    if(!localStorage.getItem("token")){
        alert("로그인 후 이용해주세요!");
        return;
    }

    let idx = getParam();
    let data = {
        "boardIdx": idx,
        "content": $("#comment").val()
    };


    $.ajax({
        type: "POST",
        url: "/api/comment",
        contentType: "application/json",
        data: JSON.stringify(data),
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
        success: function (response) {
            alert('작성 완료!');
            setArticleComment(idx);
        }
    })

}

function updateComment(boardIdx, content, commentIdx) {
    let fix = prompt("수정할 내용을 입력해주세요!\n\n기존 내용\n[" + content + "]")

    if (fix == null || fix === '')
        return

    let data = {"boardIdx": boardIdx, "content": fix, "commentIdx": commentIdx};
    $.ajax({
        type: "put",
        url: `/api/comment`,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
        success: function (response) {
            if(response === "success"){
                alert("수정 되었습니다.");
                setArticleComment(idx)
            }
            else{
                alert("자신의 댓글만 수정이 가능합니다.")
            }
        }
    })
}

function deleteComment(boardId, commentId) {
    $.ajax({
        type: "delete",
        url: `/api/comment/${commentId}`,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
        success: function (response) {
            if(response === "success"){
                alert("삭제 되었습니다.");
                setArticleComment(idx)
            }
            else{
                alert("자신의 댓글만 삭제가 가능합니다.")
            }
        }
    })
}