package servlet;


import Model.ModelCursos;
import Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/usuario")
public class Usuario extends HttpServlet {

    Service service;

    public Usuario() throws SQLException {
        this.service = Service.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Model.Usuario> usuarioModel = null;
        try {
            usuarioModel = service.getUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("usuarios", usuarioModel);
        getServletContext().getRequestDispatcher("/cursos.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service.checkUser(request, response);
    }
}

