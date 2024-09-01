package Dao;

import Model.ModelCursos;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

import static Utils.UtilsDb.*;


public class UsuarioDao {

    private Connection connection;


    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = CONNECTION;
            String user = USER_CONNECTION;
            String pass = PASS_CONNECTION;
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectarse a la base de datos: " + e.getMessage(), e);
        }
    }

    public void insertUsuario(Usuario usuario) throws SQLException {
        try {
            this.connect();

            if (usuario.getUsername() == null || usuario.getUsername().isEmpty() ||
                    usuario.getEmail() == null || usuario.getEmail().isEmpty() ||
                    usuario.getPass() == null || usuario.getPass().isEmpty()) {
                throw new IllegalArgumentException("Rellene los campos obligatorios.");
            }

            try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
                ps.setString(1, usuario.getUsername());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getPass());
                ps.setString(4, usuario.getTipo());
                ps.setInt(5, usuario.isAdmin());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Usuario registrado con éxito." + usuario.getUsername());
                } else {
                    System.out.println("No se pudo completar el registro del usuario.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        } finally {
            this.disconnect();
        }
    }



    public void updateUsuario(Usuario usuario) throws SQLException {
        try {
            this.connect();
            if (usuario.isAdmin()==1) {
                throw new IllegalArgumentException("No hemos encontrado ningún usuario con esas características");
            }
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
                ps.setString(1, usuario.getUsername());
                ps.setString(2, usuario.getEmail());
                ps.setString(3, usuario.getPass());
                ps.setString(4, usuario.getTipo());
                ps.setInt(5, usuario.isAdmin());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Usuario modificado con éxito.");
                } else {
                    System.out.println("No se pudo completar la modificación del usuario.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar el usuario: " + e.getMessage(), e);
        } finally {
            this.disconnect();
        }
    }
    public void deleteUsuario(int usuarioId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, usuarioId);
            ps.executeUpdate();
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Usuario> getUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Statement st = this.connection.createStatement()) {
            System.out.println(st.toString());
            try (ResultSet rs = st.executeQuery(SELECT_USUARIOS)) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getInt("admin")
                    );
                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return usuarios;
    }
}
