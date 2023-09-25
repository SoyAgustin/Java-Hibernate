package com.latam.alura.tienda.modelo;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

/*En esta clase se hace el mapeo de una tabla (productos) de la DB
 * una entidad  JPA funciona como un espejo de una tabla en la DB*/
@Entity
/*Por defecto JPA entiende que el nombre de la clase es la
 * misma que el nombre de la tabla en la base de datos
 * en este caso en la db tenemos productos y la clase 
 * se llama producto, para solucionar esto se usa la 
 * anotación Table*/
@Table(name = "productos")
/*Normalmente los accesos a la base de datos se traran en las clases DAO, pero se pueden hacer
* consultas medinte namedquerys dentro de la propia entidad. Por ejemplo, en lugar de hacer la consulta
* en ProductoDao podemos hacerlo aqui en Producto usando NamedQuery
* en realidad no importa el nombre que se le ponga a la namedquery pero es buena práctica colocar en que
* entidad está declarada, en este caso Producto, asi que sería Producto.nombre*/
@NamedQuery(name="Producto.consultaDePrecio",query = "SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre")

/*La anotacion INheritance se utiliza para que
* se reconozca a las clases que heredan de esta y
* las junte en una única tabla, en este caso las
* entidades que heredan de Producto son Electronico
* y Libros.
* Con SINGLE_TABLE se crea la tabla productos con
* todos los atributos que están aqui junto con los
* que están en las heredadas o se puede usar
* JOINED para tener tablas separadas
* JOINED es mas lento que SINGLE_TABLE*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
	
	@ManyToOne(fetch=FetchType.LAZY) /*Tipo de relacionamiento: muchos productos tienen una misma categoria*/
	private Categoria categoria;
	
	
	
	public Producto() {
	}
	
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
