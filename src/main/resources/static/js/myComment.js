$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: `/my-comments`,
        processData: false, // 필수
        contentType: false, // 필수
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
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
                        <td class="board-title" onclick="location.href='test.html'">${comment['boardTitle']}</td>
                        <td class="comment">${comment['content']}</td>
                        <td>${comment['username']}</td>
                        <td>${comment['createdAt']}</td>
                        <td>삭제</td>
                    </tr>`
    $("#boardList").append(temphtml)
}