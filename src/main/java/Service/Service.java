package Service;

import Dao.*;
import Model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Service {

    LoginDao loginDao;
    CursosDao cursosDao;
    AdminDao adminDao;
    UsuarioDao usuarioDao;
    ReservasDao reservasDao;
    FavoritosDao favoritosDao;

    private static Service service;

    public static Service getInstance() throws SQLException {
        if (service == null)
            return (service = new Service());
        return service;
    }

    private Service() throws SQLException {
        this.loginDao = new LoginDao();
        this.cursosDao = new CursosDao();
        this.usuarioDao = new UsuarioDao();
        this.adminDao = new AdminDao();
        this.favoritosDao = new FavoritosDao();
        this.reservasDao = new ReservasDao();
    }

    public void checkUser(HttpServletRequest request, HttpServletResponse resp) {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        System.out.println(user + " " + pass);

        this.loginDao.connect();
        Usuario usuario = null;
        try {
            usuario = this.loginDao.checkUser(user, pass);
            request.setAttribute("user", usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.loginDao.disconnect();

        try {
            if (usuario == null || usuario.getId() == 0) {
                request.setAttribute("error", "Nombre o contraseña incorrectos");
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, resp);
            } else {
                guardarUsuario(request, usuario);
                if (usuario.getAdmin() == 1) {
                    this.cursosDao.connect();
                    request.setAttribute("cursos", this.cursosDao.getCursos());
                    this.cursosDao.disconnect();
                    this.usuarioDao.connect();
                    request.setAttribute("usuarios", this.usuarioDao.getUsuarios());
                    this.usuarioDao.disconnect();
                    request.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, resp);
                } else {
                    // Si el usuario es normal, redirige a cursos.jsp
                    this.cursosDao.connect();
                    request.setAttribute("cursos", this.cursosDao.getCursos());
                    this.cursosDao.disconnect();
                    request.getServletContext().getRequestDispatcher("/cursos.jsp").forward(request, resp);
                }
            }
        } catch (ServletException | IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarUsuario(HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession();

        session.setAttribute("usuario", usuario);
    }

    private Usuario checkUserSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Usuario) session.getAttribute("usuario");
    }


    public ArrayList<Usuario> getUsuarios() throws SQLException {
        this.usuarioDao.connect();
        ArrayList<Usuario> usuarios = usuarioDao.getUsuarios();
        this.usuarioDao.disconnect();
        return usuarios;
    }

    public void PostUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action"); // Obtén el parámetro "action" de la solicitud que lo hemos indicado en nuestro jsp
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String tipo = request.getParameter("tipo");
        String adminParam = request.getParameter("admin");
        int admin = 0;
        if (adminParam != null && !adminParam.isEmpty()) {
            admin = Integer.parseInt(adminParam);
        }
        if (action != null) {
            if (action.equals("insert")) {
                Usuario usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setEmail(email);
                usuario.setPass(pass);
                usuario.setTipo(tipo);
                usuario.setAdmin(admin);

                try {
                    usuarioDao.insertUsuario(usuario);
                    response.sendRedirect("/registro");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (action.equals("update")) {
                Usuario usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setEmail(email);
                usuario.setPass(pass);
                usuario.setTipo(tipo);
                usuario.setAdmin(admin);
                try {
                    usuarioDao.updateUsuario(usuario);
                    response.sendRedirect("/registro");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (action.equals("delete")) {
                int usuarioId = Integer.parseInt(request.getParameter("id"));

                try {
                    usuarioDao.deleteUsuario(usuarioId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.usuarioDao.disconnect();
    }


    public ArrayList<Favorito> getCursosFavoritos() throws SQLException {
        this.favoritosDao.connect();
        ArrayList<Favorito> cursosFavoritos = favoritosDao.getCursosFavoritos();
        this.favoritosDao.disconnect();
        return cursosFavoritos;
    }


    public void PostFavorito(HttpServletRequest request, HttpServletResponse response) {
        this.favoritosDao.connect();
        String action = request.getParameter("action");

        this.cursosDao.connect();
        String id_curso = request.getParameter("id_curso");
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String comentario = request.getParameter("comentarios");
        String imagen = request.getParameter("imagen");

        this.usuarioDao.connect();
        String id_usuario = request.getParameter("id_usuario");

        // Verifica si las cadenas no son nulas antes de convertirlas
        if (id_curso != null && id_usuario != null) {
            if (action != null) {
                if (action.equals("insert")) {
                    Favorito favorito = new Favorito();
                    favorito.setId_curso(Integer.parseInt(id_curso));
                    favorito.setId_usuario(Integer.parseInt(id_usuario));
                    favorito.setNombre(nombre);
                    favorito.setPrecio(Float.parseFloat(precio));
                    favorito.setComentario(comentario);
                    favorito.setImagen(imagen);

                    try {
                        favoritosDao.insertFavorito(favorito);

                        response.sendRedirect("favoritos");
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (action.equals("delete")) {

                    int cursoId = Integer.parseInt(request.getParameter("id"));
                    try {
                        favoritosDao.deleteFavorito(cursoId);
                        request.getRequestDispatcher("cursos").forward(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        this.cursosDao.disconnect();
        this.usuarioDao.disconnect();
        this.favoritosDao.disconnect();
    }



    public ArrayList<ModelCursos> getCursos() throws SQLException {
        this.cursosDao.connect();
        ArrayList<ModelCursos> cursos = cursosDao.getCursos(); // Utiliza el método getCursos de tu CursosDao
        this.cursosDao.disconnect();
        return cursos; // Devuelve la lista de cursos reales de la base de datos
    }


    public void PostCurso(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.cursosDao.connect();
        String action = request.getParameter("action");
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String fecha = request.getParameter("fecha");
        String info = request.getParameter("info");
        if (action != null) {
            if (action.equals("insert")) {
                ModelCursos curso = new ModelCursos();

                curso.setNombre(nombre);
                curso.setPrecio(Float.parseFloat(precio));
                curso.setFecha(fecha);
                curso.setComentarios(info);

                try {
                    cursosDao.insertCurso(curso); // Llamamos al método insertCurso
                    response.sendRedirect("/cursos"); // Redirige a la página de cursos
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.cursosDao.disconnect();
    }



    public ArrayList<Reserva> getReservas() throws SQLException {
        this.reservasDao.connect();
        ArrayList<Reserva> reservas = reservasDao.getReservas();
        this.reservasDao.disconnect();
        return reservas;
    }

    public void PostReservas(HttpServletResponse response, HttpServletRequest request) {

        this.reservasDao.connect();
        String action = request.getParameter("action");


        this.cursosDao.connect();
        String id_curso = request.getParameter("id_curso");
        String nombre = request.getParameter("nombre");
        String comentario = request.getParameter("comentarios");
        String estado = request.getParameter("estado");
        String imagen = request.getParameter("imagen");

        this.usuarioDao.connect();
        String id_usuario = request.getParameter("id_usuario");
        if (id_curso != null && id_usuario != null) {
            if (action != null) {
                if (action.equals("insert")) {
                    Date fechaReserva = new Date();
                    Reserva reservas = new Reserva();
                    reservas.setId_curso(Integer.parseInt(id_curso));
                    reservas.setId_usuario(Integer.parseInt(id_usuario));
                    reservas.setNombre(nombre);
                    reservas.setComentarios(comentario);
                    reservas.setEstado(estado);
                    reservas.setFechaReserva(fechaReserva);
                    reservas.setImagen(imagen);

                    try {
                        reservasDao.insertReserva(reservas);
                        response.sendRedirect("reservas");
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (action.equals("update")) {
                    Date fechaReserva = new Date();
                    Reserva reservas = new Reserva();
                    reservas.setId_curso(Integer.parseInt(id_curso));
                    reservas.setId_usuario(Integer.parseInt(id_usuario));
                    reservas.setNombre(nombre);
                    reservas.setComentarios(comentario);
                    reservas.setEstado(estado);
                    reservas.setFechaReserva(fechaReserva);
                    reservas.setImagen(imagen);

                    try {
                        reservasDao.updateReserva(reservas);
                        response.sendRedirect("reservas");
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (action.equals("delete")) {
                    int reservasId = Integer.parseInt(request.getParameter("id"));

                    try {
                        reservasDao.deleteReserva(reservasId);
                        response.sendRedirect("cursos.jsp");
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        this.cursosDao.disconnect();
        this.usuarioDao.disconnect();
        this.reservasDao.disconnect();
    }

    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("Error: ", "Vuelva a iniciar sesión, conexion interumpida");
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void getAdmin(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException, SQLException {
        this.adminDao.connect();
        this.cursosDao.connect();

        ArrayList<ModelCursos> cursos = cursosDao.getCursos();
        this.usuarioDao.connect();
        ArrayList<Usuario> usuarios = usuarioDao.getUsuarios();

        req.setAttribute("cursos", cursos);
        req.setAttribute("usuarios", usuarios);

        req.getRequestDispatcher("/admin.jsp").forward(req, response);
        this.cursosDao.disconnect();
        this.usuarioDao.disconnect();
        this.adminDao.disconnect();
    }


    public void PostAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.cursosDao.connect();

        String action = request.getParameter("action");
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String fecha = request.getParameter("fecha");
        String info = request.getParameter("info");


        if(action != null) {
            if (action.equals("insertCurso")) {
                ModelCursos curso = new ModelCursos();

                curso.setNombre(nombre);
                curso.setPrecio(Float.parseFloat(precio));
                curso.setFecha(fecha);
                curso.setComentarios(info);

                try {
                    cursosDao.insertCurso(curso); // Llamamos al método insertCurso
                    response.sendRedirect("/admin"); // Redirige a la página de cursos
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (action.equals("updateCurso")) {

                ModelCursos curso = new ModelCursos();

                curso.setNombre(nombre);
                curso.setPrecio(Float.parseFloat(precio));
                curso.setFecha(fecha);
                curso.setComentarios(info);

                try {
                    cursosDao.updateCurso(curso);
                    response.sendRedirect("/admin");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (action.equals("deleteCurso")) {

                int cursoId = Integer.parseInt(request.getParameter("id"));

                try {
                    cursosDao.deleteCurso(cursoId);
                    response.sendRedirect("/admin");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                this.cursosDao.disconnect();


                this.usuarioDao.connect();


            } else if (action != null) {

                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String pass = request.getParameter("pass");

                if (action.equals("insertUsuario")) {
                    Usuario usuario = new Usuario();
                    usuario.setUsername(username);
                    usuario.setEmail(email);
                    usuario.setPass(pass);

                    try {
                        usuarioDao.insertUsuario(usuario);
                        response.sendRedirect("/admin");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (action.equals("updateUsuario")) {
                    Usuario usuario = new Usuario();
                    usuario.setUsername(username);
                    usuario.setEmail(email);
                    usuario.setPass(pass);
                    try {
                        usuarioDao.updateUsuario(usuario);
                        response.sendRedirect("/admin");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (action.equals("deleteUsuario")) {
                    int usuarioId = Integer.parseInt(request.getParameter("id"));

                    try {
                        usuarioDao.deleteUsuario(usuarioId);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            this.usuarioDao.disconnect();
        }
    }

}











