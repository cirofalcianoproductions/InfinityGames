package Controller.GestioneProdotto;

import Controller.GestioneUtente.MyServletException;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String q = request.getParameter("q");
        if (q != null) {

            List<Prodotto> prodotti = prodottoDAO.doRetrieveByNome(q + "*", null, 0, 10);


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

            int totaleprodotti = prodotti.size();
            int npag = (totaleprodotti + perpag - 1) / perpag;
            request.setAttribute("npag", npag);
            String ordstr = request.getParameter("ord");

            ProdottoDAO.OrderBy ord;
            if (ordstr != null && (ordstr.equals("DEFAULT") || ordstr.equals("PREZZO_ASC") || ordstr.equals("PREZZO_DESC"))) {
                ord = ordstr == null ? ProdottoDAO.OrderBy.DEFAULT : ProdottoDAO.OrderBy.valueOf(ordstr);
            } else {
                ord = ProdottoDAO.OrderBy.DEFAULT;
            }
            request.setAttribute("ord", ord);
            List<Prodotto> ricerca = prodottoDAO.doRetrieveByNome(q + "*", ord, (pag - 1) * perpag, perpag);

            request.setAttribute("stringa", q);
            request.setAttribute("ricerca", ricerca);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/ricerca.jsp");
        requestDispatcher.forward(request, response);
    }
}
