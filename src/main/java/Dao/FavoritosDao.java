package Dao;

import Model.Favorito;

import java.sql.*;
import java.util.ArrayList;

import static Utils.UtilsDb.*;

public class FavoritosDao {

    Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = CONNECTION;
            String user = USER_CONNECTION;
            String pass = PASS_CONNECTION;
            this.connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Favorito> getCursosFavoritos() throws SQLException {
        ArrayList<Favorito> favoritos = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(SELECT_FAVORITOS)) {
            System.out.println(ps.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Favorito favorito = new Favorito(
                            rs.getInt("id_curso"),
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getFloat("precio"),
                            rs.getString("comentario"),
                            rs.getString("imagen")
                    );
                    favoritos.add(favorito);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return favoritos;
    }

    public void insertFavorito(Favorito favorito) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_FAVORITOS)) {
            ps.setInt(1, favorito.getId_curso());
            ps.setInt(2, favorito.getId_usuario());
            ps.setString(3, favorito.getNombre());
            ps.setFloat(4, favorito.getPrecio());
            ps.setString(5, favorito.getComentario());
            ps.setString(6, favorito.getImagen());
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFavorito(int cursoId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_FAVORITOS)) {
            ps.setInt(1, cursoId);
            ps.executeUpdate();
            System.out.println("Se elimino de favoritos.");
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }

}
