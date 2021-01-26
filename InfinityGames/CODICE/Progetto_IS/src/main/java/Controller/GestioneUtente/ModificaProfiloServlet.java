package Controller.GestioneUtente;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/Modifica")
@MultipartConfig
public class ModificaProfiloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente= (Utente)request.getSession().getAttribute("utente");
        UtenteDAO utenteDAO = new UtenteDAO();


        if (utente != null) {
            if (request.getParameter("salvaModifiche") != null) {

                String passwordPrecedente= request.getParameter("passwordPrecedente");

                if(utenteDAO.doRetrieveByUsernamePassword(utente.getUsername(),passwordPrecedente)==null){

                    throw new MyServletException("Password precedente sbagliata");
                }




                String password = request.getParameter("password");

                if(!password.equals("")) {
                    if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                            && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
                        throw new MyServletException("Password non valida.");
                    }

                    String passwordConferma = request.getParameter("passwordConferma");

                    if (!password.equals(passwordConferma)) {
                         throw new MyServletException("Password e conferma differenti.");
                    } else {
                        if (password != null) {
                            utente.setPassword(password);
                            UtenteDAO.setNuovaPassword(utente);
                            request.getSession().setAttribute("modificato","utente modificato");
                        }
                    }
                }


            }
            if (request.getParameter("Aggiorna") != null) {
                Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
                String fileName = "";
                if (filePart != null) {
                   fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    if (!fileName.equals("")) {
                        System.out.println("Nome file:" + fileName); //quando carichi una foto controlla che questo ti stampa il nome del
                        //file che hai caricato

                        System.out.println(fileName);
                        filePart.write(getServletContext().getRealPath("") + File.separator + "images" + File.separator + "utenti" + File.separator + fileName);
                        utente.setImages(fileName);
                        UtenteDAO.doSaveImages(fileName, utente.getId());
                        request.getSession().setAttribute("modificato","immagine modificata");
                    }else {
                        request.getSession().setAttribute("modificato","nessuna immagine selezionata");
                    }
                }

            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/modificaProfilo.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
