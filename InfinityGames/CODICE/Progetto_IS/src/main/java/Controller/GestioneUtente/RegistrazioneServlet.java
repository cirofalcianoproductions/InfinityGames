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
import java.util.Collection;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
    private UtenteDAO utenteDAO = new UtenteDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("utente") == null) {

        String username = request.getParameter("username");
        if (!(username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+$")&& utenteDAO.doRetrieveByUsername(username) == null)) {
            request.setAttribute("notifica","Username non valido.");
            throw new MyServletException("Username non valido.");
        }

        String password = request.getParameter("password");
        if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
            request.setAttribute("notifica","Password non valida.");
            throw new MyServletException("Password non valida.");

        }

        String passwordConferma = request.getParameter("passwordConferma");
        if (!password.equals(passwordConferma)) {
            request.setAttribute("notifica","Password e conferma differenti.");
            throw new MyServletException("Password e conferma differenti.");

        }



        String email = request.getParameter("email");
        if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {
            request.setAttribute("notifica","Email non valida.");
            throw new MyServletException("Email non valida.");

        }

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setEmail(email);
        utenteDAO.Userregistration(utente);
            if (utente == null) {
                request.getSession().setAttribute("messaggio", "Username e/o password non validi");

            }


            if (utente != null) {
                Carrello carrello = CarrelloDAO.doRetrieveByIdUtente(utente.getId());
                carrello.setIdUtente(utente.getId());
                Collection<Carrello.ProdottoQuantita> lista = carrello.getProdotti();
                ArrayList<Carrello.ProdottoQuantita> lis = new ArrayList<>(lista);
                Carrello sessionCar = (Carrello) request.getSession().getAttribute("carrello");
                if (sessionCar != null) {
                    Collection<Carrello.ProdottoQuantita> sessionLista = sessionCar.getProdotti();
                    ArrayList<Carrello.ProdottoQuantita> sessionLis = new ArrayList<>(sessionLista);

                    sessionCar.setIdUtente(utente.getId());
                    for (int j = 0; j < lis.size(); j++) {
                        for (int i = 0; i < sessionLis.size(); i++) {
                            if (sessionLis.get(i).getProdotto().getId() == lis.get(j).getProdotto().getId()) {
                                CarrelloDAO.setQuantita(carrello, sessionLis.get(i).getQuantita(), sessionLis.get(i).getProdotto().getId());
                            } else {
                                CarrelloDAO.doSave(carrello, sessionLis.get(i).getProdotto(), sessionLis.get(i).getQuantita());
                            }
                        }
                    }
                }
                if (carrello.getProdotti().size() < 1) {
                    carrello = (Carrello) request.getSession().getAttribute("carrello");

                    if (carrello != null) {
                        CarrelloDAO.doSaveAll(carrello);
                    }
                }

                request.getSession().setAttribute("carrello", carrello);
            }
        request.getSession().setAttribute("utente", utente);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/registrazioneSuccesso.jsp");
        requestDispatcher.forward(request, response);
    }else {
            throw new MyServletException("Utente loggato.");
        }
        }
}
