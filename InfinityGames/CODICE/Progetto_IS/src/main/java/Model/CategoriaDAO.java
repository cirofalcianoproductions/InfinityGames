package Model;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {


            public ArrayList<Categoria> doRetrieveAll(){
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps =
                        con.prepareStatement("SELECT  id,nome, descrizione FROM Categoria ");
                ResultSet rs = ps.executeQuery();
                ArrayList<Categoria> list= new ArrayList<Categoria>();
                while (rs.next()) {

                    Categoria p = new Categoria();
                    p.setId(rs.getInt(1));
                    p.setNome(rs.getString(2));
                    p.setDescrizione(rs.getString(3));
                    list.add(p);

                }
                return list;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Categoria (nome, descrizione) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            categoria.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Categoria SET nome=?, descrizione=? WHERE id=?");
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescrizione());
            ps.setInt(3, categoria.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Categoria WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteByNome(String nomeCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Categoria WHERE nome=?");
            ps.setString(1, nomeCategoria);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Categoria doRetrieveCategoriaById(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT  id, nome, descrizione FROM Categoria " +
                            " WHERE  id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

               Categoria c = new Categoria();

                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));

                return c;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Categoria doRetrieveCategoriaByNome(String nomeCategoria){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, nome, descrizione FROM Categoria WHERE nome=?");
            ps.setString(1, nomeCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                return c;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

            }


}


