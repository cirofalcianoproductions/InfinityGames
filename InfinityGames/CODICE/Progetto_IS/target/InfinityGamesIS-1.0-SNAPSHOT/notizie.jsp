<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 09/06/2020
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<html>
<head>
    <title>Notizie</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="./WEB-INF/results/banner.jsp">
    <jsp:param name="pageTitle" value="Notizie"/>
</jsp:include>
<div style="margin: 2% 4%" onclick="chiudi(this)">
<p>
    InfinityGames è una piattaforma sviluppata da alunni di UNISA che si occupa di distribuzione digitale,
    di gestione dei diritti digitali, di modalità di gioco multi giocatore.
    Viene usata per gestire e distribuire una vasta gamma di giochi (alcuni esclusivi) e il loro relativo supporto.
    Tutte queste operazioni sono effettuate via Internet.
</p>
<p>
    InfinityGames permette agli utenti di acquistare giochi attraverso un sistema di distribuzione digitale:
    infatti una volta effettuato un acquisto, invece di ricevere tramite spedizione postale il gioco fisico (la scatola, il disco o il codice seriale),
    il contenuto viene immediatamente aggiunto alla propria libreria per essere acquisito.
</p>
</div>

<jsp:include page="./WEB-INF/results/footer.jsp"/>

