package com.latam.alura.tienda.modelo;


import javax.persistence.Embeddable;
import java.io.Serializable;

/*Esta clase se va a inyectar en la clase Cliente
* es decir, queremos separar en dos entidades los
* datos personales de los clientes pero sin crear
* una nueva clase*/
@Embeddable
/*Las entidades que usen @Embeddable deben implementar
* la clase Serializable*/
public class DatosPersonales implements Serializable {
    private String nombre;
    private String dni;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public DatosPersonales() {
    }

    public DatosPersonales(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
}
