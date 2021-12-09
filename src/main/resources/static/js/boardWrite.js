function postArticle() {
    let data = {
        "title": $("#title").val(),
        'content': $("#contents").val()
    };
    $.ajax({
        type: "POST",
        url: "/api/board",
        contentType: "application/json",
        data: JSON.stringify(data),
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        },
        success: function (response) {
            alert('작성 완료!');
            location.href = "/boardList.html";
        }
    })

}

