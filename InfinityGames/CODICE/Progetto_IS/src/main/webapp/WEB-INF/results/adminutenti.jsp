<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 19/05/2020
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Utenti</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/utentiAdmin.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="AdminUtenti"/>
</jsp:include>

<div class="utenti" ondblclick="chiudi(this)">
    <p>CIAO ${utente.username}. IN QUESTA SEZIONE PUOI VEDERE LA LISTA DEGLI UTENTI</p>
    <c:if test="${messaggio != null}">
    <div class="messaggio">
    <p>${messaggio}</p>
    </div>
    </c:if>
    <div style="overflow-x: auto">
        <section>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Admin</th>
                    <th>Ordini</th>
                    <th>Azioni</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${utenti}" var="utenti">
                    <c:if test="${utenti.id != utente.id }">
                        <tr>
                            <td>${utenti.id}</td>
                            <td>${utenti.username}</td>
                            <td>${utenti.email}</td>
                            <td>${utenti.admin ? "Si" : "No"}</td>
                            <td><a href="Todo?id=${utenti.id}">Dettagli</a></td>
                            <td>
                                <form action="Todo" method="Post">
                                    <input type="hidden" name="remove-id" value="${utenti.id}">
                                <c:if test="${!utenti.admin}">
                                        <input type="submit" name="rimuovi" value="Rimuovi">
                                        <input type="submit" name="promuovi" value="Promuovi">
                                </c:if>
                                    <c:if test="${utenti.admin}">
                                    <input type="submit" name="degrada" value="Degrada">
                                    </c:if>
                                    </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </div>
</div>

<jsp:include page="footer.jsp"/>

