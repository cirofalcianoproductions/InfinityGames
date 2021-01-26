<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 23/06/2020
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<!--<html>
<head>
    <link rel="stylesheet"
          href="./css/ordini.css"
          type="text/css"/>
</head>
<body>-->
<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="OrdiniUtenti"/>
</jsp:include>
<c:choose>
    <c:when test="${utente != null}">
        <div class="all" ondblclick="chiudi(this)">
        <c:if test="${ordiniUtenti.size() > 0}">
            <c:forEach items="${ordiniUtenti}" var="ord">
                <div class="ordini">
                    <div class="info">
                        <div class="info-lato"><p>Numero Ordine: ${ord.id}</p></div>
                        <div class="info-lato"><p>Data: ${ord.data}</p></div>
                        <div class="info-lato"><p>Totale: ${ord.totale}€</p></div>
                    </div>
                    <div class="contain-ord">
                        <c:forEach items="${ord.prodotti}" var="prodotto">
                            <div class="prodotto-ordine">
                                <p>Nome:${prodotto.prodotto.nome}</p>
                                <p>Prezzo:${prodotto.prodotto.prezzo}€</p>
                                <p>Quantita:${prodotto.quantita}</p>
                            </div>
                        </c:forEach>

                    </div>

                </div>
            </c:forEach>
        </c:if>
        <c:if test="${ordiniUtenti.size() < 1}">
            <h3> Nessun ordine effettuato</h3>
        </c:if>
        </div>

    </c:when>
    <c:otherwise>
        <p>Per visualizzare gli ordini <a href="./login.jsp">Effettua il login</a> </p>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>


