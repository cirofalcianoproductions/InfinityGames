var categoria= false;
var nome= false;
var descrizione= false;
var prezzo= false;
var sconto= false;
var img= false;
var video = false;

function validaCat() {

    var input = document.forms['admin-form']['categorie'];
    if (input != null) {
        categoria = true;
    }
    valida();
}
function validaNome() {

    var input = document.forms['admin-form']['nome'];
    if (input != null){
             nome = true;
    }
    valida();
}

function validaDesc() {


    var input = document.forms['admin-form']['descrizione'];
    if (input!= null) {
        descrizione = true;
    }
    valida();
}
function validaSconto() {

    var input = document.forms['admin-form']['sconto'];
    if (input != null) {
        sconto = true;
    }
    valida();
}
function validaImg() {

    var input = document.forms['admin-form']['file'];
    if (input != null) {
        img = true;
    }
    valida();
}
function validaPrezzo() {

    var input = document.forms['admin-form']['prezzoCent'];
    if (input!= null) {
        prezzo = true;
    }
    valida();
}
function validaVideo() {

    var input = document.forms['admin-form']['video'];
    if (input != null) {
        video = true;
    }
    valida();
}
function valida() {

    if (categoria && nome && descrizione && prezzo && sconto && img && video) {

        document.getElementById("modifica").disabled = false;
        document.getElementById("notifica").innerHTML="";

    } else {
        document.getElementById("modifica").disabled = true;
        document.getElementById("notifica").innerHTML = "inserisici tutti i campi";
    }
}


