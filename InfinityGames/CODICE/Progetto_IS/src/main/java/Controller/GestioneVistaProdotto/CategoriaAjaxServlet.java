package Controller.GestioneVistaProdotto;

import Controller.GestioneUtente.MyServletException;
import Model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaAjax")
public class CategoriaAjaxServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdottoDAO prodottoDAO= new ProdottoDAO();
        JSONArray prodJson = new JSONArray();
        String ricerca = request.getParameter("ricerca");
        String id = request.getParameter("id");
        String pagstr = request.getParameter("pag");

        List<Categoria> categorie = (List<Categoria>) getServletContext().getAttribute("categorie");




        if (ricerca != null && !ricerca.equals("") && id!= null && !id.equals("")) {
            int parametro;
            int idCat;
            try {
                parametro = Integer.parseInt(ricerca);
                idCat = Integer.parseInt(id);

            } catch (NumberFormatException e) {
                throw new MyServletException("Parametro non valido");
            }

            int flag=0;
            for (Categoria c: categorie){
                if (c.getId()==idCat){
                    flag=1;
                    break;
                }
            }
            if (flag==0){
                throw new MyServletException("Nessuna Categoria trovata");
            }

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

            int totaleprodotti = prodottoDAO.countByCategoria(idCat);
            int npag = (totaleprodotti + perpag - 1) / perpag;
            request.setAttribute("npag", npag);

            String ordstr = request.getParameter("ord");
            ProdottoDAO.OrderBy ord;
            if (ordstr != null && (ordstr.equals("DEFAULT") || ordstr.equals("PREZZO_ASC") || ordstr.equals("PREZZO_DESC"))) {
                ord = ordstr == null ? ProdottoDAO.OrderBy.DEFAULT : ProdottoDAO.OrderBy.valueOf(ordstr);
            }else{
                ord= ProdottoDAO.OrderBy.DEFAULT;
            }
            request.setAttribute("ord", ord);


            List<Prodotto> prodotti = null;
            if (parametro == 2) {
                prodotti = prodottoDAO.doRetrieveByPopolareForCat(idCat, ord, (pag - 1) * perpag, perpag);
            } else if (parametro == 1 || parametro == 3) {
                prodotti = prodottoDAO.doRetrieveByIdCat(idCat, ord, (pag - 1) * perpag, perpag);
                if (parametro == 3) {
                    prodotti.removeIf(p -> p.getQuant_vend() < 10);
                }
            }
            for (Prodotto p : prodotti) {
                JSONArray obj2 = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("Prod", p.getId());
                obj.put("nome", p.getNome());
                obj.put("descrizione", p.getDescrizione());
                obj.put("prezzo", p.getPrezzo());
                obj.put("sconto", p.getSconto());
                obj.put("imagesProd", p.getImages());
                for (Categoria c: p.getCategorie()){
                    JSONObject objCat= new JSONObject();
                    objCat.put("idCat",c.getId());
                    objCat.put("nome", c.getNome());
                    obj2.put(objCat);
                }
                obj.put("categoria",obj2);
                prodJson.put(obj);
            }
            response.setContentType("application/json");
            response.getWriter().append(prodJson.toString());
        }else {
            throw new MyServletException("Nessun parametro");
        }


    }
}


