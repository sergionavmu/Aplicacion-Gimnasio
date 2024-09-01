<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="login.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="contacto.css">
    <link rel="stylesheet" href="style.css">
    <header>
        <h2 class="logo">Contacto</h2>
        <nav class="navigation">
            <a href="index.jsp">Inicio</a>
            <a href="cursos">Cursos</a>
            <a href="horario.jsp">Horarios</a>
            <a href="contacto.jsp">Contacto</a>
            <button class="btnLogin-popup">Login</button>
        </nav>
    </header>
</head>
<body>
        <section id="contact">
            <div class="content">
                <div id="form">
                    <form action="" id="contactForm" method="post">
                        <span>Nombre</span>
                        <input type="text" name="name" class="name" placeholder="Escriba su nombre" tabindex=1 />
                        <span>Email</span>
                        <input type="text" name="email" class="email" placeholder="Escriba su correo" tabindex=2 />
                        <span>Mensaje</span>
                        <textarea class="message" placeholder="Deje su mensaje" tabindex=4></textarea>
                        <input type="submit" name="submit" value="Send e-mail" class="submit" tabindex=5>
                    </form>
                </div>
            </div>
        </section>
    </div>
        <script src="script.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
