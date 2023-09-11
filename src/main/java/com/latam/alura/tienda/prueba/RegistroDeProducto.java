package com.latam.alura.tienda.prueba;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		/*Producto celular= new Producto(
				"Samsung",
				"telefono usado", 
				new BigDecimal("1000"),
				celulares);
		*/
		
		/*Con esto se hace la conexion a la DB como se hacia 
		 * con JDBC de forma manual, JPA ya tiene las clases
		 * para no hacer la conexion tan manualmente*/
		
	    EntityManager em = JPAUtils.getEntityManager();
	    
	    /*Se abre una conexion em antes de los DAO
	     * para que después se hagan las correspondientes
	     * transacciones con la misma conexion */
	    
	    /*
	    ProductoDao productoDao = new ProductoDao(em);
	    CategoriaDao categoriaDao = new CategoriaDao(em);
	    */
	    
	    em.getTransaction().begin();
	    
	    /*
	    productoDao.guardar(celular);
	    categoriaDao.guardar(celulares);
	    */
	    em.persist(celulares);
	    celulares.setNombre("LIBROS");
	    
	    em.flush();
	    em.clear();
	    
	    celulares = em.merge(celulares);
	    celulares.setNombre("SOFTWARES");
	    
	    em.flush();
	    
	    em.remove(celulares);
	    em.flush();
	    em.clear();
	    
	    /*Las entidades que se encuentran dentro de 
	     * el bloque persist(); --- clear();
	     * se encuentran en estado managed
	     * Después de cada clear(); se puede traer la entidad 
	     * desde la base de datos con un merge();
	     * El metodo flush no cambia el estado managed
	     * a detached();*/
	}

}
