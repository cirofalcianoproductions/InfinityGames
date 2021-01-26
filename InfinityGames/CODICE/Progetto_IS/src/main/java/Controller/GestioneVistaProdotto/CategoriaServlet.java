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
import java.util.List;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdottoDAO service = new ProdottoDAO();

        List<Categoria> categorie = (List<Categoria>) getServletContext().getAttribute("categorie");
        String idcat = request.getParameter("id");
        if (idcat != null && !idcat.equals("")) {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));

            } catch (NumberFormatException e) {
                throw new MyServletException("Id categoria non valido");
            }

            int flag=0;
            for (Categoria c: categorie){
                if (c.getId()==id){
                    flag=1;
                    break;
                }
            }
            if (flag==0){
                throw new MyServletException("Nessuna Categoria trovata");
            }







            request.setAttribute("categoria", categorie.stream().filter(c -> c.getId() == id).findAny().get());

            String pagstr = request.getParameter("pag");
            int pag;
            if (pagstr == null || pagstr.equals("")) {
                pag = 1;
            } else {
                pag = Integer.parseInt(pagstr);
            }
            request.setAttribute("pag", pag);

            int perpag = 10;

            int totaleprodotti = service.countByCategoria(id);
            int npag = (totaleprodotti + perpag - 1) / perpag;
            request.setAttribute("npag", npag);

            String ordstr = request.getParameter("ord");

            ProdottoDAO.OrderBy ord;
            if (ordstr != null && (ordstr.equals("DEFAULT") || ordstr.equals("PREZZO_ASC") || ordstr.equals("PREZZO_DESC"))) {
                ord = ordstr == null ? ProdottoDAO.OrderBy.DEFAULT : ProdottoDAO.OrderBy.valueOf(ordstr);
            } else {
                ord = ProdottoDAO.OrderBy.DEFAULT;
            }
            request.setAttribute("ord", ord);

            List<Prodotto> prodotti = service.doRetrieveByIdCat(id, ord, (pag - 1) * perpag, perpag);
            request.setAttribute("prodotti", prodotti);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/categoria.jsp");
            dispatcher.forward(request, response);
        } else {
            throw new MyServletException("Categoria non selezionata");
        }
    }
}
