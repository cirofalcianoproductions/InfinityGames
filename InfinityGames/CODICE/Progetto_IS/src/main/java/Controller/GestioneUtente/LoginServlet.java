package Controller.GestioneUtente;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    //private final LoginDAO loginDAO = new LoginDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    if (request.getParameter("login") != null) {
        UtenteDAO service = new UtenteDAO();
        Utente utente = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && password != null) {
            utente = service.doRetrieveByUsernamePassword(username, password);
        }

       /* if (utente == null) {
           request.getSession().setAttribute("notifica","Username e/o password non validi");
        }*/


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
            /*    Login login = new Login();
                login.setIdutente(utente.getId());
                login.setToken(UUID.randomUUID().toString());
                login.setTime(Timestamp.from(Instant.now()));

                loginDAO.doSave(login);

                Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
                cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
                response.addCookie(cookie);
*/
        }

        if (utente == null) {

          // try {
               // request.getSession().setAttribute("messaggio", "Username e/o password non validi");
                throw new MyServletException("Username e/o password non validi");

          /*}catch(MyServletException e){
                e.printStackTrace();
                response.sendRedirect("./login.jsp");
            }*/


            //request.getSession().setAttribute("messaggio", "Username e/o password non validi");
            // throw new MyServletException("Username e/o password non validi");

        } else {
            String dest = request.getHeader("referer");
            if (dest == null || dest.contains("/login.jsp") || dest.trim().isEmpty()) {
                dest = ".";
                response.sendRedirect("/InfinityGames/");
            } else {
                response.sendRedirect(dest);
            }
        }

        request.getSession().setAttribute("utente", utente);


    } else {
        response.sendRedirect("./login.jsp");
    }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}


