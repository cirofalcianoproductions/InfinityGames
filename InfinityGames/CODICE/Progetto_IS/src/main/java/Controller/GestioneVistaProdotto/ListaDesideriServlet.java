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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListaDesideri")
public class ListaDesideriServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente= (Utente)request.getSession().getAttribute("utente");
        UtenteDAO service = new UtenteDAO();
        if (utente != null) {
            if (request.getParameter("click") != null) {

                String rimuovi=request.getParameter("rimuovi");
                if (rimuovi != null && !rimuovi.equals("")) {
                    int id;
                    try {
                        id = Integer.parseInt(rimuovi);

                    } catch (NumberFormatException e) {
                        throw new MyServletException("Id prodotto non valido");
                    }
                    service.removeDesideri(utente.getId(), id);
                    ArrayList<Prodotto> list = (ArrayList<Prodotto>) request.getSession().getAttribute("desideri");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == id) {
                            list.remove(i);
                        }
                    }
                }

            } else {
                if (utente != null) {

                    List<Prodotto> lista = null;
                    try {
                        lista = service.getDesideri(utente.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    request.getSession().setAttribute("desideri", lista);
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/desideri.jsp");
            dispatcher.forward(request, response);
        }else {
                RequestDispatcher dispatcher= request.getRequestDispatcher("./login.jsp");
                dispatcher.forward(request,response);
        }
    }
}
