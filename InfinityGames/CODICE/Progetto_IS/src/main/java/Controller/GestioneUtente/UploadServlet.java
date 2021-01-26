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

@WebServlet("/Upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente= (Utente)request.getSession().getAttribute("utente");
        if (utente != null) {

            Part filePartUtente = request.getPart("img"); // Retrieves <input type="file" name="file">
            if (filePartUtente != null) {
                String fileName = Paths.get(filePartUtente.getSubmittedFileName()).getFileName().toString();
                System.out.println("Nome file:" + fileName); //quando carichi una foto controlla che questo ti stampa il nome del
                //file che hai caricato

                filePartUtente.write(getServletContext().getRealPath("") + File.separator + "images" + File.separator + "utenti" + File.separator + fileName);

                UtenteDAO.doSaveImages(fileName, utente.getId());
            }
        }else {
            RequestDispatcher dispatcher=request.getRequestDispatcher("./login.jsp");
            dispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
