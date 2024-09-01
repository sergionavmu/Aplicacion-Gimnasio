package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ModelCursos;
import Model.Usuario;

import static Utils.UtilsDb.*;

public class AdminDao {

    Connection connection;
    CursosDao cursosDao;
    UsuarioDao usuarioDao;

    public AdminDao() {
        cursosDao = new CursosDao();
        usuarioDao = new UsuarioDao();
    }

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

    public CursosDao getCursosDao() {
        cursosDao.connect();
        return cursosDao;
    }

    public UsuarioDao getUsuarioDao() {
        usuarioDao.connect();
        return usuarioDao;
    }

    public void insertCurso(ModelCursos curso) throws SQLException {
        cursosDao.insertCurso(curso);
    }


    public void updateCurso(ModelCursos curso) throws SQLException {
        cursosDao.updateCurso(curso);
    }


    public void deleteCurso(int cursoId) throws SQLException {
        cursosDao.deleteCurso(cursoId);
    }

    public void insertUsuario(Usuario usuario) throws SQLException {
        usuarioDao.insertUsuario(usuario);
    }


    public void updateUsuario(Usuario usuario) throws SQLException {
        usuarioDao.updateUsuario(usuario);
    }


    public void deleteUsuario(int usuarioId) throws SQLException {
        usuarioDao.deleteUsuario(usuarioId);
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }

}
