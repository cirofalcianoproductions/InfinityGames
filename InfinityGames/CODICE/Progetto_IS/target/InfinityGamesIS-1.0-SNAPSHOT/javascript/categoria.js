function categoria(str,pag,ord) {
   var num=globale;
    var dataList = document.getElementById("sottomenu" + num);
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.responseType = 'json';
    xmlHttpReq.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            //rimuovi elementi <option>
            dataList.innerHTML = "";

            for (var i in this.response) {
                var divContieni = document.createElement('div');
                var divImgCol = document.createElement('div');
                var aSubImg = document.createElement('a');
                divContieni.classList.add("contieni");
                divImgCol.classList.add("img-col");
                aSubImg.classList.add("sub-img");
                aSubImg.href = "Prodotto?id=" + this.response[i].Prod;
                var img = document.createElement('img');
                img.src = "images/prodotti/" + this.response[i].imagesProd;
                var col = document.createElement('div');
                col.classList.add("col-2");
                var right_col = document.createElement('div');
                right_col.classList.add("right-col");
                var name_over = document.createElement('div');
                name_over.classList.add("name-over");
                var h5 = document.createElement('h5');
                h5.innerHTML = this.response[i].nome;
                var prezzo_down = document.createElement('div');
                prezzo_down.classList.add("prezzo-down");
                var sub_prezzo = document.createElement('div');
                sub_prezzo.classList.add("sub-prezzo");
                if (this.response[i].sconto > 0) {
                    var span = document.createElement("span");
                    span.style.color= "green";
                    span.innerHTML ="-"+ this.response[i].sconto +"%";
                    sub_prezzo.appendChild(span);

                    var c_del = document.createElement("del");
                    c_del.innerHTML =this.response[i].prezzo+"€ ";
                    var c_h5 = document.createElement("h5");
                    c_h5.innerHTML = "Prezzo: " + (this.response[i].prezzo - (this.response[i].prezzo * this.response[i].sconto / 100))+"€ ";
                    c_h5.appendChild(c_del);
                    sub_prezzo.appendChild(c_h5);
                } else {
                    var new_h5 = document.createElement("h5");
                    new_h5.innerHTML = "Prezzo: "+this.response[i].prezzo+"€ ";
                    sub_prezzo.appendChild(new_h5);
                }
                var col2right= document.createElement("div");
                col2right.classList.add("col-2-right");
                var div= document.createElement("div");
                div.style.float="right";
                var p_cat= document.createElement("p");
                p_cat.innerHTML="Categorie: ";
                for (var j in this.response[i].categoria){

                    var a= document.createElement("a");
                    a.classList.add("categoria");
                    a.href="CategoriaServlet?id=" + this.response[i].categoria[j].idCat;
                    a.innerHTML=""+this.response[i].categoria[j].nome+" ";
                    p_cat.appendChild(a);
                }
                div.appendChild(p_cat);
                col2right.appendChild(div);
                divContieni.appendChild(col2right);
                aSubImg.appendChild(img);
                divImgCol.appendChild(aSubImg);
                name_over.appendChild(h5);
                prezzo_down.appendChild(sub_prezzo);
                right_col.appendChild(name_over);
                right_col.appendChild(prezzo_down);
                col.appendChild(right_col);
                divContieni.appendChild(divImgCol);
                divContieni.appendChild(col);
                dataList.appendChild(divContieni);

            }
        }
    }

    xmlHttpReq.open("GET", "CategoriaAjax?ricerca=" + encodeURIComponent(num) + "&id=" + encodeURIComponent(str)+"&pag="+encodeURIComponent(pag)+
        "&ord=" + encodeURIComponent(ord), true);
    xmlHttpReq.send();
}


/*
var quanti=3;
var i;

    myfunction= function (conta,str) {
        $("#sottomenu" + conta).css("display", "block");
        $("#principale" + conta).css({"color": "white", "background-color": "#214b6b"})
        for (i=1;i<=quanti;i++) {
            if(i!=conta){
                $("#principale"+i).css("color","#000000");
                $("#principale"+i).css("backgroundColor","#7c9ccc");
                $("#sottomenu"+i).css("display","none");
            }
        }
        $("#principale"+conta).click(function(){
            $.ajax({type:"GET", url: "CategoriaAjax", dataType:"JSON", data:"ricerca="+conta+"&"+"id="+str,
                success: function(result){

                }});
        });
    }

*/