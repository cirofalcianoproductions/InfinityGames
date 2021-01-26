<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%-- Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 10/04/2020
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Infinity Games - ${param.pageTitle}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/footer.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/adminCategoria.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/utentiAdmin.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/adminProdotto.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/carrello.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/categoria.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/profilo.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/ordini.css"
          type="text/css"/>
    <link rel="stylesheet" href="./css/prodotto.css">
    <link rel="stylesheet"
          href="./css/recenti.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="./css/contatti.css"
          type="text/css"/>
    <link rel="stylesheet" href="./css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./javascript/menuRes.js"></script>
    <script src="./javascript/menuLaterale.js"></script>
</head>
<body>

<div class="super_container" >
    <div class="contieni-icona">
        <div id="resp" class="icon-resp">
            <div>
          <span>
              <a href="javascript:void(0)" style="text-decoration: none" onclick="openNav()"><i
                      class="fa fa-align-justify" style="font-size: 45px"></i></a>
          </span>
            </div>
        </div>
        <div id="myNav" class="overlay">
            <a href="javascript:void(0)" style="text-decoration: none" onclick="closeNav()">&times;</a>
            <div class="overlay-content">
                <div class="menu-resp">
                    <div class="drop">
                        <a class="responsive-menu" href="#" style="text-decoration: none">Negozio </a>
                        <div class="freccia"><i class="fa fa-angle-double-down"></i></div>
                    </div>
                    <div class="dropdown-content">
                        <a href="ListaDesideri">Lista dei desideri</a>
                        <a href="./notizie.jsp">Chi siamo</a>
                        <a href="/InfinityGames/">Home</a>
                    </div>
                </div>
                <div class="menu-resp">
                    <a class="responsive-menu" href="Libreria" style="text-decoration: none">La Tua Libreria </a>
                </div>
                <div class="menu-resp">
                    <a class="responsive-menu" href="Assistenza" style="text-decoration: none">ASSISTENZA</a>
                </div>
                <div class="menu-resp">
               <%--     <c:if test="${carrello.prodotti.size() >0 }"> --%>
                        <a class="responsive-menu" href="Carrello" style="text-decoration: none">CARRELLO

                        </a>
                 <%--   </c:if> --%>
                </div>

                <div class="menu-resp">
                    <c:if test="${utente == null}">
                        <a class="responsive-menu" href="./login.jsp" style="text-decoration: none">LOGIN </a>
                    </c:if>
                </div>

                <c:if test="${utente != null}">
                    <div class="menu-resp">
                        <div class="drop">
                            <a class="responsive-menu" href="#" style="text-decoration: none">${utente.username}</a>
                            <div class="freccia"><i class="fa fa-angle-double-down"></i></div>
                        </div>
                        <div class="dropdown-content-ute" style="top:270px;">
                            <c:if test="${utente.admin}">
                                <a href="AdminCategoria">Aggiungi Categoria</a>
                                <a href="AdminProdotto">Aggiungi Prodotto</a>
                                <a href="AdminUtenti">Utenti</a>
                            </c:if>
                            <c:if test="${!utente.admin}">
                                <a href="Profilo">Profilo</a>
                                <a href="Ordini">I miei ordini</a>
                            </c:if>
                            <a href="Logout">Logout</a>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="logo">
            <a href="/InfinityGames/">
                <img class="logo-img" src="./images/logoTSW.png" width="50%" height="90px">
                <span class="imgName">INFINITY GAMES</span>
            </a>
        </div>

        <div class="menu">
            <ul class="menu_tendina">
                <li class="dropdown">
                    <a class="menuitem" href="/InfinityGames/"
                       style="text-decoration: none">NEGOZIO </a>
                    <div class="dropdown-nonresp">

                        <a href="ListaDesideri">Lista dei desideri</a>
                        <a href="./notizie.jsp">Chi siamo</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a class="menuitem" href="Libreria" style="text-decoration: none">LA TUA LIBRERIA </a>
                </li>
                <a class="menuitem" href="Assistenza" style="text-decoration: none">ASSISTENZA </a>

               <%-- <c:if test="${carrello.prodotti.size() >0}"> --%>
                    <a class="menuitem" href="Carrello" style="text-decoration: none">CARRELLO<c:if
                            test="${carrello.prodotti.size() != null && carrello.prodotti.size() >0}">(${carrello.prodotti.size()})</c:if> </a>
             <%--   </c:if> --%>

                <c:if test="${utente == null}">
                    <a class="menuitem" href="./login.jsp" style="text-decoration: none">LOGIN</a>
                </c:if>

                <c:if test="${utente != null}">
                    <li class="dropdown">
                        <a class="menuitem" href="#" style="text-decoration: none">${utente.username}</a>
                        <div class="dropdown-nonresp">
                            <c:if test="${utente.admin}">
                                <a href="AdminCategoria">Aggiungi Categoria</a>
                                <a href="AdminProdotto">Aggiungi Prodotto</a>
                                <a href="AdminUtenti">Utenti</a>
                            </c:if>
                            <c:if test="${!utente.admin}">
                                <a href="Profilo">Profilo</a>
                                <a href="Ordini">I miei ordini</a>
                            </c:if>
                            <a href="Logout">Logout</a>

                        </div>
                    </li>
                </c:if>
            </ul>
        </div>


    </div>

</div>

<!--
</body>
</html>
-->