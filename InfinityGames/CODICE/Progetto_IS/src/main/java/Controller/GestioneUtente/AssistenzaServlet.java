package Controller.GestioneUtente;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Assistenza")
public class AssistenzaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente= (Utente)request.getSession().getAttribute("utente");

        if (utente != null){

            RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/results/assistenza.jsp");
            dispatcher.forward(request,response);
        }else {
            RequestDispatcher dispatcher= request.getRequestDispatcher("./login.jsp");
            dispatcher.forward(request,response);
        }
    }
}
