<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ModelCursos" %>
<%@ page import="servlet.Cursos" %>
<%@ page import="Dao.CursosDao" %>
<%@ page import="Dao.AdminDao" %>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Favorito" %>
<%@ page import="Model.Reserva" %>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="cursos.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <%
        String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : "";
        ArrayList<ModelCursos> cursos = (ArrayList<ModelCursos>) request.getAttribute("cursos");
        ArrayList<String> datos = (ArrayList<String>) request.getAttribute("datos");
        String usuario = (String) request.getAttribute("usuario");
        Usuario user = (Usuario) request.getAttribute("user");
    %>

    <header>
        <h2 class="logo">Cursos</h2>
       <%@ include file="navbar.jsp" %>
    </header>

    <div class="container mt-5">
        <div class="row" id="data-panel">
            <div class="form-header">
                <% if (!error.equals("")) { %>
                    <div class="alert alert-danger" role="alert">
                        <%=error%>
                    </div>
                <% } %>
                <div class="card-container">
                    <% for (ModelCursos curso : cursos) { %>
                        <div class="card-body">
                            <img src="<%=curso.getImagen()%>">
                            <div class="card-text">
                                <h2 class="card-title"><%=curso.getNombre()%></h2>
                                <p>Precio: <%=curso.getPrecio()%>€</p>
                                <p>Fecha: <%=curso.getFecha()%></p>
                                <p>Información: <%=curso.getComentarios()%></p>
                            </div>

                            <div class="card-foot">
                                <% if (user != null) { %>
                                    <!-- Agregar a Favoritos -->
                                    <form action="favoritos" method="post">
                                        <input type="hidden" name="action" value="insert"/>
                                        <input type="hidden" name="id_curso" value="<%=curso.getId()%>"/>
                                        <input type="hidden" name="id_usuario" value="<%=user.getId()%>"/>
                                        <input type="hidden" name="nombre" value="<%=curso.getNombre()%>"/>
                                        <input type="hidden" name="precio" value="<%=curso.getPrecio()%>"/>
                                        <input type="hidden" name="comentarios" value="<%=curso.getComentarios()%>"/>
                                        <input type="hidden" name="imagen" value="<%=curso.getImagen()%>"/>
                                        <button type="submit" class="btn btn-warning"><i class="bi bi-suit-heart-fill"></i>Agregar a favoritos</button>
                                    </form>

                                    <!-- Reservar -->
                                    <form action="reservas" method="post">
                                        <input type="hidden" name="action" value="insert"/>
                                        <input type="hidden" name="id_curso" value="<%=curso.getId()%>"/>
                                        <input type="hidden" name="id_usuario" value="<%=user.getId()%>"/>
                                        <input type="hidden" name="nombre" value="<%=curso.getNombre()%>"/>
                                        <input type="hidden" name="comentarios" value="<%=curso.getComentarios()%>"/>
                                        <input type="hidden" name="imagen" value="<%=curso.getImagen()%>"/>
                                        <button type="submit" class="btn btn-primary"><i class="fas fa-calendar-alt"></i> Reservar</button>
                                    </form>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                    <% if (datos != null) { %>
                        <% for (String str : datos) { %>
                            <p><%= str %></p>
                        <% } %>
                    <% } %>
            </div>
        </div>
    </div>
</body>
</html>