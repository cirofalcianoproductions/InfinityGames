<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Errore</title>
    <link rel="stylesheet"
          href="./css/style.css"
          type="text/css"/>
</head>
<body>
<jsp:include page="banner.jsp"/>

<div class="exception-all" ondblclick="chiudi(this)">
    <div class="font">
        <section>
            <h1><%= exception.getMessage() %>
            </h1>
            <h2><% if(exception.getMessage().equals("Username e/o password non validi")) {
            %>
                <h3>ritorna alla pagina di <a href="/InfinityGames/login.jsp"> Login </a></h3>
                <%
                    }%></h2>
        </section>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>