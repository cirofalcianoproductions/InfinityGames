<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 09/06/2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<!--<html>
<head>
    <title>Recenti</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/recenti.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/categoria.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Recenti"/>
</jsp:include>
<c:choose>
    <c:when test="${utente != null}">
<c:choose>
<c:when test="${recenti.size() > 0}">
    <div class="contain-recenti" ondblclick="chiudi(this)">
        <div class="contain_all">

    <p> IN QUESTA SEZIONE PUOI TROVARE I PRODOTTI VISUALIZZATI NELL'ULTIMO MESE</p>

    <!-- MENU # 1 -->

    <div id="menu" >
    <c:forEach items="${recenti}" var="prodotto">
        <div class="contieni-rec">
            <div class="img-col-rec">
                <a class="sub-img-rec" href="Prodotto?id=${prodotto.id}"><img src="images/prodotti/${prodotto.images}"></a>
            </div>
            <div class="col-2-rec">
                <div class="right-col-rec">
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
                                    <h5>Prezzo: <del>${prodotto.prezzo}€</del> ${String.format("%.2f",prodotto.prezzo = prodotto.prezzo - (prodotto.prezzo*prodotto.sconto)/100)}€</h5>
                                </c:when>
                                <c:otherwise>
                                    <h5>Prezzo: ${prodotto.prezzo}€</h5>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>


            </div>
            <div class="col-2-right-rec">
                <div style="float: right">
                    <p>Categorie:
                        <c:forEach items="${prodotto.categorie}" var="categoria" varStatus="status">
                            <a class="categoria" href="CategoriaServlet?id=<c:out value="${categoria.id}"/>"><c:out value="${categoria.nome}" /></a><c:if test="${not status.last}">, </c:if>
                        </c:forEach>
                    </p>
                </div>
                <div>
                    <p style="font-size: 13px">Visualizzato il: ${prodotto.data}</p>
                </div>
            </div>
        </div>

    </c:forEach>

    </div>


    <div style="position: relative;">
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


</c:when>
    <c:otherwise>
        <div class="recenti">
            <h3>NESSUN PRODOTTO VISUALIZZATO</h3>
        </div>
    </c:otherwise>
</c:choose>
    </c:when>
    <c:otherwise>
        <div class="recenti">
            <a href="./login.jsp">Effettua il login</a>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>

