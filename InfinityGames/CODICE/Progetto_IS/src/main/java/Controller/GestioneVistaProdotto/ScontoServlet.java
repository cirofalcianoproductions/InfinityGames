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
import java.util.List;

@WebServlet("/Sconto")
public class ScontoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdottoDAO service= new ProdottoDAO();

        int perpag = 10;

        int totaleprodotti = service.countBySconto();
        int npag = (totaleprodotti + perpag - 1) / perpag;
        request.setAttribute("npag", npag);

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


        String ordstr = request.getParameter("ord");

        ProdottoDAO.OrderBySconto ord;
        if (ordstr != null && (ordstr.equals("DEFAULT") || ordstr.equals("PREZZO_ASC") || ordstr.equals("PREZZO_DESC"))) {
            ord = ordstr == null ? ProdottoDAO.OrderBySconto.DEFAULT : ProdottoDAO.OrderBySconto.valueOf(ordstr);
        }else{
            ord= ProdottoDAO.OrderBySconto.DEFAULT;
        }
        request.setAttribute("ord", ord);


        List<Prodotto> prodotti = service.doRetrieveBySconto(ord, (pag - 1) * perpag, perpag);
        request.setAttribute("sconti",prodotti);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/sconti.jsp");
        dispatcher.forward(request,response);
    }
}
