
    let idx = '';
    $(document).ready(function () {
        idx = location.search.split("=").pop()
        getEditArticle(idx);
    });

    function getEditArticle(idx) {
        $.ajax({
            type: "GET",
            url: `/board/${idx}`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
            },
            success: function (response) {
                $("#BoardTitle").val(response['title']);
                $("#BoardContent").val(response['content']);
            }
        })

    }


    function updateArticle() {
        let username = "user"
        let title = $("#BoardTitle").val()
        let content = $("#BoardContent").val()
        let data = {"user": username, "title": title, "content": content}

        $.ajax({
            type: "PUT",
            url: `boards/${idx}`,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
            },
            success: function (response) { // 성공하면
                alert("수정 되었습니다.")
                location.href = "/boardList.html";

            }
        })
    }



    function deleteArticle() {
        $.ajax({
            type: "DELETE",
            url: `boards/${idx}`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
            },
            data: {},
            success: function (response) { // 성공하면
                alert("삭제 되었습니다.")
                location.href = "/boardList.html";

            }
        })

    }

    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (localStorage.getItem('token')) {
            jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
    });
