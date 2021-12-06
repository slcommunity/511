// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
$(window).on("load resize ", function () {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right': scrollWidth});
}).resize();

let turnInfo = "1기";

$(document).ready(function () {
    collectionTurn();
    showCollections(turnInfo);

    $("#turnSelect").change(function () {
        turnInfo = $(this).val();
        showCollections(turnInfo);
    });
});

function collectionTurn() {
    $.ajax({
        type: "GET",
        url: `/admin/turn`,
        success: function (response) {
            let list = response.data;
            $("#turnSelect").empty();
            for (let i = 0; i < list.length; i++) {
                callTurns(list[i]);

            }
        }
    })
}

function callTurns(value) {
    let tempHtml1 = `<option value="${value['turn']}">${value['turn']}</option>`;
    $("#turnSelect").append(tempHtml1);
}

function showCollections(turn) {
    $.ajax({
        type: "GET",
        url: `/admin/url/${turn}`,
        success: function (response) {
            $("#presentationCollection").empty();
            $("#timeAttackCollection").empty();
            let list = response.data;
            for (let i = 0; i < list.length; i++) {
                makeCollections(list[i], turn);
            }
        }
    })
}

function makeCollections(listElement, turn) {
    let url = listElement.url;
    let urlName = listElement.urlName;
    let tempHtml1

    if (listElement.urlSection == "TIMEATTACK") {
        tempHtml1 = `<tr>
                    <td>${urlName}</td>
                    <td><a href="https://${url}">${url}</a></td>
                </tr>`;
        $("#presentationCollection").append(tempHtml1);
    } else {
        tempHtml1 = `<tr>
                    <td>${urlName}</td>
                    <td><a href="https://${url}">${url}</a></td>
                </tr>`;
        $("#timeAttackCollection").append(tempHtml1);
    }
}
