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
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente=(Utente)request.getSession().getAttribute("utente");

            int id;

            try {
                id = Integer.parseInt(request.getParameter("id"));

            } catch (NumberFormatException e) {
                throw new MyServletException("Id prodotto non valido");
            }
            ProdottoDAO service = new ProdottoDAO();

            Prodotto prodotto = service.doRetrieveById(id);
            if (prodotto == null) {
                throw new MyServletException("Prodotto non trovato");
            }
        request.setAttribute("prodotto", prodotto);

        if (utente != null) {
            Date data = new Date();
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);
            String s = formatoData.format(data);

            boolean flag = false;
            int[] visual = service.doRetrieveVisAll(utente.getId());
            for (int i = 0; i < visual.length; i++) {
                if (visual[i] == id) {
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                service.setDataVis(utente.getId(), id, s);
            } else {
                service.doSaveProdotto(utente.getId(), id, s);
            }

        }

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/results/prodotto.jsp");
        requestDispatcher.forward(request, response);

    }
}
