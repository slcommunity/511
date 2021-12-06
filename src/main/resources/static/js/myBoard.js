// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function() {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: `/my-boards`,
        processData: false, // 필수
        contentType: false, // 필수
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
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
                        <td class="title" onclick="location.href='test.html'">${board['title']}</td>
                        <td>${board['content']}</td>
                        <td>${board['createdAt']}</td>
                        <td>삭제</td>
                    </tr>`
    $("#boardList").append(temphtml)
}