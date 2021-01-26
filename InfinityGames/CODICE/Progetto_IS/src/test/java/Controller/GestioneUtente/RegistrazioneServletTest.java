package Controller.GestioneUtente;

import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class RegistrazioneServletTest extends RegistrazioneServlet {

    RegistrazioneServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;


    @BeforeEach
    void setUp() {
        servlet = new RegistrazioneServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

   /* @Test  //nessun parametro inserito
    void parametriVuoti() {
        request.addParameter("username","");
        request.addParameter("email","");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Username non valido.", exception.getMessage());
    }*/
    @Test  //lunghezza username non valida
    void TC_GU1_01() {
        request.addParameter("username","genn");
        request.addParameter("email","");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Username non valido.", exception.getMessage());
    }

    @Test   //username formato non valido
    void TC_GU1_02(){
        request.addParameter("username","1&&");
        request.addParameter("email","");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Username non valido.", exception.getMessage());
    }


  @Test  //username già esistente
    void TC_GU1_06(){
        request.addParameter("username","utente1");
        request.addParameter("email","utente@gmail.com");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","Password1");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Username non valido.", exception.getMessage());
    }


  /*  @Test //password non inserita
    void passwordNull(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password non valida.", exception.getMessage());
    } */

    @Test  //formato password non valido
    void TC_GU1_04(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","12ab&baca");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password non valida.", exception.getMessage());
    }

    @Test  //password inferiore a 8 caratteri
    void TC_GU1_03(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","Passwo1");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password non valida.", exception.getMessage());
    }



   /* @Test //conferma password non inserita
    void passwordConfermaNull(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password e conferma differenti.", exception.getMessage());
    } */

  /*  @Test  //conferma password non valida
    void passwordConfermaErrorFormat(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","Passw");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Password e conferma differenti.", exception.getMessage());
    } */

 /*   @Test  //email non inserita
    void emailNull(){
        request.addParameter("username","Pascann");
        request.addParameter("email","");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","Password1");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Email non valida.", exception.getMessage());
    } */

    @Test  //email non valida
    void TC_GU1_05(){
        request.addParameter("username","Pascann");
        request.addParameter("email","coseacaso@acaso");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","Password1");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doGet(request,response);});
        assertEquals("Email non valida.", exception.getMessage());
    }

    @Test //utente registrato con successo
    void TC_GU1_07(){

        request.addParameter("username","Pascann");
        request.addParameter("email","pascann@acaso.it");
        request.addParameter("password","Password1");
        request.addParameter("passwordConferma","Password1");

        assertDoesNotThrow(()->{servlet.doGet(request,response);});


        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByUsername("Pascann");
        utenteDAO.doDeleteUtente(utente.getId());
    }
}