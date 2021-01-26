function acquisto() {
    var rimuovi = document.getElementById("remove");
    rimuovi.style.display = "none";
    var el = document.getElementById("form");
    el.style.display = "none";
    var el2 = document.getElementById("conferma");
    el2.style.display = "block";
}

function conferma() {
    var el = document.getElementById("form");
    el.style.display = "block";
    var el2 = document.getElementById("conferma");
    el2.style.display = "none";
    var rimuovi = document.getElementById("remove");
    rimuovi.style.display = "block";
}