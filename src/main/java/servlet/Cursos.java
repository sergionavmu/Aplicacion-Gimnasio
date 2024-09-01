package servlet;

import Model.ModelCursos;
import Model.Usuario;
import Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/cursos")
public class Cursos extends HttpServlet {
    Service service;

    public Cursos() throws SQLException {
        this.service = Service.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ModelCursos> cursosModel = null;
        //ArrayList<Usuario> usuarios = null;
        try {
            cursosModel = service.getCursos();
            //usuarios = service.getUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("cursos", cursosModel);
        //request.setAttribute("user", usuarios);
        getServletContext().getRequestDispatcher("/cursos.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service.PostCurso(request, response);
        response.sendRedirect("cursos.jsp");
    }
}
