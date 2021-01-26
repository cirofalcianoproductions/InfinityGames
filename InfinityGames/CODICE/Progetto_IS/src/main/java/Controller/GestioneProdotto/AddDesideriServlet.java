package Controller.GestioneProdotto;


import Controller.GestioneUtente.MyServletException;
import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AddDesideri")
public class AddDesideriServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/xml");

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente != null) {
            UtenteDAO service = new UtenteDAO();

            String prod= request.getParameter("prodId");
            if (prod != null && !prod.equals("")) {
                int idPro;
                try {
                    idPro = Integer.parseInt(prod);

                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non valido");
                }

                List<Prodotto> list = null;

                try {
                    list = service.getDesideri(utente.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                boolean flag = true;
                if (list.size() == 0) {

                    service.addDesideri(utente.getId(), idPro);
                    response.getWriter().append("<ok/>");
                } else {

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() != idPro) {

                            flag = true;

                        } else {
                            flag = false;
                            break;

                        }
                    }
                    if (flag == true) {
                        service.addDesideri(utente.getId(), idPro);
                        response.getWriter().append("<ok/>");
                    } else {
                        response.getWriter().append("<no/>");
                    }
                }

            }else {
                throw new MyServletException("Nessun prodotto selezionato");
            }

        } else {
            response.sendRedirect("./login.jsp");
        }
    }
}
