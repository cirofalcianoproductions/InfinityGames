package Controller.GestioneUtente;

import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class LoginServletTest extends LoginServlet {

    LoginServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new LoginServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

   @Test  //nessun parametro inserito
    void TC_GU2_01() {
        request.addParameter("login", "Login");
        request.addParameter("username","");
        request.addParameter("password","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Username e/o password non validi", exception.getMessage());
    }

    @Test  //viene inserito solo il parametro username
    void TC_GU2_02() {
        request.addParameter("login", "Login");
        request.addParameter("username","utente1");
        request.addParameter("password","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Username e/o password non validi", exception.getMessage());
    }

   /* @Test  //viene inserito solo il parametro password
    void parametroUsernameNotSend() {
        request.addParameter("login", "Login");
        request.addParameter("username","");
        request.addParameter("password","Password1");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Username e/o password non validi", exception.getMessage());
    } */
   /* @Test  //i parametri inseriti hanno un formato non valido
    void parametriFormatError() {
        request.addParameter("login", "Login");
        request.addParameter("username","user");
        request.addParameter("password","password");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Username e/o password non validi", exception.getMessage());
    } */

   /* @Test  //i parametri inseriti non trovano alcun utente registrato
    void TC_GU2_03() {
        request.addParameter("login", "Login");
        request.addParameter("username","utente123");
        request.addParameter("password","Password123");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Username e/o password non validi", exception.getMessage());
    } */

    @Test  //utente registrato -> autenticazione riuscita
    void TC_GU2_03() {
        request.addParameter("login", "Login");
        request.addParameter("username","utente1");
        request.addParameter("password","Password1");

        assertDoesNotThrow(()->{servlet.doPost(request,response);});
    }



}