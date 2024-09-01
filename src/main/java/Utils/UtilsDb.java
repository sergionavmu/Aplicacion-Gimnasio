package Utils;

public class UtilsDb {
    public static final String CHECK_USER = "select id, username, email, fechaAlta, pass, admin from usuario where username = ? and pass = ?";
    public static final String UPDATE_USER = "update usuario username = ?, set email = ?, pass = ? where id = ? ";
    public static final String INSERT_USER = "INSERT INTO usuario (username, email, pass, tipus, admin) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER = "delete from usuario  where id = ? ";
    public static final String SELECT_USUARIOS = "select * from usuario";

    public static final String SELECT_CURSOS = "select * from curso";
    public static final String INSERT_CURSO= "INSERT INTO curso (nombre, precio, fecha, info) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_CURSO = "UPDATE curso SET precio = ?, fecha = ?, info = ? WHERE nombre = ?";
    public static final String DELETE_CURSO = "delete from curso where id = ? ";

    public static final String SELECT_RESERVA= "select * from reserva";
    public static final String INSERT_RESERVA = "INSERT INTO reserva (id_curso, id_usuario, nombre,  comentarios, estado, fechaReserva, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_RESERVA = "UPDATE reserva SET id_curso= ?, id_usuario=?, nombre = ?, fechaReserva = ?, comentarios = ?, estado = ? WHERE id = ?";
    public static final String DELETE_RESERVA = "DELETE FROM reserva WHERE id = ?";

    public static final String SELECT_FAVORITOS= "SELECT * from favorito";
    public static final String INSERT_FAVORITOS= "INSERT INTO favorito (id_curso, id_usuario, nombre, precio, comentario, imagen) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_FAVORITOS = "delete from favorito where id = ?";


    // Método para conectarse a la base de datos
    public static final String SCHEMA_NAME= "gym"; // el nombre del schema (Base de datos)
    public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER_CONNECTION = "user"; // modificar por usuario de BBDD (root)
    public static final String PASS_CONNECTION = "jupiter"; // modificar por password de BBDD (root)
}
