package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Prodotto {

    private int id;
    private String nome,descrizione;
    private double prezzo;
    private int quant_vend;
    private List<Categoria> categorie;
    private int sconto;
    private String data;
    private int visual;
    private String images;
    private String video;

    public Prodotto(){

    }

    public Prodotto(int id, String nome, String descrizione, double prezzo){
     this.id=id;
     this.nome=nome;
     this.descrizione=descrizione;
     this.prezzo=prezzo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzo() {

        return prezzo;
    }
    public String getPrezzoEuro() {
        return String.format("%.2f", prezzo / 100.);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(double prezzo) {

        this.prezzo = prezzo;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    public int getQuant_vend() {
        return quant_vend;
    }

    public void setQuant_vend(int quant_vend) {
        this.quant_vend = quant_vend;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public int getVisual() {
        return visual;
    }

    public void setVisual(int visual) {
        this.visual = visual;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) throws ParseException {
        SimpleDateFormat formatter2=new SimpleDateFormat("dd MMMMMMMM yyyy");
        Date date=formatter2.parse(data);
        String date1=formatter2.format(date);
        this.data = date1;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
