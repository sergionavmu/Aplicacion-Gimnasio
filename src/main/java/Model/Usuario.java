package Model;

import Utils.Genero;
import Utils.Pais;
import Utils.TipoIdentificacion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private static int idNext = 1;
    private int id;
    private String username;
    private String email;
    private String pass;
    private String cpass;
    private String tipo;
    private Date fechaAlta;
    private int admin;

    public Usuario(int id, String username, String email, String pass, String cpass, String tipo, int admin, Date fechaAlta) {
        this.id = idNext;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.cpass = cpass;
        this.fechaAlta= new Date();
        this.tipo = tipo;
        this.admin = admin;
        idNext++;
    }

    public Usuario(int id, String username, String email, String pass, int admin) {
        this.id = idNext;
        this.username = username;
        this.admin = admin;
        this.email = email;
        this.pass=pass;
        this.fechaAlta= new Date();

        idNext++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getAdmin() {
        return admin;
    }

    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Usuario() {

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", cpass='" + cpass + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", admin=" + admin +
                '}';
    }
}