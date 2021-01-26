package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class Carrello {
    public static class ProdottoQuantita  {
        private Prodotto prodotto;
        private int quantita;


        public ProdottoQuantita(Prodotto prodotto, int quantita) {
            this.prodotto = prodotto;
            this.quantita = quantita;
        }
        public ProdottoQuantita(){

        }

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

        public Prodotto getProdotto() {
            return prodotto;
        }

        public double getPrezzoTotCent() {
            if (prodotto.getSconto()>0) {
                Double prezzo_sco=prodotto.getPrezzo() - ((prodotto.getSconto() * prodotto.getPrezzo())/100);
                return ( quantita *  prezzo_sco);
            }else {
                return (quantita * prodotto.getPrezzo());
            }
        }


        public String getPrezzoTotEuro() {
                return String.format("%.2f", quantita * prodotto.getPrezzo() / 100.);
        }
    }

    private int idUtente;

    public void setProdotti(LinkedHashMap<Integer, ProdottoQuantita> prodotti) {
        this.prodotti = prodotti;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    private LinkedHashMap<Integer, ProdottoQuantita> prodotti = new LinkedHashMap<>();

    public Collection<ProdottoQuantita> getProdotti() {
        return prodotti.values();
    }

    public ProdottoQuantita get(int prodId) {
        return prodotti.get(prodId);
    }

    public void put(Prodotto prodotto, int quantita) {
        prodotti.put(prodotto.getId(), new ProdottoQuantita(prodotto, quantita));
    }

    public ProdottoQuantita remove(int prodId) {
        return prodotti.remove(prodId);
    }

    public double getPrezzoTotCent() {
        return  prodotti.values().stream().mapToDouble(p -> p.getPrezzoTotCent()).sum();
    }

    public String getPrezzoTotEuro() {

        return String.format("%.2f", getPrezzoTotCent() );
    }

    @Override
    public String toString() {
        return "Carrello [prodotti=" + prodotti + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((prodotti == null) ? 0 : prodotti.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carrello other = (Carrello) obj;
        if (prodotti == null) {
            if (other.prodotti != null)
                return false;
        } else if (!prodotti.equals(other.prodotti))
            return false;
        return true;
    }
}
