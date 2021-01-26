<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 30/03/2020
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Infinity Games</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet" href="./css/categoria.css"/>
</head>
<body onload="Vista()">
<jsp:include page="banner.jsp"/>
<div class="contain" ondblclick="chiudi(this)">
    <div class="contain_all">
        <c:if test="${utente.admin}">
            <form action="AdminCategoria" method="post">
                <h1>${categoria.nome}
                    <input type="hidden" name="id" value="${categoria.id}">
                    <input type="submit" value="Modifica">
                    <input type="submit" name="rimuovi" value="Rimuovi">
                </h1>
            </form>
        </c:if>


        <p>Prodotti con l'etichetta "${categoria.nome}"</p>
        <p> Esplora i prodotti più popolari, i più venduti e quelli scontati con l'etichetta "${categoria.nome}" su
            Infity Games</p>

        <div id="menu-main">
            <div class="tab-bar">
                <div id="n1"><a href="javascript:void(0)" onclick="Apri(1,${categoria.id},${pag},'${ord}')" id="principale1">Tutti
                    i
                    giochi</a></div>
                <div id="n2"><a href="javascript:void(0)" onclick="Apri(2,${categoria.id},${pag},'${ord}')"
                                id="principale2">Popolari</a></div>
                <div id="n3"><a href="javascript:void(0)" onclick="Apri(3,${categoria.id},${pag},'${ord}')" id="principale3">I
                    piu
                    venduti</a></div>
            </div>
            <div class="ordina">
                <p>Ordine:
                    <c:forEach items="${ord.values()}" var="o">
                        <c:choose>
                            <c:when test="${o == 'DEFAULT'}">
                                <c:set var="desc" value="default"/>
                            </c:when>
                            <c:when test="${o == 'PREZZO_ASC'}">
                                <c:set var="desc" value="prezzo (crescente)"/>
                            </c:when>
                            <c:when test="${o == 'PREZZO_DESC'}">
                                <c:set var="desc" value="prezzo (decrescente)"/>
                            </c:when>
                        </c:choose>
                        <i onclick="categoria(${categoria.id},${pag},'${o}')">${desc}</i>
                    </c:forEach>
                </p>
            </div>
        </div>

        <!-- MENU # 1 -->
        <div id="sottomenu1">
            <c:forEach items="${prodotti}" var="prodotto">
                <div class="contieni">
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
                            <p>Categorie:
                                <c:forEach items="${prodotto.categorie}" var="categoria" varStatus="status">
                                    <a class="categoria"
                                       href="CategoriaServlet?id=<c:out value="${categoria.id}"/>"><c:out
                                            value="${categoria.nome}"/></a><c:if test="${not status.last}">, </c:if>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>

            </c:forEach>

        </div>
        <div id="sottomenu2">

        </div>
        <div id="sottomenu3">

        </div>
        <div class="pagina">
            <a <c:if test="${pag > 1}">href="?id=${param.id}&pag=${pag - 1}"</c:if>>&larr; precendente</a>
            &emsp;
            <c:forEach begin="1" end="${npag}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index == pag}">
                        <b>${loop.index}</b>
                    </c:when>
                    <c:otherwise>
                        <a href="?id=${param.id}&pag=${loop.index}">${loop.index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            &emsp;
            <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}"</c:if>>successiva &rarr;</a>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="./javascript/menu.js"></script>
<script src="./javascript/categoria.js"></script>
</body>
</html>
