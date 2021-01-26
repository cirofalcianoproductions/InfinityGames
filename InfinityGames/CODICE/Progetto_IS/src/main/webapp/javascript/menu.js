var globale = 1;
var quanti = 3;
var i;

function Vista() {

    var el = document.getElementById("principale1");
    el.style.color = "white";
    el.style.backgroundColor = "#214b6b";
    var element= document.getElementById("sottomenu1");
    element.style.display = "block";
}

function Apri(conta, str,pag, ord) {
    globale = conta;
    var element = document.getElementById("principale" + conta);
    element.style.color = "white";
    element.style.backgroundColor = "#214b6b";
    var el = document.getElementById("sottomenu" + conta);
    el.style.display = "block";

    categoria(str, pag,ord);

    for (i = 1; i <= quanti; i++) {
        if (i != conta) {
            document.getElementById("principale" + i).style.color = "#000000";
            document.getElementById("principale" + i).style.backgroundColor = "#47648f";
            document.getElementById("sottomenu" + i).style.display = "none";

        }
    }

}

