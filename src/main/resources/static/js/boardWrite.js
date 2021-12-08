

function postArticle() {
    let data = {
        "title": $("#title").val(),
        'content': $("#contents").val()
    };

    $.ajax({
        type: "POST",
        url: "/boards",
        contentType: "application/json",
        data: JSON.stringify(data),

        success: function (response) {
            alert('작성 완료!');
            location.href = "/boardList.html";
        }
    })

}


$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    if (localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
});

