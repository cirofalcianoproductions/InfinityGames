<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 03/07/2020
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>-->
<!-- Footer -->
<footer class="page-footer " ondblclick="chiudi(this)">

    <!-- Footer Links -->
    <div class="container-fluid ">

        <!-- Grid row -->
        <div class="roww">

            <!-- Grid column -->
            <div class="col-md-3 ">

                <!-- Links -->
                <p class="text-uppercase">Home</p>

                <ul class="list-unstyled">
                    <li>
                        <a href="/InfinityGames/">Negozio</a>
                    </li>
                    <li>
                        <a href="/InfinityGames/Sconto">Sconti</a>
                    </li>
                    <li>
                        <a href="/InfinityGames/Popolari">Popolari</a>
                    </li>
                    <li>
                        <a href="/InfinityGames/Venduti">Piu Venduti</a>
                    </li>
                </ul>

            </div>
            <!-- Grid column -->

            <!-- Grid column -->
            <div class="col-md-3 ">

                <!-- Links -->
                <p class="text-uppercase">Altro</p>

                <ul class="list-unstyled">
                    <li>
                        <a href="./contatti.jsp">Contatti</a>
                    </li>
<c:if test="${utente == null}">
                    <li>
                        <a href="./login.jsp">Login</a>
                    </li>
</c:if>
                    <li>
                        <a href="./notizie.jsp">Notizie</a>
                    </li>
                    <li>
                        <a href="/InfinityGames/Assistenza">Assistenza</a>
                    </li>
                </ul>

            </div>
            <!-- Grid column -->

            <div class="col-md">

                <!-- Call to action -->
                <ul class="list-unstyled">
                    <li class="list-inline-item">
                        <c:choose>
                            <c:when test="${utente == null}">
                            <p class="text-uppercase">Registrati</p>
                    </li>
                    <li class="list-inline-item">
                        <span class="radius">
                        <a href="./registrazione.jsp">Sign up!</a>
                        </span>
                    </li>
                            </c:when>
                    <c:otherwise>
                        <p class="text-uppercase">Benvenuto</p>
                        </li>
                        <li class="list-inline-item">
                          <span class="radius">
                              <a href="#">Infinity Games</a>
                          </span>
                        </li>
                    </c:otherwise>
                        </c:choose>

                </ul>
                <!-- Call to action -->

            </div>

        </div>
        <!-- Grid row -->

    </div>
    <!-- Footer Links -->

</footer>
<!-- Footer -->

<script src="./javascript/ricerca.js"></script>
<script  src="./javascript/xfade2.js"></script>
<script src="./javascript/navbar.js"></script>
<script src="./javascript/admin-form.js"></script>
<script src="./javascript/carrello.js"></script>
<script src="./javascript/desideri-rimuovi.js"></script>
<script src="./javascript/modificaProfilo.js"></script>
<script src="./javascript/ordiniUtente.js"></script>
<script src="./javascript/addDesideri.js"></script>
<script src="./javascript/validazione_form_registrazione.js"></script>
</body>
</html>
