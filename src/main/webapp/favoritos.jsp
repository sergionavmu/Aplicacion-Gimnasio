<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="login.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ModelCursos" %>
<%@ page import="servlet.Cursos" %>
<%@ page import="Dao.CursosDao" %>
<%@ page import="Dao.AdminDao" %>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Favorito" %>
<html>
<head>
    <link rel="stylesheet" href="favoritos.css">
    <link rel="stylesheet" href="style.css">
    <header>
        <h2 class="logo">Favoritos</h2>
        <%@ include file="navbar.jsp" %>
    </header>
</head>
<body>
    <%
        ArrayList<Favorito> favoritos = (ArrayList<Favorito>) request.getAttribute("favoritos");
        ArrayList<String> datos = (ArrayList<String>) request.getAttribute("datos");
        String usuario = (String) request.getAttribute("usuario");
    %>
    <div class="container mt-5">
        <div class="form-toggle">
            <div class="form-panel one">
                <div class="card-container">
                    <% if (favoritos != null) { %>
                        <% for (Favorito favorito : favoritos) { %>
                            <div class="card mb-3">
                                <div class="card-body">
                                    <img src="<%=favorito.getImagen()%>">
                                    <h2 class="card-title"><%=favorito.getNombre()%></h2>
                                    <p class="card-text">Precio: <%=favorito.getPrecio()%>€</p>
                                    <p class="card-text">Informacion: <%=favorito.getComentario()%></p>
                                </div>
                            </div>
                        <% } %>
                    <% } else { %>
                        <p>No hay cursos favoritos disponibles.</p>
                    <% } %>
                    <% if (datos != null) { %>
                        <% for (String str : datos) { %>
                            <p><%= str %></p>
                        <% } %>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <script src="script.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>