package Controller.GestioneProdotto;

import Controller.GestioneUtente.MyServletException;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@WebServlet("/AdminProdotto")
@MultipartConfig
public class AdminProdottoServlet extends HttpServlet {

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ProdottoDAO prod = new ProdottoDAO();

       //MyServletException.checkAdmin(request);

    String idstr = request.getParameter("id");
    if (idstr != null) {
        if (request.getParameter("rimuovi") != null) {
            prodottoDAO.doDelete(Integer.parseInt(idstr));
            request.setAttribute("notifica", "Prodotto rimosso con successo.");
        } else {
            Prodotto prodotto;
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            String prezzoCent = request.getParameter("prezzoCent");
            String sconto = request.getParameter("sconto");
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            Part fileVideo = request.getPart("video");

            if (nome != null  && descrizione != null && prezzoCent != null && sconto != null ) {
                // modifica/aggiunta prodotto
                prodotto = new Prodotto();
                if(nome.equals("")){
                    throw new MyServletException("Campo nome vuoto");
                }
                if(!(nome.matches("[a-zA-Z0-9 ]+"))){
                    throw new MyServletException("Formato nome non valido");
                }
                prodotto.setNome(nome);
                if(descrizione.equals("")){
                    throw new MyServletException("Campo descrizione vuoto");
                }
                if(!(descrizione.matches("[a-zA-Z ]+"))){
                    throw new MyServletException("Formato descrizione non valido");
                }
                prodotto.setDescrizione(descrizione);
                double scont;
                int prezz;


                try {
                    scont = Double.parseDouble(prezzoCent);
                    prezz = Integer.parseInt(sconto);
                    if (prezz < 0 || scont < 0) {
                        throw new MyServletException("sconto e/o prezzo non validi");
                    }
                    prodotto.setPrezzo(scont);
                    prodotto.setSconto(prezz);

                } catch (NumberFormatException e) {
                    throw new MyServletException("sconto e/o prezzo non validi");
                }


                if (filePart != null /*&& !filePart.equals("")*/) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    if (fileName != null && !fileName.equals("")) {
                        System.out.println("Nome file:" + fileName); //quando carichi una foto controlla che questo ti stampa il nome del
                        //file che hai caricato
                        filePart.write(getServletContext().getRealPath("") + File.separator + "images" + File.separator + "prodotti" + File.separator + fileName);
                        prodotto.setImages(fileName);
                    } else {
                        throw new MyServletException("Nessuna immagine selezionata");
                    }

                } /*else{
                    throw new MyServletException("Nessuna immagine selezionata");
                }*/

                if (fileVideo != null /*&& !fileVideo.equals("")*/) {
                    String fileName = Paths.get(fileVideo.getSubmittedFileName()).getFileName().toString();
                    if (fileName != null && !fileName.equals("")) {
                        System.out.println("Nome file:" + fileName); //quando carichi una foto controlla che questo ti stampa il nome del
                        //file che hai caricato
                        fileVideo.write(getServletContext().getRealPath("") + File.separator + "videos" + File.separator + fileName);
                        prodotto.setVideo(fileName);
                    } else {
                        throw new MyServletException("Nessun video selezionato");
                    }
                }
                /* else{
                    throw new MyServletException("Nessun video selezionato");
                } */

try {
    String[] categorie = request.getParameterValues("categorie");
    if (categorie != null && !categorie.equals("")) {
        prodotto.setCategorie(categorie != null ? Arrays.stream(categorie).map(id -> {
            Categoria c = new Categoria();
            c.setId(Integer.parseInt(id));
            return c;
        }).collect(Collectors.toList()) : Collections.emptyList());
    } else {
        throw new MyServletException("Nessuna categoria selezionata");
    }
}catch(NumberFormatException e){
    throw new MyServletException("Nessuna categoria selezionata");
}

                if (idstr.isEmpty()) { // aggiunta nuovo prodotto
                    if (prod.doRetrieveProdottoByNome(nome) != null) {
                        throw new MyServletException("Prodotto già esistente");
                    }
                    prodottoDAO.doSave(prodotto);
                    request.setAttribute("notifica", "Prodotto aggiunto con successo.");
                } else { // modifica prodotto esistente
                    prodotto.setId(Integer.parseInt(idstr));

                    if(prodottoDAO.doRetrieveById(Integer.parseInt(idstr))==null){
                        throw new MyServletException("id prodotto non valido");
                    }

                    prodottoDAO.doUpdate(prodotto);
                    request.setAttribute("notifica", "Prodotto modificato con successo.");
                }



            } else {



                  /*  try {
                    if (prod.doRetrieveProdottoByNome(nome) != null) {
                        throw new MyServletException("Prodotto già esistente");
                    }*/


                  /*  }catch(MyServletException e ){
                        request.setAttribute("notifica","Prodotto già esistente");

                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminprodotto.jsp");
                        requestDispatcher.forward(request, response);
                    } */


                int id;

                try {
                    id = Integer.parseInt(idstr);

                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non valido");
                }

                prodotto = prodottoDAO.doRetrieveById(id);
            }

            request.setAttribute("prodotto", prodotto);
        }
    }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request, response);

    }
}
