<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 19/05/2020
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<html>
<head>
    <title>Registrazione avvenuta con successo</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Registrazione avvenuta con successo"/>
</jsp:include>
<div class="reg-all" ondblclick="chiudi(this)">
<div class="font">
    <p style="margin-top: 0px">Benvenuto su Infinity Games!</p>
    <p>Ora puoi usufruire dei nostri servizi e delle nostre offerte</p>
</div>
</div>
<jsp:include page="footer.jsp"/>

