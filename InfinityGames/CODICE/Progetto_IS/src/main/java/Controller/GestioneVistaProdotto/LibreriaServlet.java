package Controller.GestioneVistaProdotto;


import Controller.GestioneUtente.MyServletException;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/Libreria")
public class LibreriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {

            ProdottoDAO service = new ProdottoDAO();

            String pagstr = request.getParameter("pag");
            int pag;
            if (pagstr == null || pagstr.equals("")) {
                pag = 1;
            } else {

                try {
                    pag = Integer.parseInt(pagstr);

                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non valido");
                }
            }
            request.setAttribute("pag", pag);

            int perpag = 10;

            if (utente.getLibreria() != null) {
                int totaleprodotti = utente.getLibreria().size();
                int npag = (totaleprodotti + perpag - 1) / perpag;
                request.setAttribute("npag", npag);

                String ordstr = request.getParameter("ord");
                ProdottoDAO.OrderByAlfabetico ord;
                if (ordstr != null && (ordstr.equals("DEFAULT") || ordstr.equals("PREZZO_ASC") || ordstr.equals("PREZZO_DESC"))) {
                    ord = ordstr == null ? ProdottoDAO.OrderByAlfabetico.DEFAULT : ProdottoDAO.OrderByAlfabetico.valueOf(ordstr);
                }else{
                    ord= ProdottoDAO.OrderByAlfabetico.DEFAULT;
                }
                request.setAttribute("ord", ord);

                List<Prodotto> prodotti = service.doRetrieveByLibreria(ord, utente.getId(), (pag - 1) * perpag, perpag);

                request.setAttribute("libreria", prodotti);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/libreria.jsp");

                dispatcher.forward(request, response);
                return;

            }else{

               request.setAttribute("libreria", Collections.emptyList());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/libreria.jsp");
                dispatcher.forward(request, response);
            }

        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

    }
}
