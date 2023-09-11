package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*En esta clase se hace el mapeo de una tabla (productos) de la DB
 * una entidad  JPA funciona como un espejo de una tabla en la DB*/
@Entity
/*Por defecto JPA entiende que el nombre de la clase es la
 * misma que el nombre de la tabla en la base de datos
 * en este caso en la db tenemos productos y la clase 
 * se llama producto, para solucionar esto se usa la 
 * anotación Table*/
@Table(name = "productos")
public class Producto {
	/*En principio estos atributos son los nombres de las
	 * columnas de la tabla*/
	
	@Id//Con estas anoteciones configuramos la llave primaria 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//el generationType depende de la base de datos, en este caso H2, se puede poner en AUTO
	private Long id;
	//@column(name="nombres") //En caso de que los nombres  sean diferentes se usa la anotación column
	private String nombre; 
	private String descripcion; 
	private BigDecimal precio;
	private LocalDate fechaDeRegistro = LocalDate.now();
	@Enumerated(EnumType.STRING) /*Si no se coloca esto la categoria se guarda como entero (la posición en el enum)*/
	private Categoria categoria;
	
	
	
	public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	} 

}
