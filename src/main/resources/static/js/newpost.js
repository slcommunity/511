$(document).ready(function () {
    first();
});


    function first () {


        $.ajax({
        type : "GET",
        url : "/first",
        data : {},
        success : function(data) {
        var len = data.length;
            for (var i = 0; i<len; i++){
            $('#titleTable').append("<tr> <td>" + data[i].TITLE + "</tr> </td>");
            };
            console.log(data);
        }, error : function() {
            alert("통신에 실패했습니다.");
        }
        });
        };

function search() {
    let title = $("#search").val()
    $.ajax({
        type: "GET",
        url: `/searchpost?search=${title}`,
        data: {},
        success: function (response) {
            $("#postlist").empty()
            for (let i = 0; i < response.length; i++) {
                let searchtitle = response[i]
            }
        }
    })
}

