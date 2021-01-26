<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 19/05/2020
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Categorie</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/adminCategoria.css"
          type="text/css"/>

</head>
<body>-->

<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (categoria == null ? 'Aggiungi' : 'Modifica')}"/>

<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Categoria"/>
</jsp:include>
<div class="cat-all" ondblclick="chiudi(this)">
    <div class="font">
        <div>
            <h1>${operazione} categoria:</h1>
            <h5>${notifica}</h5>
            <c:if test="${param.rimuovi == null}">
                <div>
                    <form action="AdminCategoria" method="post">
                        <input type="hidden" name="id" value="${categoria.id}">
                        <div>
                            <label>Nome:</label>
                            <input type="text" name="nome" value="${categoria.nome}">
                        </div>
                        <div>
                            <label>Descrizione:</label>
                            <textarea name="descrizione">${categoria.descrizione}</textarea>
                        </div>
                        <input type="submit" value="${operazione}">
                        <c:if test="${categoria != null}">
                            <input type="submit" name="rimuovi" value="Rimuovi">
                        </c:if>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

