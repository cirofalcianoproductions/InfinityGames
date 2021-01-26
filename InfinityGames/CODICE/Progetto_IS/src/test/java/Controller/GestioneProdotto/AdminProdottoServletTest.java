package Controller.GestioneProdotto;

import Controller.GestioneUtente.MyServletException;
import Controller.GestioneUtente.RegistrazioneServlet;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Utente;
import Model.UtenteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class AdminProdottoServletTest extends AdminProdottoServlet {

    AdminProdottoServlet servlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;


    @BeforeEach
    void setUp() {
        servlet = new AdminProdottoServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }



                                          /*   AGGIUNTA PRODOTTO  */

   /* @Test //nessun parametro inserito nel prodotto
    void parametriVuoti() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","");
        request.addParameter("descrizione","");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Campo nome vuoto", exception.getMessage());
    } */

    @Test //formato nome non valido
    void TC_GP4_01() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","&&!!");
        request.addParameter("descrizione","");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Formato nome non valido", exception.getMessage());
    }

  /*  @Test // campo descrizione vuoto
    void eccezioneDescrizioneNull() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Campo descrizione vuoto", exception.getMessage());
    } */

    @Test // formato decrizione non valido
    void TC_GP4_03() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","01001");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Formato descrizione non valido", exception.getMessage());
    }

 /*   @Test // prezzo e sconto vuoti
    void eccezionePrezzoScontoNull() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("sconto e/o prezzo non validi", exception.getMessage());
    } */

    @Test // prezzo negativo, sconto positivo o sconto == 0
    void TC_GP4_04() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","-1");
        request.addParameter("sconto","2");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("sconto e/o prezzo non validi", exception.getMessage());
    }

    @Test // prezzo positivo, sconto negativo
    void TC_GP4_05() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","-1");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("sconto e/o prezzo non validi", exception.getMessage());
    }

    @Test // nessuna categoria selezionata
    void TC_GP4_06() {

        request.addParameter("id","");
        request.addParameter("categorie","");
        request.addParameter("nome","gioco");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","0");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Nessuna categoria selezionata", exception.getMessage());
    }

    @Test //prodotto già presente nella piattaforma
    void TC_GP4_02() {

        request.addParameter("id","");
        request.addParameter("categorie","1");
        request.addParameter("nome","Fifa20");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","0");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Prodotto già esistente", exception.getMessage());
    }


    @Test // aggiunta prodotto con successo
    void TC_GP4_07() {

        request.addParameter("id","");
        request.addParameter("categorie","1");
        request.addParameter("nome","giocoAggiunto");
        request.addParameter("descrizione","gioco di calcio");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","0");
        request.addParameter("file","");
        request.addParameter("video","");

        assertDoesNotThrow(()->{servlet.doGet(request,response);});

        ProdottoDAO prodottoDAO= new ProdottoDAO();
        prodottoDAO.doDeleteByNomeForTesting("giocoAggiunto");

    }



                          /*    MODIFICA PRODOTTO  */




 /*   @Test //nessun parametro inserito nel prodotto da modificare
    void parametriVuotiModifica() {

        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","");
        request.addParameter("descrizione","");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Campo nome vuoto", exception.getMessage());
    } */
 @Test //id prodotto non esistente
 void TC_GP5_01() {

     request.addParameter("id","150");
     request.addParameter("categorie","1");
     request.addParameter("nome","Pes20");
     request.addParameter("descrizione","adawd");
     request.addParameter("prezzoCent","10");
     request.addParameter("sconto","0");
     request.addParameter("file","");
     request.addParameter("video","");

     MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
     assertEquals("id prodotto non valido", exception.getMessage());
 }

    @Test // formato nome non valido
    void TC_GP5_02() {

        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","!!&&");
        request.addParameter("descrizione","");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Formato nome non valido", exception.getMessage());
    }

    @Test // formato descrizione non valido
    void TC_GP5_03() {

        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","Pes20");
        request.addParameter("descrizione","01010");
        request.addParameter("prezzoCent","");
        request.addParameter("sconto","");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Formato descrizione non valido", exception.getMessage());
    }

    @Test //prezzo negativo
    void TC_GP5_04(){
        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","Pes20");
        request.addParameter("descrizione","Gioco di calcio");
        request.addParameter("prezzoCent","-1");
        request.addParameter("sconto","0");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("sconto e/o prezzo non validi", exception.getMessage());
    }

    @Test //sconto negativo
    void TC_GP5_05(){
        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","Pes20");
        request.addParameter("descrizione","Gioco di calcio");
        request.addParameter("prezzoCent","-1");
        request.addParameter("sconto","0");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("sconto e/o prezzo non validi", exception.getMessage());
    }

    @Test //nessuna categoria assegnata al prodotto modificato
    void TC_GP5_06(){
        request.addParameter("id","4");
        request.addParameter("categorie","");
        request.addParameter("nome","Pes20");
        request.addParameter("descrizione","Gioco di calcio");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","5");
        request.addParameter("file","");
        request.addParameter("video","");

        MyServletException exception = assertThrows(MyServletException.class, ()->{servlet.doPost(request,response);});
        assertEquals("Nessuna categoria selezionata", exception.getMessage());
    }


    @Test //prodotto modificato con successo
    void TC_GP5_07(){
        request.addParameter("id","16");
        request.addParameter("categorie","3");
        request.addParameter("nome","Borderlands");
        request.addParameter("descrizione","GDR");
        request.addParameter("prezzoCent","20");
        request.addParameter("sconto","5");
        request.addParameter("file","");
        request.addParameter("video","");

        assertDoesNotThrow(()->{servlet.doGet(request,response);});

    }



}