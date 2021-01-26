<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 07/06/2020
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!--<html>
<head>
    <title>Modifica Profilo</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/categoria.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/profilo.css"
          type="text/css"/>
    <script src="./javascript/modificaProfilo.js"></script>

</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="ModificaProfilo"/>
</jsp:include>
<c:choose>
    <c:when test="${utente != null}">
        <div class="contain" ondblclick="chiudi(this)">
            <div class="contain-all" style="padding: 2%">
                <div class="info">
                    <div class="img-profilo">
                        <img src="./images/utenti/${utente.images}" width="100px" height="120px">
                    </div>
                    <div class="nome">
                        <h3>${utente.username}</h3>
                    </div>
                </div>
                <c:if test="${modificato != null}">
                    <div class="modificato"><p>${modificato}</p></div>
                </c:if>
                <% request.getSession().setAttribute("modificato", null);%>
                <div class="form">

                    <form name="modifica" action="Modifica" method="post">

                            <div class="form-info">
                                <div><label>Password Precedente</label></div>
                                <div><input type="password" name="passwordPrecedente" oninput="validaPasswordPrecedente()"></div>
                            </div>
                        <div class="form-info">
                            <div><label>Modifica Password </label></div>
                            <div><input type="password" name="password" oninput="validaPasswordProfilo()"></div>
                        </div>
                        <div class="form-info">
                            <div><label>Password (conferma)</label></div>
                            <div><input type="password" name="passwordConferma"  oninput="validaPasswordProfilo()"></div>
                        </div>
                        <div>
                            <input id="salva" name="salvaModifiche" type="submit" value="Salva" disabled>
                            <span id="modificamessaggio"></span>
                        </div>
                    </form>

                    <form  method="post" action="Modifica" enctype="multipart/form-data">
                        <div><label>Modifica Immagine</label></div>
                        <div class="imag-prf"><img src="./images/utenti/${utente.images}"></div>
                        <div><input  type="file" name="file" ></div>
                        <div><input type="submit" name="Aggiorna" value="Aggiorna"></div>
                    </form>

                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <p> Effettua il <a href="./login.jsp"> login</a></p>
    </c:otherwise>
</c:choose>
<jsp:include page="footer.jsp"/>


