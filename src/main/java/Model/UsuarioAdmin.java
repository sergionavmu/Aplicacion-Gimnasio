package Model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdmin {
    private String id;
    private String nom;
    private String cognoms;
    private String dataAlta;
    private List<Reserva> reserves;

    public UsuarioAdmin(String id, String nom, String cognoms, String dataAlta) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataAlta = dataAlta;
        this.reserves = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(String dataAlta) {
        this.dataAlta = dataAlta;
    }

    public List<Reserva> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserva> reserves) {
        this.reserves = reserves;
    }
}