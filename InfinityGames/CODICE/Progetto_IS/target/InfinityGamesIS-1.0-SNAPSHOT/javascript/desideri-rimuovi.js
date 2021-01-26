
function annulla(id) {
    $("#form-remove"+id).css("display","none");
//    document.getElementById("form-remove" + id).style.display = "none";
    $("#remove"+id).css("display","block");
 //   document.getElementById("remove" + id).style.display = "block";
    if ($(document).height <468){
        $("#des").css("height","200px");
    }
  /*  if (document.screen.width < 468) {
        var el = document.getElementById("des");
        el.style.height = "200px";
    }*/
}

function rimuovi(id) {
  /*  if (document.screen.width < 468) {
        var el = document.getElementById("des");
        el.style.height = "270px";
    }
   */
    if ($(document).height <468){
        $("#des").css("height","270px");
    }
    $("#form-remove"+id).css("display","block");
    $("#remove"+id).css("display","none");
//    document.getElementById("form-remove" + id).style.display = "block";
 //   document.getElementById("remove" + id).style.display = "none";
}
