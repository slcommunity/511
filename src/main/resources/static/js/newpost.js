$(document).ready(function () {
    pageload();
});

function pageload() {

    $.ajax({
        type: "GET",
        url: "/api/posts",
        data: {},
        success: function (data) {
            $("#postlist").empty();
            for (var i = 0; i < data.length; i++) {
                let imageUrl = data[i].imageUrl
                let temphtml;
                if(imageUrl === "") {
                    temphtml = `<div class="post">
                                        <div class="post-image post-image-1">
                                            <img src="image/default-image.jpg" alt="">
                                        </div>
                                    <div class="post-content">
                                        <a href="${data[i].postLink}"><h3 class="post-title">${data[i].title}</h3></a>
                                        <div><p>${data[i].user.username}</p></div>
                                            <div class="post-excerpt">
                                                <p>${data[i].summary}</p>
                                            </div>
                                    </div>
                                        </div>`
                }
                else{
                    temphtml = `<div class="post">
                                        <div class="post-image post-image-1">
                                            <img src="${imageUrl}" alt="">
                                        </div>
                                    <div class="post-content">
                                        <a href="${data[i].postLink}"><h3 class="post-title">${data[i].title}</h3></a>
                                            <div><p>${data[i].user.username}</p></div>
                                            <div class="post-excerpt">
                                                <p>${data[i].summary}</p>
                                            </div>
                                    </div>
                                        </div>`
                }
                $("#postlist").append(temphtml);
            }

        }, error: function () {
            alert("통신에 실패했습니다.");
        }
    });
}

function search() {

    var searchtext = $('#search').val();
    var seletOption= $('select[name=select]').val();
    if (searchtext == ''){
        alert("검색어를 입력하세요.");
        return;
    }

    $.ajax({
        type: "GET",
        url: `/api/posts/${searchtext}`,
        data : { searchtext : searchtext,
            selecter : seletOption},
        success: function (data) {
            if (data.length == 0) {
                return;
            }
            $('#postlist').empty();
            for (var i = 0; i < data.length; i++) {
                let imageUrl = data[i].imageUrl
                let temphtml;
                if(imageUrl === "") {
                    temphtml = `<div class="post">
                                        <div class="post-image post-image-1">
                                            <img src="image/default-image.jpg" alt="">
                                        </div>
                                    <div class="post-content">
                                        <a href="${data[i].postLink}"><h3 class="post-title">${data[i].title}</h3></a>
                                        <div><p>${data[i].user.username}</p></div>
                                            <div class="post-excerpt">
                                                <p>${data[i].summary}</p>
                                            </div>
                                    </div>
                                        </div>`
                }
                else{
                    temphtml = `<div class="post">
                                        <div class="post-image post-image-1">
                                            <img src="${imageUrl}" alt="">
                                        </div>
                                    <div class="post-content">
                                        <a href="${data[i].postLink}"><h3 class="post-title">${data[i].title}</h3></a>
                                            <div><p>${data[i].user.username}</p></div>
                                            <div class="post-excerpt">
                                                <p>${data[i].summary}</p>
                                            </div>
                                    </div>
                                        </div>`
                }
                $("#postlist").append(temphtml);
            }
        }, error: function () {
            alert("통신에 실패했습니다.");
        }
    });
}