
var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var usernameOk = false;
var passwordOk = false;
var emailOk = false;

function validaUsername() {
    var input = document.forms['registrazione']['username'];
    if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
        // verifica se esiste un utente con lo stesso username
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>') {
                usernameOk = true;
                input.style.border = borderOk;
            } else {
                input.style.border = borderNo;
                usernameOk = false;
            }
            cambiaStatoRegistrami();
        }
        xmlHttpReq.open("GET", "VerificaUsername?username="
            + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        input.style.border = borderNo;
        usernameOk = false;

        cambiaStatoRegistrami();
    }
}

function validaPassword() {
    var inputpw = document.forms['registrazione']['password'];
    var inputpwconf = document.forms['registrazione']['passwordConferma'];
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

        }
    } else {
        inputpw.style.border = borderNo;
        inputpwconf.style.border = borderNo;
        passwordOk = false;

    }
    cambiaStatoRegistrami();
}


function validaEmail() {
    var input = document.forms['registrazione']['email'];
    if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>') {
                input.style.border = borderOk;
                emailOk = true;
            } else {
                input.style.border = borderNo;
                emailOk = false;

            }
            cambiaStatoRegistrami();
        }
        xmlHttpReq.open("GET", "VerificaEmail?email="
            + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        input.style.border = borderNo;
        emailOk = false;

    }
    cambiaStatoRegistrami();

}

function cambiaStatoRegistrami() {
    if (usernameOk && passwordOk && emailOk) {
        document.getElementById('registrami').disabled = false;
        document.getElementById('registramimessaggio').innerHTML = '';
    } else {
        document.getElementById('registrami').disabled = true;
        document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
    }
}