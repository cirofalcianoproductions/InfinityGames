package Controller.GestioneOrdini;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import static java.lang.Integer.*;

@WebServlet("/Acquisto")
public class AcquistoServlet extends HttpServlet {

    private OrdiniDAO ordiniDAO = new OrdiniDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {


            if (request.getParameter("conf-acq") != null) {

                ProdottoDAO service = new ProdottoDAO();
                UtenteDAO UtenteDao = new UtenteDAO();


                Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

                CarrelloDAO.doDeleteAll(carrello);

                Date date = new Date();
                DateFormat formatoData = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);

                String s = formatoData.format(date);

                Collection<Carrello.ProdottoQuantita> lista = carrello.getProdotti();
                ArrayList<Carrello.ProdottoQuantita> lis = new ArrayList(lista);

                Ordini ordine = new Ordini();
                ordine.setUtente(utente.getId());
                ordine.setProdotti(lis);
                ordine.setData(s);
                ordine.setTotale(carrello.getPrezzoTotCent());

                ordiniDAO.doSave(ordine);

                for (int i = 0; i < lis.size(); i++) {
                    ProdottoDAO.setQuantita(lis.get(i).getProdotto().getId(), lis.get(i).getQuantita());
                }


                OrdiniDAO ordiniDAO = new OrdiniDAO();
                List<Ordini> Ordini = ordiniDAO.getRetrieveByUtente(utente.getId());

                ArrayList<Prodotto> prodotto = new ArrayList<>();

                if (Ordini != null) {

                    for (int i = 0; i < Ordini.size(); i++) {
                        for (int j = 0; j < Ordini.get(i).getProdotti().size(); j++) {
                            prodotto.add(Ordini.get(i).getProdotti().get(j).getProdotto());
                        }
                    }
                    ArrayList<Prodotto> nuovo = new ArrayList<>();


                    for (int i = 0; i < prodotto.size(); i++) {
                        boolean uguale = false;
                        for (int j = i + 1; j < prodotto.size(); j++) {
                            if (prodotto.get(i).getId() == prodotto.get(j).getId()) {
                                uguale = true;
                                break;
                            }
                        }
                        if (!uguale) {
                            nuovo.add(prodotto.get(i));
                        }
                    }


                    if (UtenteDao.getLibreria(utente.getId()).size() > 0) {
                        service.doDeleteLibreria(utente.getId());
                    }
                    utente.setLibreria(nuovo);
                    for (int i = 0; i < nuovo.size(); i++) {
                        service.doSaveInLibreria(nuovo.get(i), utente.getId());
                    }


                    carrello.getProdotti().clear();
                }


            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/results/AcquistoSuccesso.jsp");
            dispatcher.forward(request, response);


        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

