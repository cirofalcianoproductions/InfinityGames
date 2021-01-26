package Controller.GestioneUtente;

import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.*;

class ModificaProfiloServletTest extends ModificaProfiloServlet {


    ModificaProfiloServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new ModificaProfiloServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByUsername("utente1");

        request.getSession().setAttribute("utente",utente);


    }

  /*  @Test //non viene inserito nessun parametro
    void nessunInserimentoPassword(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password precedente sbagliata", exception.getMessage());

    } */

    @Test //la password precedente è sbagliata
    void TC_GU5_01(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1234");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password precedente sbagliata", exception.getMessage());

    }
    @Test //lunghezzza password nuova non valida
    void TC_GU5_02(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1");
        request.addParameter("password","pass");
        request.addParameter("passwordConferma","");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password non valida.", exception.getMessage());

    }

    @Test //formato password nuova non valido
    void TC_GU5_03(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1");
        request.addParameter("password","password");
        request.addParameter("passwordConferma","");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password non valida.", exception.getMessage());

    }

    @Test //conferma password valida
    void TC_GU5_04(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1");
        request.addParameter("password","Password2");
        request.addParameter("passwordConferma","jbaas");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password e conferma differenti.", exception.getMessage());

    }

 /*   @Test //conferma password non valida
    void confermaPasswordNotValid(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1");
        request.addParameter("password","Password2");
        request.addParameter("passwordConferma","Password1");


        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password e conferma differenti.", exception.getMessage());

    } */

    @Test //aggiornamento password con successo
    void TC_GU5_05(){


        request.addParameter("salvaModifiche","Salva");
        request.addParameter("passwordPrecedente","Password1");
        request.addParameter("password","Password2");
        request.addParameter("passwordConferma","Password2");


       assertDoesNotThrow(()->{servlet.doGet(request,response);});

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByUsername("utente1");
        utente.setPassword("Password1");
        utenteDAO.setNewPassword(utente);


    }





}