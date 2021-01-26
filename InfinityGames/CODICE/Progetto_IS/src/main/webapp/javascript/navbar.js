$(window).resize(function () {


    if ($(window).width() < 921) {

        $(".accordion").unbind("mouseenter");
       $(".nav-item").mouseleave(function () {
            $(".accordion").next().css("display", "none");
        })

        $(".accordion").click(function () {

           /*    var panel = this.nextElementSibling;
               if (panel.style.display == "block") {
                 panel.style.display = "none";
               } else {
                 panel.style.display = "block";
                 panel.style.position= "static";
               }
*/
            if ($(this).next().is(":visible")) {
                $(this).next().css("display", "none");
            } else {
                $(this).next().css("display", "block");
                $(this).next().css("position", "static");
            }
        });
    } else {

      $(".accordion").next().css("position", "absolute");
        $(".accordion").mouseenter(function () {
            $(this).next().css({"display": "block", "position": "absolute"});
        })
        $(".nav-item").mouseleave(function () {
            $(".accordion").next().css("display", "none");
        })

    }
})
