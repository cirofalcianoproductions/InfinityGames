$(document).ready(function () {

    $(".info").click(function () {
        if ($(this).next().height() == 0) {
            $(this).next().css({"height": "auto", "padding": "10px", "border": "solid"});
        } else {
            $(this).next().css({"height": "0px", "padding": "0px", "border": "none"});
        }
    })
})