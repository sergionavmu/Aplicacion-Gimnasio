package Dao;

import Model.Usuario;

import java.sql.*;

import static Utils.UtilsDb.*;

public class LoginDao {

    private Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = CONNECTION;
            String user = USER_CONNECTION;
            String pass = PASS_CONNECTION;
            this.connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public Usuario checkUser(String nombre, String pass) throws SQLException {
        // String select = GET_PERSONA_BY_ID; tendría el valor: "select * from persona where id = ?".
        Usuario usuario = null;
        try (PreparedStatement ps = this.connection.prepareStatement(CHECK_USER)) {
            ps.setString(1, nombre);
            ps.setString(2, pass);
            System.out.println(ps.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getInt("admin")
                    );
                }
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // métod para desconectarse de la base de datos

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
