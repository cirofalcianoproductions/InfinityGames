package Model;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UtenteDAO {

    public static void doSaveImages(String fileName, int id) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("UPDATE Utente SET images=? WHERE id=?");
                ps.setString(1, fileName);
                ps.setInt(2, id);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("UPDATE error.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public static void setNuovaPassword(Utente utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE Utente SET passwordhash=? WHERE id=?");
            ps.setString(1,utente.getPassword());
            ps.setInt(2,utente.getId());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setNewPassword(Utente utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE Utente SET passwordhash=? WHERE id=?");
            ps.setString(1,utente.getPassword());
            ps.setInt(2,utente.getId());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setModifica(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE Utente SET username=?,passwordhash=?,email=? WHERE id=?");
            ps.setString(1,utente.getUsername());
            ps.setString(2,utente.getPassword());
            ps.setString(3,utente.getEmail());
            ps.setInt(4,utente.getId());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void doUpdateUtente(int uteID,boolean flag) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps;
            if (flag == true) {
                ps = con.prepareStatement("UPDATE Utente SET adminn=true WHERE id=?");
            }else {
                ps= con.prepareStatement("UPDATE Utente SET adminn=false WHERE id=?");
            }
            ps.setInt(1,uteID);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void addDesideri(int id, int idPro) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO desideri(idUtente,idProdotto) VALUES (?,?);");
            ps.setInt(1,id);
            ps.setInt(2,idPro);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> getLibreria(int idUtente)  {
        try(Connection con= ConPool.getConnection()){

            PreparedStatement ps=
                    con.prepareStatement("SELECT idProdotto,nome,descrizione,images FROM libreria WHERE idUtente=?");
            ps.setInt(1,idUtente);
            ArrayList<Prodotto> lista= new ArrayList<>();
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Prodotto p= new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setImages(rs.getString(4));
                lista.add(p);
            }
            return lista;
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernamePassword(String username, String password){


        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,username,passwordhash,email,adminn,images "+" " + "FROM Utente " +" "+
                            "WHERE username=? AND passwordhash=SHA1(?)");
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Utente p = new Utente();
               p.setId(rs.getInt(1));
               p.setUsername(rs.getString(2));
               p.setPassword(rs.getString(3));
               p.setEmail(rs.getString(4));
               p.setAdmin(rs.getBoolean(5));
               p.setImages(rs.getString(6));
               p.setLibreria(getLibreria(rs.getInt(1)));
              return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Utente>  doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT id, username, passwordhash,  email, adminn FROM utente ");

            ArrayList<Utente> utenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setAdmin(rs.getBoolean(5));
                utenti.add(u);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, email, adminn FROM utente WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setAdmin(rs.getBoolean(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Userregistration(Utente utente){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (username,passwordhash,email,adminn) VALUES(?,?,?,?)"
                                , Statement.RETURN_GENERATED_KEYS                   // questo codice è utile solo se si vuole restituire il bean customer completo di id
            );

            String id = UUID.randomUUID().toString();

            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getEmail());
            ps.setBoolean(4,utente.isAdmin());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            utente.setId(rs.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Utente doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, email, adminn,images FROM utente WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setAdmin(rs.getBoolean(5));
                p.setImages(rs.getString(6));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> getDesideri(int idU) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
        PreparedStatement ps = con.prepareStatement(
                "SELECT id, nome, descrizione,prezzo, sconto,images FROM Prodotto LEFT JOIN desideri ON id=idProdotto WHERE idUtente=?");
        ps.setInt(1, idU);
        ArrayList<Prodotto> desideri = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Prodotto c = new Prodotto();
            c.setId(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDescrizione(rs.getString(3));
            c.setPrezzo(rs.getDouble(4));
            c.setSconto(rs.getInt(5));
            c.setImages(rs.getString(6));
            desideri.add(c);
        }
        return desideri;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeDesideri(int id, int idPro) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM desideri WHERE idUtente=? AND idProdotto=?");
            ps.setInt(1, id);
            ps.setInt(2,idPro);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> getRecenti(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "select id, nome , descrizione, prezzo,data_vis, sconto from Prodotto join utenteprodotti on id=pid where uid=?;");
            ps.setInt(1, id);
            ArrayList<Prodotto> recenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto c = new Prodotto();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                c.setPrezzo(rs.getDouble(4));
                c.setData(rs.getString(5));
                c.setSconto(rs.getInt(6));
                recenti.add(c);
            }
            return recenti;
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByRecentiId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT COUNT(*) FROM Prodotto join utenteprodotti on id=pid where uid=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Prodotto> doRetrieveByRecenti(int id,int i, int perpag) {
        ProdottoDAO service= new ProdottoDAO();
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("select id, nome , descrizione, prezzo,data_vis,sconto,images from Prodotto join utenteprodotti on id=pid where uid=?  LIMIT ?, ?");
            ps.setInt(1, id);
            ps.setInt(2, i);
            ps.setInt(3, perpag);
            ArrayList<Prodotto> recenti = new ArrayList<Prodotto>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto c = new Prodotto();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                c.setPrezzo(rs.getDouble(4));
                c.setData(rs.getString(5));
                c.setSconto(rs.getInt(6));
                c.setImages(rs.getString(7));
                c.setCategorie(service.getCategorie(con, c.getId()));
                recenti.add(c);

            }

            return recenti;
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void doDeleteUtente(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("DELETE  FROM Utente WHERE id=?");
            ps.setInt(1, id);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, email, adminn FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setAdmin(rs.getBoolean(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
