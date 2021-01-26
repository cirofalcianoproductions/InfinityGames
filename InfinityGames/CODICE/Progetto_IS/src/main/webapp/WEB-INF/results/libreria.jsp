<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.ProdottoDAO" %>
<%@ page import="Model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 07/05/2020
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="banner.jsp">
    <jsp:param name="pageTitle" value="Libreria"/>
</jsp:include>
<c:choose>
    <c:when test="${utente != null}">
<div class="contain" ondblclick="chiudi(this)">
    <div class="contain_all">

        <p> IN QUESTA SEZIONE PUOI TROVARE I TUOI GIOCHI</p>
        <c:choose>
            <c:when test="${libreria.size() > 0}">
                <!-- MENU # 1 -->
                <div id="menu-main">
                    <div class="tab-bar">
                        <div class="ordina">
                            <p>Ordine:
                                <c:forEach items="${ord.values()}" var="o">
                                    <c:choose>
                                        <c:when test="${o == 'DEFAULT'}">
                                            <c:set var="desc" value="default" />
                                        </c:when>
                                        <c:when test="${o == 'PREZZO_ASC'}">
                                            <c:set var="desc" value="alfabetico(ASC)" />
                                        </c:when>
                                        <c:when test="${o == 'PREZZO_DESC'}">
                                            <c:set var="desc" value="alfabetico(DESC)" />
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${o == ord}">
                                            <i>${desc}</i>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="?pag=${pag}&perpag=${perpag}&ord=${o}">${desc}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>
                <div id="menu" >
                    <c:forEach items="${libreria}" var="prodotto">
                        <div class="contieni">
                            <div class="img-col">
                                <a class="sub-img" href="Prodotto?id=${prodotto.id}"><img src="images/prodotti/${prodotto.images}"></a>
                            </div>
                            <div class="col-2">
                                <div class="right-col">
                                    <div class="name-over">
                                        <h5>
                                                ${prodotto.nome}
                                        </h5>
                                    </div>
                                </div>

                            </div>
                            <div class="col-2-right">
                                <div style="float: right">
                                   <p>
                                       ${prodotto.descrizione}
                                   </p>
                                    <p>Categorie:
                                        <c:forEach items="${prodotto.categorie}" var="categoria" varStatus="status">
                                            <a class="categoria" href="CategoriaServlet?id=<c:out value="${categoria.id}"/>"><c:out value="${categoria.nome}" /></a><c:if test="${not status.last}">, </c:if>
                                        </c:forEach>
                                    </p>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>


                <div style="position: relative; top: 10px">
                    <a <c:if test="${pag > 1}">href="?id=${param.id}&pag=${pag - 1}"</c:if>>&larr; precendente</a>
                    &emsp;
                    <c:forEach begin="1" end="${npag}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.index == pag}">
                                <b>${loop.index}</b>
                            </c:when>
                            <c:otherwise>
                                <a href="?id=${param.id}&pag=${loop.index}">${loop.index}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    &emsp;
                    <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}"</c:if>>successiva &rarr;</a>
                </div>
            </c:when>
            <c:otherwise>
                <h3>NESSUN PRODOTTO</h3>
            </c:otherwise>
        </c:choose>

    </div>
</div>
    </c:when>
    <c:otherwise>
        <a href="./login.jsp">Effettua il login</a>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>
