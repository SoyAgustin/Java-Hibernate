package com.latam.alura.tienda.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoriaId implements Serializable {
    private String password;
    private String nombre;

    public CategoriaId() {
    }

    public CategoriaId(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
