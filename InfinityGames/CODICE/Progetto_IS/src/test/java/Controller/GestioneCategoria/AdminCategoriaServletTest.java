package Controller.GestioneCategoria;

import Controller.GestioneProdotto.AdminProdottoServlet;
import Controller.GestioneUtente.InitServlet;
import Controller.GestioneUtente.MyServletException;
import Model.Categoria;
import Model.CategoriaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminCategoriaServletTest extends AdminCategoriaServlet {



    AdminCategoriaServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockServletConfig config;

    @BeforeEach
    void setUp() throws ServletException {

        servlet = new AdminCategoriaServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        config = new MockServletConfig();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> categorie = categoriaDAO.doRetrieveAll();

        config.getServletContext().setAttribute("categorie",categorie);

        servlet.init(config);
    }

                                           /*    AGGIUNTA CATEGORIA  */


    @Test
    void TC_GC1_01(){ //formato nome non valido

        request.addParameter("id","");
        request.addParameter("nome","01&!");
        request.addParameter("descrizione","ascasc");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Formato nome non valido", exception.getMessage());
    }
    @Test
    void TC_GC1_02(){ //formato descrizione non valido

        request.addParameter("id","");
        request.addParameter("nome","Arcade");
        request.addParameter("descrizione","giochi123 & !!");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Formato descrizione non valido", exception.getMessage());
    }
    @Test
    void TC_GC1_03(){ //nome categoria già esistente

        request.addParameter("id","");
        request.addParameter("nome","Sport");
        request.addParameter("descrizione","il salto della quaglia");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("nome categoria già esistente", exception.getMessage());
    }


    @Test
    void TC_GC1_04() { //categoria aggiunta con successo

        request.addParameter("id","");
        request.addParameter("nome","Arcade");
        request.addParameter("descrizione","giochi vintage");

        assertDoesNotThrow(()->{servlet.doGet(request,response);});

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.doDeleteByNome("Arcade");
    }


                                                 /*    MODIFICA CATEGORIA  */


    @Test
    void TC_GC2_01(){ //id categoria da modificare non valido

        request.addParameter("id","10");
        request.addParameter("nome","");
        request.addParameter("descrizione","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("id categoria non valido", exception.getMessage());
    }





    @Test
    void TC_GC2_02(){ //formato nome non valido

        request.addParameter("id","6");
        request.addParameter("nome","01&!");
        request.addParameter("descrizione","dasdsadsa");



        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Formato nome non valido", exception.getMessage());
    }

    @Test
    void TC_GC2_03(){ //formato descrizione non valido

        request.addParameter("id","6");
        request.addParameter("nome","Quiz");
        request.addParameter("descrizione","giochi123 & !!");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Formato descrizione non valido", exception.getMessage());
    }

    @Test
    void TC_GC2_04() { //categoria modificata con successo

        request.addParameter("id","6");
        request.addParameter("nome","Quiz");
        request.addParameter("descrizione","Rispondere a domande");

        assertDoesNotThrow(()->{servlet.doGet(request,response);});

    }



}