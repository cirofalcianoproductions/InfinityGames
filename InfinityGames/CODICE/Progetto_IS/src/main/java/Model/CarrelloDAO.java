package Model;

import java.sql.*;
import java.util.LinkedHashMap;

public class CarrelloDAO {
    public static void doDeleteAll(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM carrello WHERE idUtente=?");
            ps.setInt(1, carrello.getIdUtente());
            if (ps.executeUpdate() == 0) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeProdById(Carrello carrello, int prodId) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM carrello WHERE idProdotto=? AND idUtente=?");
            ps.setInt(1, prodId);
            ps.setInt(2, carrello.getIdUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSave(Carrello carrello, Prodotto prodID, int add) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement psCa = con
                    .prepareStatement("INSERT INTO carrello (idUtente,idProdotto,quantita,prezzo,images) VALUES (?,?, ?,?,?)");

            psCa.setInt(1, carrello.getIdUtente());
            psCa.setInt(2, prodID.getId());
            psCa.setInt(3, add);
            psCa.setDouble(4, prodID.getPrezzo());
            psCa.setString(5, prodID.getImages());


            if (psCa.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setQuantita(Carrello carrello, int addNum, int idProdotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(" update carrello  set quantita=quantita+?  where idProdotto=? and idUtente=?; ");
            ps.setInt(1, addNum);
            ps.setInt(2, idProdotto);
            ps.setInt(3, carrello.getIdUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeQuantita(Carrello carrello, int addNum, int idProdotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(" update carrello  set quantita=quantita-?  where idProdotto=? and idUtente=?; ");
            ps.setInt(1, addNum);
            ps.setInt(2, idProdotto);
            ps.setInt(3, carrello.getIdUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Carrello doRetrieveByIdUtente(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement psCa = con
                    .prepareStatement("SELECT c.idUtente,p.id,c.quantita,p.prezzo,p.nome,p.descrizione,p.sconto FROM carrello c LEFT JOIN Prodotto p on idProdotto=id WHERE idUtente=?");

            psCa.setInt(1, id);

            ResultSet rs = psCa.executeQuery();
            Carrello car = new Carrello();

            while (rs.next()) {
                car.setIdUtente(rs.getInt(1));
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getInt(2));
                prodotto.setPrezzo(rs.getDouble(4));
                prodotto.setNome(rs.getString(5));
                prodotto.setDescrizione(rs.getString(6));
                prodotto.setSconto(rs.getInt(7));
                Carrello.ProdottoQuantita prodottoQuantita = new Carrello.ProdottoQuantita(prodotto, rs.getInt(3));
                LinkedHashMap<Integer, Carrello.ProdottoQuantita> link = new LinkedHashMap<>();
                link.put(prodotto.getId(), prodottoQuantita);
                car.setProdotti(link);

            }

            return car;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSaveAll(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement psCa = con
                    .prepareStatement("INSERT INTO carrello (idUtente,idProdotto,quantita,prezzo) VALUES (?,?, ?,?)");
            for (Carrello.ProdottoQuantita c : carrello.getProdotti()) {
                psCa.setInt(1, carrello.getIdUtente());
                psCa.setInt(2, c.getProdotto().getId());
                psCa.setInt(3, c.getQuantita());
                if (c.getProdotto().getSconto() > 0) {
                    psCa.setDouble(4, c.getProdotto().getPrezzo() - ((c.getProdotto().getPrezzo() * c.getProdotto().getSconto()) / 100));
                } else {
                    psCa.setDouble(4, c.getProdotto().getPrezzo());
                }
                psCa.addBatch();
            }
            psCa.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

