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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/Recenti")
public class RecentiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente=(Utente)request.getSession().getAttribute("utente");

        if (utente != null) {
            UtenteDAO service = new UtenteDAO();


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

            int totaleprodotti = service.countByRecentiId(utente.getId());
            int npag = (totaleprodotti + perpag - 1) / perpag;
            request.setAttribute("npag", npag);

            List<Prodotto> lista = service.doRetrieveByRecenti(utente.getId(), (pag - 1) * perpag, perpag);

            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);


            for (int i = 0; i < lista.size(); i++) {
                try {
                    DateFormat formatter;
                    Date date = null;
                    formatter = new SimpleDateFormat("dd-MMM-yy");
                    date = (Date) formatter.parse(lista.get(i).getData());
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(date);

                    if (calendar2.get(Calendar.MONTH) != calendar.get(Calendar.MONTH)) {
                        lista.remove(i);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            request.setAttribute("recenti", lista);

            RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/results/recenti.jsp");
            dispatcher.forward(request,response);

        }else{
            RequestDispatcher dispatcher= request.getRequestDispatcher("./login.jsp");
            dispatcher.forward(request,response);
        }



    }
}
