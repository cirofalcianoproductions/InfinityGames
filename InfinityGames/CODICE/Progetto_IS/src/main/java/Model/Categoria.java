package Model;


public class Categoria {

    private int id;
    private String nome,descrizione;

    public Categoria(){

    }

    public Categoria(int id, String nome, String descrizione){
        this.id=id;
        this.nome=nome;
        this.descrizione=descrizione;
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
