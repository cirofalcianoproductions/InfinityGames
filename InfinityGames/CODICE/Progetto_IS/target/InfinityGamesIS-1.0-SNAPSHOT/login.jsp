<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 21/04/2020
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<!--<html>
<head>
    <title>Infinity Games-Login</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>-->
<jsp:include page="./WEB-INF/results/banner.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<c:choose>
    <c:when test="${utente == null}">
<div id="login" class="page-content" onclick="chiudi(this)">
    <div class="main">
<% if(request.getSession().getAttribute("messaggio") != null){ %>
<div id="error-display">
    <p><%= request.getSession().getAttribute("messaggio")%></p>
    <% request.getSession().setAttribute("messaggio",null);%>
</div>
<% } %>

    <div class="leftcol">
        <div class="loginbox">
            <div class="loginbox-left">
                <h2>Accedi</h2>
                <p>Ad un account esistente</p>
                <br>
                <form action="LoginServlet" method="post">
                  Username:
                  <input type="text" name="username"/><br/>
                  Password:
                  <input type="password" name="password"/><br/>
                  <input type="submit" name="login" value="Login"/>

                  </form>
            </div>
            <div class="loginbox-sep"></div>
            <div class="loginbox-right">
                <h2>Crea</h2>
                <p>Un nuovo account gratuito</p>
                <p>L'iscrizione è gratuita e facilmente accessibile</p>
                <a href="registrazione.jsp">
                    <button>Crea</button>
                </a>
            </div>
        </div>
        <a class="loginbox-last"></a>
    </div>
    <div class="box-right">
        <h2>Perchè unirsi a Infinity Games ?</h2>
        <ul id="why_list">
            <li>
                Acquista e scarica giochi completi
            </li>
            <li>
                Per far crescere la nostra community
            </li>
            <li>
                Per usufruire dei nostri sconti
            </li>
            <li>
                Per rimanere sempre aggiornato
            </li>
        </ul>
    </div>
    </div>
</div>
</c:when>
<c:otherwise>
    <div class="reg-all" onclick="chiudi(this)">
        <div class="font">
            <p style="margin-top: 0px">Hai già effettuato l'accesso</p>
            <p>Vai al <a href="/InfinityGames/" >NEGOZIO</a> </p>
        </div>
    </div>
</c:otherwise>
</c:choose>

<jsp:include page="./WEB-INF/results/footer.jsp"/>


