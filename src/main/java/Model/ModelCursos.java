package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelCursos {
    private static int idNext = 1;
    private int id;
    private String nombre;
    private float precio;
    private String fecha;
    private String comentarios;
    private int favorito;
    private String imagen;

    public ModelCursos() {
        this.id = idNext;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha= fecha;
        this.comentarios= comentarios;
        this.favorito = favorito;
        this.imagen = imagen;
        idNext++;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public static int getIdNext() {
        return idNext;
    }

    public static void setIdNext(int idNext) {
        ModelCursos.idNext = idNext;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


    @Override
    public String toString() {
        return "ModelCursos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", fecha='" + fecha + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", favorito=" + favorito +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
