package Model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Utente {

    private String username,password,email;
    private int id;
    private boolean admin;
    private String images;
    private List<Prodotto> desideri;
    private List<Prodotto> libreria;


    public Utente(String username,String password,String email){

        this.username=username;
        this.password=password;
        this.email=email;
        this.admin=false;
    }

    public Utente(){

    }

    public Utente(int id,String username,String password,String email){

        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.admin=false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin(){
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLibreria(List<Prodotto> libreria) {
        this.libreria = libreria;
    }

    public List<Prodotto> getLibreria() {
        return libreria;
    }

    public void setPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1,
                    digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Utente [id=" + id + ", username=" + username + ", passwordhash=" + password
                + ", email=" + email + ", admin=" + admin + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utente other = (Utente) obj;
        if (admin != other.admin)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }


}
