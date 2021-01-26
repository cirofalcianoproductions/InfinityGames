var add=false;

function aggiungi(id) {

    var xmlHttpReq= new XMLHttpRequest();

    xmlHttpReq.onreadystatechange= function () {

        if(this.readyState == 4 && this.status == 200
           && this.responseText == '<ok/>'){
           add=true;
        }else{
            add=false
        }

        mostra();
    }

    xmlHttpReq.open("GET","AddDesideri?prodId=" +encodeURIComponent(id),true);
    xmlHttpReq.send();

}



function mostra() {

    var element=document.getElementById("addDes");
    element.style.display="block";
    if (add){
        document.getElementById("addDes").style.display="block";
        document.getElementById("mes").innerHTML="Aggiunto alla lista dei desideri";
    }else {
        document.getElementById("addDes").style.display="block";
        document.getElementById("mes").innerHTML="Gia presente nella lista dei desideri";
    }

}