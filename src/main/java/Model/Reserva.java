package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
        private int id_curso;
        private int id_usuario;
        private String nombre;
        private String comentarios;
        private String estado;
        private String imagen;
        private Date fechaReserva;
        private List<ModelCursos> cursos;

        public Reserva(int id_curso, int id_usuario, String nombre, String comentarios, String estado, String imagen, Date fechaReserva) {
            this.id_curso=id_curso;
            this.id_usuario=id_usuario;
            this.nombre = nombre;
            this.comentarios = comentarios;
            this.imagen = imagen;
            this.estado = estado;
            this.fechaReserva = new Date();
            this.cursos=new ArrayList<>();


        }

    public Reserva() {

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ModelCursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<ModelCursos> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Reservas{" +
                "id_curso=" + id_curso +
                ", id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", estado='" + estado + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}