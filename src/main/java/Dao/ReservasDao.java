package Dao;

import Model.Reserva;

import java.sql.*;
import java.util.ArrayList;

import static Utils.UtilsDb.*;

public class ReservasDao {

    Connection connection;


    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = CONNECTION;
            String user = USER_CONNECTION;
            String pass = PASS_CONNECTION;
            this.connection = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reserva> getReservas() throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Statement st = this.connection.createStatement()) {
            System.out.println(st.toString());
            try (ResultSet rs = st.executeQuery(SELECT_RESERVA)) {
                while (rs.next()) {
                    Reserva reserva = new Reserva(
                            rs.getInt("id_usuario"),
                            rs.getInt("id_curso"),
                            rs.getString("nombre"),
                            rs.getString("comentarios"),
                            rs.getString("estado"),
                            rs.getString("imagen"),
                            rs.getDate("fechaReserva")

                    );
                    reservas.add(reserva);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return reservas;
    }


    public void insertReserva(Reserva reserva) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_RESERVA)) {
            ps.setInt(1, reserva.getId_curso());
            ps.setInt(2, reserva.getId_usuario());
            ps.setString(3, reserva.getNombre());
            ps.setString(4, reserva.getComentarios());
            ps.setString(5, reserva.getEstado());
            ps.setDate(6, new java.sql.Date(reserva.getFechaReserva().getTime()));
            ps.setString(7, reserva.getImagen());
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.disconnect();
    }


    public void updateReserva(Reserva reserva) throws SQLException {
        try {
            this.connect();
            if(reserva.getNombre() == null || reserva.getNombre().isEmpty()) {
                throw new IllegalArgumentException("No hemos encontrado ningún reserva con esas características");
            }

            try (PreparedStatement ps = connection.prepareStatement(UPDATE_RESERVA)) {
                ps.setInt(1, reserva.getId_curso());
                ps.setInt(2, reserva.getId_usuario());
                ps.setString(3, reserva.getNombre());
                ps.setString(4, reserva.getComentarios());
                ps.setString(5, reserva.getEstado());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Curso modificado con éxito.");
                } else {
                    System.out.println("No se pudo completar la modificación del curso.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar el curso: " + e.getMessage(), e);
        } finally {
            this.disconnect();
        }
    }

    public void deleteReserva(int reservaId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_RESERVA)) {
            ps.setInt(1, reservaId);
            ps.executeUpdate();
        }
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

