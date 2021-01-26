package Controller.GestioneAmministratore;

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

@WebServlet("/Todo")
public class TodoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ute = (Utente) request.getSession().getAttribute("utente");
        OrdiniDAO ordiniDAO = new OrdiniDAO();
        UtenteDAO utenteDAO = new UtenteDAO();
        if (ute != null && ute.isAdmin()) {
            String idUte=request.getParameter("id");
            if (idUte != null && !idUte.equals("")) {
                int id;
                try {
                    id = Integer.parseInt(idUte);

                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non valido");
                }

                List<Ordini> ordini = ordiniDAO.getRetrieveByUtente(id);
                request.setAttribute("ordiniUtenti", ordini);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ordiniUtente.jsp");
                dispatcher.forward(request, response);
            } else {
                String removeid = request.getParameter("remove-id");
                if (removeid != null && !removeid.equals("")) {
                    int utenteID;
                    try {
                        utenteID = Integer.parseInt(removeid);

                    } catch (NumberFormatException e) {
                        throw new MyServletException("Id prodotto non valido");
                    }
                    String rimuovi = request.getParameter("rimuovi");
                    if (rimuovi != null && !rimuovi.equals("")) {
                        Utente utente = utenteDAO.doRetrieveById(utenteID);
                        if (!utente.isAdmin()) {
                            utenteDAO.doDeleteUtente(utenteID);
                            request.setAttribute("messaggio", "Utente eliminato!");
                        } else {
                            throw new MyServletException("Impossibile rimuovere admin");
                        }
                    }

                    String promuovi = request.getParameter("promuovi");
                    if (promuovi != null && !promuovi.equals("")) {
                        Utente utente = utenteDAO.doRetrieveById(utenteID);
                        if (!utente.isAdmin()) {
                            utenteDAO.doUpdateUtente(utenteID, true);
                            request.setAttribute("messaggio", "Utente promosso!");
                        } else {
                            request.setAttribute("messaggio", "Utente già admin!");
                        }
                    }

                    String degrada = request.getParameter("degrada");
                    if (degrada != null && !degrada.equals("")) {
                        Utente utente = utenteDAO.doRetrieveById(utenteID);
                        if (utente.isAdmin()) {
                            utenteDAO.doUpdateUtente(utenteID, false);
                            request.setAttribute("messaggio", "Utente degradato!");
                        } else {
                            request.setAttribute("messaggio", "Utente non admin!");
                        }
                    }
                }



                List<Utente> utenti = utenteDAO.doRetrieveAll();
                request.setAttribute("utenti", utenti);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/adminutenti.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            throw new MyServletException("Utente non autorizzato");
        }

    }
    }

