package Controller.GestioneUtente;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="/HomeServlet", urlPatterns = "/index.html")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        ArrayList<Prodotto> prodotti = prodottoDAO.doRetrieveAll(0,5);
        ArrayList<Prodotto> lista= prodottoDAO.doRetrieveAllSconto();

        request.setAttribute("sconti",lista);
        request.setAttribute("prodotti", prodotti);


        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/index.jsp");
        dispatcher.forward(request,response);
    }
}
