<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Model.ModelCursos" %>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Usuario" %>
<%@ page import="Service.Service" %>
<%@ page import="java.util.ArrayList" %>


<html>
<head>
    <title>Panel de Administrador</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>

    <%
        ArrayList<ModelCursos> cursos = (ArrayList<ModelCursos>) request.getAttribute("cursos");
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
    %>

    <h1>Panel de Administrador</h1>

    <div class="form-curso">
        <!-- Formulario para insertar un nuevo curso -->
        <h2>Insertar Nuevo Curso</h2>
        <form action="admin" class="signing-force" method="POST">
            <input type="hidden" name="action" value="insertCurso">
            <label>Nombre del Curso: <input type="text" name="nombre" required></label><br>
            <label>Precio: <input type="text" name="precio" required></label><br>
            <label>Fecha: <input type="text" name="fecha" required></label><br>
            <label>Comentarios: <input type="text" name="info" required></label><br>
            <input type="submit" value="Insertar Curso">
        </form>

        <h2>Modificar Curso</h2>
        <form action="admin" class="signing-force" method="POST">
            <input type="hidden" name="action" value = "updateCurso">
            <label>Nombre del Curso: <input type="text" name="nombre" required></label><br>
            <label>Precio: <input type="text" name="precio"></label><br>
            <label>Fecha: <input type="text" name="fecha"></label><br>
            <label>Comentarios: <input type="text" name="info"></label><br>
            <input type="submit" value="Actualizar">
        </form>

        <h2>Eliminar Curso</h2>
        <form action="admin" class="signing-force" method="POST">
            <input type="hidden" name="action" value="deleteCurso">
            <label>ID del Curso: <input type="text" name="id"></label>
            <input type="submit" value="Eliminar">
        </form>
    </div>

    <!-- Lista de Cursos -->
    <h2>Lista de Cursos</h2>
    <div class"tabla-curso">
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Fecha</th>
                <th>Comentarios</th>
            </tr>
            <%
                if (cursos != null) {
                    for (ModelCursos curso : cursos) {
                %>
                    <tr>
                        <td><%= curso.getId() %></td>
                        <td><%= curso.getNombre() %></td>
                        <td><%= curso.getPrecio() %></td>
                        <td><%= curso.getFecha() %></td>
                        <td><%= curso.getComentarios() %></td>
                    </tr>
                <%
                    }
                } else {
                    out.println("<p>No hay cursos disponibles.</p>");
                }
            %>
        </table>
    </div>

    <div class="form-usuario">
        <!-- Formulario para insertar un nuevo usuario -->
        <h2>Insertar Nuevo Usuario</h2>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="insertUsuario">
            <label>Nombre de Usuario: <input type="text" name="username" required></label><br>
            <label>Email: <input type="text" name="email"></label><br>
            <label>Contraseña: <input type="password" name="pass" required></label><br>
            <label>Tipo: <input type="text" name="tipo"></label><br>
            <input type="submit" value="Insertar Usuario">
        </form>
        <h2>Modificar Usuario</h2>
        <form action="admin" class="signing-force" method="POST">
            <input type="hidden" name="action" value = "updateUsuario">
            <label>Nombre del Curso: <input type="text" name="nombre" required></label><br>
            <label>Precio: <input type="text" name="precio"></label><br>
            <label>Fecha: <input type="text" name="fecha"></label><br>
            <label>Comentarios: <input type="text" name="info"></label><br>
            <input type="submit" value="Actualizar">
        </form>

         <h2>Eliminar Curso</h2>
         <form action="admin" class="signing-force" method="POST">
             <input type="hidden" name="action" value="deleteUsuario">
             <label>ID del Usuario: <input type="text" name="id" required></label>
             <input type="submit" value="Eliminar">
         </form>
    </div>

    <!-- Lista de Usuarios -->
    <h2>Lista de Usuarios</h2>
    <div class="tabla-usuario">
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre de Usuario</th>
                <th>Email</th>
                <th>Tipo</th>
                <th>Admin</th>
            </tr>
            <%
                if (usuarios != null) {
                    for (Usuario usuario : usuarios) {
                %>
                    <tr>
                        <td><%= usuario.getId() %></td>
                        <td><%= usuario.getUsername() %></td>
                        <td><%= usuario.getEmail() %></td>
                        <td><%= usuario.getTipo() %></td>
                        <td><%= usuario.getAdmin() %></td>
                    </tr>
                <%
                    }
                } else {
                    out.println("<p>No hay usuarios disponibles.</p>");
                }
            %>
        </table>
    </div>
</body>
</html>