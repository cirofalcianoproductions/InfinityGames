package Controller.GestioneUtente;

import Model.Utente;
import Model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/VerificaPassword")
public class VerificaPasswordPrecedenteServlet extends HttpServlet {
    private final UtenteDAO utenteDAO = new UtenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String password = request.getParameter("passwordPrecedente");
        response.setContentType("text/xml");

        if (utenteDAO.doRetrieveByUsernamePassword(utente.getUsername(),password)!=null) {
            response.getWriter().append("<ok/>");
        } else {
            response.getWriter().append("<no/>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   doGet(request,response);

    }
}
