package Model;


import java.util.ArrayList;
import java.util.List;

public class Favorito {
    private int id_curso;
    private int id_usuario;
    private String nombre;
    private float precio;
    private String comentario;
    private String imagen;
    private List<ModelCursos> cursos;

    public Favorito(int id_curso, int id_usuario, String nombre, float precio, String comentario, String imagen) {
        this.id_curso = id_curso;
        this.id_usuario = id_usuario;
        this.nombre=nombre;
        this.precio = precio;
        this.comentario = comentario;
        this.imagen = imagen;
        this.cursos = new ArrayList<>();
    }

    public Favorito() {

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<ModelCursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<ModelCursos> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "id_curso=" + id_curso +
                ", id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", comentario='" + comentario + '\'' +
                ", imagen='" + imagen + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
