<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ModelCursos" %>
<%@ page import="servlet.Cursos" %>
<%@ page import="Dao.CursosDao" %>
<%@ page import="Dao.AdminDao" %>
<%@ page import="Model.Usuario" %>
<%@ include file="login.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GigaGym</title>
    <link rel="stylesheet" href="style.css">
    <%
        String usuario = (String) request.getAttribute("usuario");
        Usuario user = (Usuario) request.getAttribute("user");
        ArrayList<ModelCursos> cursos = (ArrayList<ModelCursos>) request.getAttribute("cursos");
        ArrayList<String> datos = (ArrayList<String>) request.getAttribute("datos");
    %>
</head>

<body>

    <header>
        <div class="logo-container">
            <img src="img/logo.png">
            <h2 class="logo">Bienvenido al GigaGym</h2>
        </div>
        <nav class="navigation">
            <a href="index.jsp">Inicio</a>
            <a href="cursos">Cursos</a>
            <a href="horario.jsp">Horarios</a>
            <a href="contacto.jsp">Contacto</a>
            <button class="btnLogin-popup">Login</button>
        </nav>
    </header>

    <section class="video-section">
        <div class"video-title">
            <h2>SE UN GIGACHAD</h2>
            <h2>SE PARTE DEL GIGA GYM</h2>
        </div>
        <video autoplay loop muted>
            <source src="img/gigachad.mp4" type="video/mp4">
        </video>
    </section>



    <script src="script.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>