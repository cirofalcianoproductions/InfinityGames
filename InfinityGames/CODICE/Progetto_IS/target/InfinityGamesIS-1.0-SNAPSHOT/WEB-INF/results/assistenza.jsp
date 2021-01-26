<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 09/06/2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Assistenza</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Assistenza"/>
</jsp:include>
<div class="assistenza" ondblclick="chiudi(this)">

    <c:choose>
    <c:when test="${utente != null}">
    <div>
        <p>
            Ciao, ${utente.username}. Come possiamo aiutarti?
        </p>
    </div>
    <div class="contieni-assistenza">
        <div class="affianca">
            <div>
                <img src="./images/pacco.jpg">
            </div>
            <div>
                <a href="/InfinityGames/Ordini">
                    I miei ordini
                </a>
                <p>
                    Visualizza i tuoi oridni
                </p>
            </div>
        </div>

        <div class="affianca">
            <div>
                <img src="./images/profilo.jpg">
            </div>
            <div>
                <a href="/InfinityGames/Profilo">
                    Il mio account
                </a>
                <p>
                    Gestisci il tuo account
                </p>
            </div>
        </div>

        <div class="affianca">
            <div>
                <img src="./images/contatti.jpg">
            </div>
            <div>
                <a href="./contatti.jsp">
                    Contatti
                </a>
                <p>
                    Contatta il nostro Servizio Clienti
                </p>
            </div>
        </div>

        </c:when>
        <c:otherwise>
            <p>Per poter ricevere assistenza</p>
            <a href="./login.jsp">Effettua il login</a>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="footer.jsp"/>

