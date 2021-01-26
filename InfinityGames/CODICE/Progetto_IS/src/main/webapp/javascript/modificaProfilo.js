var  passwordOk = false;
var passwordPrecedente= false;
var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';


function validaPasswordPrecedente(){
    var inputPassPrec = document.forms['modifica']['passwordPrecedente'];
    var passPrec = inputPassPrec.value;
    if (passPrec.length >= 8 && passPrec.toUpperCase() != passPrec
        && passPrec.toLowerCase() != passPrec && /[0-9]/.test(passPrec)) {

        var xmlHttpReq = new XMLHttpRequest();

        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>') {
                passwordPrecedente = true;
                inputPassPrec.style.border = borderOk;
            } else {
                inputPassPrec.style.border = borderNo;
                passwordPrecedente = false;
                document.getElementById('modificamessaggio').innerHTML = 'password sbagliata';
            }
            cambiaStatoModifica();

        }
        xmlHttpReq.open("GET", "VerificaPassword?passwordPrecedente=" + encodeURIComponent(passPrec), true);
        xmlHttpReq.send();
    }else{

        inputPassPrec.style.border = borderNo;
        passwordPrecedente=false;
    }
        cambiaStatoModifica();

}


function validaPasswordProfilo() {
    var inputpw = document.forms['modifica']['password'];
    var inputpwconf = document.forms['modifica']['passwordConferma'];
    var password = inputpw.value;
    if (password.length >= 8 && password.toUpperCase() != password
            && password.toLowerCase() != password && /[0-9]/.test(password)) {

            inputpw.style.border = borderOk;

        if (password == inputpwconf.value) {
            inputpwconf.style.border = borderOk;
            passwordOk = true;
        } else {
            inputpwconf.style.border = borderNo;
            passwordOk = false;
            document.getElementById('modificamessaggio').innerHTML = 'password non confermata';
        }
    } else {
        inputpw.style.border = borderNo;
        inputpwconf.style.border = borderNo;
        passwordOk = false;
        document.getElementById('modificamessaggio').innerHTML = 'Password non valida';
    }
    cambiaStatoModifica();
}




function cambiaStatoModifica() {
    if (passwordOk && passwordPrecedente) {
        document.getElementById('salva').disabled = false;
        document.getElementById('modificamessaggio').innerHTML = '';
    } else {
        document.getElementById('salva').disabled = true;
        document.getElementById('modificamessaggio').innerHTML = 'Verifica di aver immesso le password in modo corretto.';
    }
}

