package Model;

import java.util.ArrayList;
import java.util.List;

public class Ordini {

        private int id;
        private String data;
        private double totale;
        private int Utente;
        private List<Carrello.ProdottoQuantita> prodotti;

        public Ordini(){

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public double getTotale() {
        return totale;
    }

    public int getUtente() {
            return Utente;
        }


         public void setUtente(int utente) {
            this.Utente = utente;
         }

         public List<Carrello.ProdottoQuantita> getProdotti() {
            return prodotti;
         }

         public void setProdotti(List<Carrello.ProdottoQuantita> prodotti) {
            this.prodotti = prodotti;
         }
}
