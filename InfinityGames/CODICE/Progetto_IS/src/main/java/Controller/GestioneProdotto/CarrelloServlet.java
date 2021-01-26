package Controller.GestioneProdotto;


import Controller.GestioneUtente.MyServletException;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {

    ArrayList<Prodotto> carrello = new ArrayList<Prodotto>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        if (carrello == null) {
            carrello = new Carrello();
            if (utente != null) {
                carrello.setIdUtente(utente.getId());
            }
            session.setAttribute("carrello", carrello);
        }

        String idSt = request.getParameter("prodId");

        if (idSt != null) {
            int prodId ;

            try {
                prodId = Integer.parseInt(idSt);

            } catch (NumberFormatException e) {
                throw new MyServletException("Id prodotto non valido");
            }

            String addNumStr = request.getParameter("addNum");
            if (addNumStr != null) {
                int addNum ;
                try {
                    addNum = Integer.parseInt(addNumStr);

                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non valido");
                }
                Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
                if (prodQuant != null) {
                    prodQuant.setQuantita(prodQuant.getQuantita() + addNum);
                    if (utente != null) {
                        CarrelloDAO.setQuantita(carrello, prodId, addNum);
                    }

                } else {
                    Prodotto prodotto = prodottoDAO.doRetrieveById(prodId);
                    carrello.put(prodotto, addNum);
                    if (utente != null) {
                        CarrelloDAO.doSave(carrello, prodotto, addNum);

                    }

                }
            } else {
                String removeNum = request.getParameter("removeNum");
                if (removeNum != null) {
                    int addNum ;
                    try {
                        addNum = Integer.parseInt(removeNum);

                    } catch (NumberFormatException e) {
                        throw new MyServletException("Id prodotto non valido");
                    }
                    Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
                    if (prodQuant != null) {
                        prodQuant.setQuantita(prodQuant.getQuantita() - addNum);
                        if (utente != null) {
                            CarrelloDAO.removeQuantita(carrello, addNum, prodId);
                        }

                    }
                    if (prodQuant.getQuantita() == 0) {
                        carrello.remove(prodQuant.getProdotto().getId());
                        if (utente != null) {
                            CarrelloDAO.removeProdById(carrello, prodId);
                        }

                    }
                }
            }
        }
        String all = request.getParameter("rimuovi-all");
        if (all != null) {
            Collection<Carrello.ProdottoQuantita> list = carrello.getProdotti();
            ArrayList<Carrello.ProdottoQuantita> lis = new ArrayList(list);
            if (utente != null) {
                CarrelloDAO.doDeleteAll(carrello);
            }
            for (int i = 0; i < lis.size(); i++) {
                carrello.remove(lis.get(i).getProdotto().getId());
            }

        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/results/carrello.jsp");
        dispatcher.forward(request, response);

    }
}
