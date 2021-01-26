package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrdiniDAO {
    public void doSave(Ordini ordini) {
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps= con.prepareStatement("INSERT INTO dettaglio_ordini (idUte,data_acq,totale) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,ordini.getUtente());
            ps.setString(2,  ordini.getData());
            ps.setDouble(3,ordini.getTotale());

            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idOrdine = rs.getInt(1);

            PreparedStatement psCa = con
                    .prepareStatement("INSERT INTO prodotto_ordini (idOrdine, idProdotto,quantita,prezzo,images) VALUES (?, ?,?,?,?)");
            for (Carrello.ProdottoQuantita c : ordini.getProdotti()) {
                psCa.setInt(1, idOrdine);
                psCa.setInt(2, c.getProdotto().getId());
                psCa.setInt(3, c.getQuantita());
                if (c.getProdotto().getSconto() > 0) {
                    psCa.setDouble(4, c.getProdotto().getPrezzo()-((c.getProdotto().getPrezzo()*c.getProdotto().getSconto())/100));
                }else{
                    psCa.setDouble(4, c.getProdotto().getPrezzo());
                }
                psCa.setString(5,c.getProdotto().getImages());
                psCa.addBatch();
            }
            psCa.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ordini> getRetrieveByUtente(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "select  idOrd,idUte,data_acq,totale from dettaglio_ordini where idUte=?;");
            ps.setInt(1, id);
            ArrayList<Ordini> idOrdini= new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordini ordini= new Ordini();
                ordini.setId(rs.getInt(1));
                ordini.setUtente(rs.getInt(2));
                ordini.setData(rs.getString(3));
                ordini.setTotale(rs.getDouble(4));
                ordini.setProdotti(getProdotti(con,ordini.getId()));
                idOrdini.add(ordini);
            }

            return idOrdini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Carrello.ProdottoQuantita> getProdotti(Connection con, int idOrdine) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "SELECT p.id,p.nome, p.descrizione,p_ord.prezzo,p.sconto,p_ord.quantita,p.images FROM Prodotto p LEFT JOIN prodotto_ordini p_ord ON id=idProdotto WHERE idOrdine=?");
        ps.setInt(1, idOrdine);
        ResultSet rs = ps.executeQuery();
        ArrayList<Carrello.ProdottoQuantita> list= new ArrayList<Carrello.ProdottoQuantita>();
        while (rs.next()) {

            Prodotto p = new Prodotto();
            p.setId(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setDescrizione(rs.getString(3));
            p.setPrezzo(rs.getDouble(4));
            p.setSconto(rs.getInt(5));
            p.setImages(rs.getString(7));

            Carrello.ProdottoQuantita prodottoQuantita= new Carrello.ProdottoQuantita(p,rs.getInt(6));

            list.add(prodottoQuantita);

        }
        return list;

    }

}
