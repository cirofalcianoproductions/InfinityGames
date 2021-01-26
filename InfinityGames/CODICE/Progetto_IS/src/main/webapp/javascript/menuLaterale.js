


function openNav() {
      $("#myNav").css("width","230px");

}


function chiudi(q)
{
    if ($("#myNav").height() > 0) {
        $(function () {
            $(q).click(function (e) {

                if ($(e.target).is("resp"))
                    return;
                $("#myNav").css("width", "0px");
            });
        });
    }
}


function closeNav() {

    $("#myNav").css("width","0px");

}

