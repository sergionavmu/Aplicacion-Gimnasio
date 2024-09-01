package servlet;


import Service.Service;
import Model.Favorito;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/favoritos")
public class Favoritos extends HttpServlet {
    Service service;
    Model.Usuario usuario;

    public Favoritos() throws SQLException {
        this.service = Service.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Favorito> favoritos = null;
        usuario  = (Model.Usuario) request.getSession().getAttribute("id_usuario");
        try {

            favoritos = service.getCursosFavoritos();
            request.setAttribute("favoritos", favoritos);

            getServletContext().getRequestDispatcher("/favoritos.jsp").forward(request, response);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service.PostFavorito(request, response);

    }
}
