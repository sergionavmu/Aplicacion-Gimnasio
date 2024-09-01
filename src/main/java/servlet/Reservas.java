package servlet;

import Model.Reserva;
import Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/reservas")
public class Reservas extends HttpServlet {

    Service service;
    Model.Usuario usuario;

    public Reservas() throws SQLException {
        this.service = Service.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Reserva> reservas = null;
        usuario = (Model.Usuario) request.getSession().getAttribute("id_usuario");
        try {

            reservas = service.getReservas();
            request.setAttribute("reservas", reservas);

            getServletContext().getRequestDispatcher("/reservas.jsp").forward(request, response);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.service.PostReservas(response, request);
    }
}
