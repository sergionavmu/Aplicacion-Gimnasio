<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="login.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="horario.css">
        <link rel="stylesheet" href="style.css">
    <header>
        <h2 class="logo">Horario del Gimnasio</h2>
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
        <table>
            <tr>
                <th>Hora</th>
                <th>Lunes</th>
                <th>Martes</th>
                <th>Miércoles</th>
                <th>Jueves</th>
                <th>Viernes</th>
                <th>Sábado</th>

            </tr>
            <tr>
                <td>08:00 AM</td>
                <td>Entrenamiento funcional</td>
                <td>Yoga</td>
                <td>Zumba</td>
                <td>Spinning</td>
                <td>Body Pump</td>
                <td>Pilates</td>

            </tr>
            <tr>
                <td>09:00 AM</td>
                <td>Spinning</td>
                <td>Entrenamiento funcional</td>
                <td>Pilates</td>
                <td>Yoga</td>
                <td>Zumba</td>
                <td>Body Pump</td>

            </tr>
            <tr>
                <td>10:00 AM</td>
                <td>Yoga</td>
                <td>Pilates</td>
                <td>Entrenamiento funcional</td>
                <td>Spinning</td>
                <td>Body Pump</td>
                <td>Zumba</td>
            </tr>
            <tr>
                <td>11:00 AM</td>
                <td>Spinning</td>
                <td>Pilates</td>
                <td>Yoga</td>
                <td>Entrenamiento funcional</td>
                <td>Zumba</td>
                <td>Pilates</td>
            </tr>
            <!-- Agrega más filas para otros horarios -->
        </table>
        <script src="script.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
