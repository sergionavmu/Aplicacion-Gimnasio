package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet("/helloworld")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter salida = resp.getWriter();

        salida.println("<html><body>");
        salida.println("");
        salida.println("Fecha y hora actual: " + new Date());
        salida.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter salida = resp.getWriter();
        BufferedReader entrada = req.getReader();
        String line = entrada.readLine();

        salida.println(line);
    }
}
