<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 10/06/2020
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="AcquistoSuccesso"/>
</jsp:include>

<div class="reg-all" ondblclick="chiudi(this)">
    <div class="font">
        <p style="margin-top: 0px">Grazie per aver acquistato su INFINITY GAMES</p>
        <p>I tuoi prodotti sono ora visibili nella tua <a href="Libreria" >Libreria</a> </p>
        <p>Se vuoi continuare ad acquistare vai al <a href="/InfinityGames/" >NEGOZIO</a> </p>
    </div>
</div>


<jsp:include page="footer.jsp"/>

