
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
                console.log(response)
                $("#BoardTitle").val(response['title']);
                $("#BoardContent").val(response['content']);
            }
        })

    }


    function updateArticle() {
        let title = $("#BoardTitle").val()
        let content = $("#BoardContent").val()
        let data = {"title": title, "content": content}

        $.ajax({
            type: "PUT",
            url: `boards/${idx}`,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
            },
            success: function (response) { // 성공하면
                if(response === "success"){
                    alert("수정 되었습니다.")
                    location.href = "/boardList.html";
                }
                else{
                    alert("자신의 글만 수정이 가능합니다.")
                }
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
                if(response === "success"){
                    alert("삭제 되었습니다.")
                    location.href = "/boardList.html";
                }
                else{
                    alert("자신의 글만 삭제가 가능합니다.")
                }
            }
        })
    }
