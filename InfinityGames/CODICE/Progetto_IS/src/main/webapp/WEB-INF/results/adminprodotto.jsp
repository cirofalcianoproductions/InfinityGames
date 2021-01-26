<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 12/05/2020
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<!--<html>
<head>
    <title>Infinity Games</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/adminProdotto.css"
          type="text/css"/>
</head>
<body>-->

<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>

<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="AdminProdotto"/>
</jsp:include>

<div class="add-prod" ondblclick="chiudi(this)">
    <h1>${operazione} prodotto</h1>
    <div class="messaggio">
    <h5 id="notifica">${notifica}</h5>
    </div>
    <c:if test="${param.rimuovi == null}">
        <div class="form-prod">
        <form action="AdminProdotto" name="admin-form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${prodotto.id}">
            <div class="add">
            <label style="display: block">Inserisci le Categorie:</label>
            <c:forEach items="${categorie}" var="categoria">
                <input type="checkbox" name="categorie" value="${categoria.id}"
                       <c:if test="${operazione != 'Modifica'}">oninput="validaCat()"</c:if><c:if test="${prodotto.categorie.stream().anyMatch(c -> c.id == categoria.id).orElse(false)}">checked</c:if>><label>${categoria.nome}</label>
            </c:forEach>
            </div>
            <div class="add">
            <label style="display: block">Nome:</label>
            <input type="text" name="nome" value="${prodotto.nome}" <c:if test="${operazione != 'Modifica'}">oninput="validaNome()"</c:if>>
            <label style="display: block">Descrizione:</label>
            <textarea name="descrizione" <c:if test="${operazione != 'Modifica'}">oninput="validaDesc()"</c:if>>${prodotto.descrizione}</textarea>
            <label style="display: block">Prezzo:</label>
            <input type="number" name="prezzoCent" value="${prodotto.prezzo}" <c:if test="${operazione != 'Modifica'}">oninput= "validaPrezzo()"</c:if>><br>
            <label style="display: block">Sconto:</label>
            <input type="number" name="sconto" value="${prodotto.sconto}"<c:if test="${operazione != 'Modifica'}"> oninput="validaSconto()"</c:if>>
            </div>
            <div class="add">
            <label>Carica Immagine: </label>

            <input type="file" name="file"  <c:if test="${operazione != 'Modifica'}">oninput="validaImg()" </c:if>><br>
            </div>
            <div class="add">
            <label>Carica video: </label>
            <input type="file" name="video" <c:if test="${operazione != 'Modifica'}">oninput="validaVideo()" </c:if>>
            </div>
            <input id="modifica" type="submit"  name="operazioneProdotto"  value="${operazione}" <c:if test="${operazione != 'Modifica'}">disabled</c:if>>
            <c:if test="${prodotto != null}">
                <input type="submit" name="rimuovi" value="Rimuovi" >
            </c:if>

        </form>
        </div>
    </c:if>
</div>
<jsp:include page="footer.jsp"/>


