

$(document).ready(function () {



$(".drop").click(function () {

        if ($(this).next().height() == 0) {
            if ($(this).next().attr("class") == "dropdown-content") {
                $(this).next().css("height", "130px");
            } else {
                $(this).next().css("height", "230px");
            }

        }else {
            $(this).next().css("height","0px");
        }
    })

})

