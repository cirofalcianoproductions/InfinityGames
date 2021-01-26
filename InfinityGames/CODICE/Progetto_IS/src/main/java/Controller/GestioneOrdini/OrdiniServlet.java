package Controller.GestioneOrdini;

import Model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Ordini")
public class OrdiniServlet extends HttpServlet {
    private OrdiniDAO ordiniDAO= new OrdiniDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UtenteDAO service= new UtenteDAO();

        Utente utente=(Utente)request.getSession().getAttribute("utente");
        if (utente != null){
            List<Ordini> Ordini= ordiniDAO.getRetrieveByUtente(utente.getId());
            request.setAttribute("ordini",Ordini);
        }

        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/results/ordini.jsp");
        dispatcher.forward(request,response);
    }
}
