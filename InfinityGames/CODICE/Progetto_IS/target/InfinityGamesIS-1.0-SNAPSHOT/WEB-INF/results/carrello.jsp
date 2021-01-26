<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 07/05/2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Infinity Games - Carrello</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/carrello.css"
          type="text/css"/>

</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<div class="contain-all" ondblclick="chiudi(this)">
    <div>
        <a href="Sconto">
            <img src="./images/sconti.jpg" width="100%" height="200px">
        </a>
    </div>
    <div class="centrale">
        <c:if test="${carrello.prodotti.size() >0}">
        <h1 style="text-align: center">Il tuo carrello: ${carrello.prodotti.size()}</h1>
        <div class="carrello-all">
            <c:forEach items="${carrello.prodotti}" var="carrello" varStatus="loop">
                <div id="${carrello.prodotto.id}" class="carrello">
                    <div class="carrello-main">
                        <div class="carrello-img">
                            <img src="./images/prodotti/${carrello.prodotto.images}">
                        </div>
                        <div class="sub-carrello">
                            <div class="sub">
                                <p>${carrello.prodotto.nome}</p>
                                <h5>Quantità: ${carrello.quantita}</h5>
                                <form action="Carrello" method="post">
                                    <select name="removeNum">
                                        <c:forEach begin="1" end="${carrello.quantita}" varStatus="loop">
                                            <option value="${loop.index}">${loop.index}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="prodId" value="${carrello.prodotto.id}">
                                    <input type="submit" value="Rimuovi"/>
                                </form>
                            </div>
                            <div class="prezzo-unit">
                                <h5>Prezzo unit.: <c:choose><c:when test="${carrello.prodotto.sconto > 0}"><c:out
                                        value="${carrello.prodotto.prezzo - ((carrello.prodotto.prezzo*carrello.prodotto.sconto)/100)}">€</c:out></c:when><c:otherwise>${carrello.prodotto.prezzo}€</c:otherwise></c:choose></h5>
                            </div>
                        </div>
                    </div>
                    <c:if test="${loop.last}">
                        <script>
                            var el = document.getElementById("${carrello.prodotto.id}");
                            el.style.borderBottomStyle = "groove";
                        </script>
                    </c:if>
                </div>
            </c:forEach>

        </div>
        <div class="riepilogo">
            <h1>Totale: ${carrello.prezzoTotEuro} &euro;</h1>
            <form action="Carrello">
                <input id="remove" type="submit" name="rimuovi-all" value="Rimuovi Tutti"/>
            </form>
            <br>
            <c:if test="${utente !=  null && carrello.prodotti.size() > 0}">

                <button id="form" name="acquista" value="Acquista" onclick="acquisto()">Acquista</button>

                <div id="conferma">
                    <form style="display: inline-block" action="Acquisto">
                        <input type="submit" name="conf-acq" value="Conferma" onclick="conferma()">
                    </form>
                    <button onclick="conferma()">Annulla</button>
                </div>
            </c:if>

            <c:if test="${utente == null}">
                <a href="./login.jsp">Se vuoi acquistare effettua il login</a>
            </c:if>
            <div>
                <br>
                <a href="/InfinityGames/" style="text-decoration: none">Continua ad acquistare</a>
            </div>
        </div>
        </c:if>
        <c:if test="${carrello.prodotti.size() < 1}">
            <div class="vuoto">
                <h2> Il Carrello è vuoto</h2>
                <p>Nessun prodotto presente nel carrello se vuoi acquistare vai al negozio</p>
                <a href="/InfinityGames/" style="text-decoration: none">Continua ad acquistare</a>
            </div>
        </c:if>
    </div>
</div>


<jsp:include page="footer.jsp"/>
<!--
<script>
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
</script>
-->
