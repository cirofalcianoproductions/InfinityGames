<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 08/06/2020
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Lista desideri</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet" href="./css/desideri.css" type="text/css">
    <link rel="stylesheet" href="./css/categoria.css" type="text/css">
</head>
<body>-->

<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Desideri"/>
</jsp:include>

<c:choose>
    <c:when test="${utente != null}">
        <div class="contain-desideri" ondblclick="chiudi(this)">
           <div class="contain_all">
        <c:choose>
            <c:when test="${sessionScope.desideri.size() >0}">

                <p>LISTA DEI PREFERITI DI: ${utente.username}</p>

                <div id="menu">
                    <c:forEach items="${sessionScope.desideri}" var="prodotto">
                        <div id="des" class="contieni-des">
                            <div class="img-col">
                                <a class="sub-img" href="Prodotto?id=${prodotto.id}"><img
                                        src="images/prodotti/${prodotto.images}"></a>
                            </div>
                            <div class="col-2">
                                <div class="right-col">
                                    <div class="name-over">
                                        <h5>
                                                ${prodotto.nome}
                                        </h5>
                                    </div>
                                    <div class="prezzo-down">
                                        <div class="sub-prezzo">
                                            <c:choose>
                                                <c:when test="${prodotto.sconto > 0}">
                                                    <span style="color: green">-${prodotto.sconto}%</span>
                                                    <h5>Prezzo:
                                                        <del>${prodotto.prezzo}€</del>
                                                            ${String.format("%.2f",prodotto.prezzo = prodotto.prezzo - (prodotto.prezzo*prodotto.sconto)/100)}€
                                                    </h5>
                                                </c:when>
                                                <c:otherwise>
                                                    <h5>Prezzo: ${prodotto.prezzo}€</h5>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="col-2-right">
                                <div style="float: right">
                                    <div>
                                        <form action="Carrello" method="post">
                                            <label>Quantità:</label>
                                            <select name="addNum">
                                                <c:forEach begin="1" end="30" varStatus="loop">
                                                    <option value="${loop.index}">${loop.index}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="prodId" value="${prodotto.id}">
                                            <input type="submit" name="carrello" value="Aggiungi al carrello">
                                        </form>
                                    </div>
                                    <div class="remove">
                                        <a href="javascript:void(0)" id="remove${prodotto.id}"
                                           style="text-decoration: none"
                                           onclick="rimuovi(${prodotto.id})">Rimuovi</a>
                                        <form id="form-remove${prodotto.id}" action="ListaDesideri"
                                              style="display: none">
                                            <p>Rimuovere prodotto dalla lista dei desideri?</p>
                                            <input type="hidden" name="rimuovi" value="${prodotto.id}">
                                            <input type="submit" name="click" value="Rimuovi">
                                            <button onclick="annulla(${prodotto.id})">Annulla</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </c:when>
            <c:otherwise>
                <p>CIAO ${utente.username}</p>
                <p>LISTA DESIDERI VUOTA</p>
            </c:otherwise>
        </c:choose>
           </div>
        </div>
    </c:when>
    <c:otherwise>
        <div onclick="chiudi(this)">
        <a href="./login.jsp" style="text-decoration: none">Effettua il login</a>
        </div>
    </c:otherwise>
</c:choose>


<jsp:include page="footer.jsp"/>


