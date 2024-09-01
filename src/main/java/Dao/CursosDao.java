package Dao;

import Model.ModelCursos;
import Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

import static Utils.UtilsDb.*;

public class CursosDao {

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

    public ArrayList<ModelCursos> getCursos() throws SQLException {
        ArrayList<ModelCursos> cursos = new ArrayList<>();


        try (Statement st = this.connection.createStatement()) {
            System.out.println(st.toString());
            try (ResultSet rs = st.executeQuery(SELECT_CURSOS)) {
                while (rs.next()) {
                    ModelCursos curso = new ModelCursos(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("info"),
                            rs.getInt("favorito"),
                            rs.getString("imagen")
                    );
                    cursos.add(curso);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return cursos;
    }

    public void insertCurso(ModelCursos cursos) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CURSO)) {
            ps.setString(1, cursos.getNombre());
            ps.setFloat(2,  cursos.getPrecio());
            ps.setString(3, cursos.getFecha());
            ps.setString(4, cursos.getComentarios());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Curso creado con éxito. Nombre del curso: " + cursos.getNombre());
            } else {
                System.out.println("No se pudo completar la creacion del curso.");
            }
        }
    }
    public void updateCurso(ModelCursos cursos) throws SQLException {
        try {
            this.connect();
            if (cursos.getNombre() == null || cursos.getNombre().isEmpty()) {
                throw new IllegalArgumentException("Nombre del curso no válido");
            }

            try (PreparedStatement ps = connection.prepareStatement(UPDATE_CURSO)) {
                ps.setFloat(1, cursos.getPrecio());
                ps.setString(2, cursos.getFecha());
                ps.setString(3, cursos.getComentarios());
                ps.setString(4, cursos.getNombre());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Curso modificado con éxito. Nombre del curso: " + cursos.getNombre());
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


    public void deleteCurso(int cursoId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CURSO)) {
            ps.setInt(1, cursoId);
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