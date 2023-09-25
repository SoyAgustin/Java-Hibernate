package com.latam.alura.tienda.modelo;

import javax.persistence.Entity;

@Entity
/*No usamos la anotación @Table porque es
* opcional, si se crea una tabla es con el
* nombre Libros, igual que el nombre de la
* clase.*/
public class Libros extends Producto{
    private String autor;
    private int paginas;

    public Libros() {
    }

    public Libros(String autor, int paginas) {
        this.autor = autor;
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
