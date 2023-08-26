package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.latam.alura.tienda.modelo.Producto;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Producto celular = new Producto();
		celular.setNombre("Samsumg");
		celular.setDescripcion("Telefono usado");
		celular.setPrecio(new BigDecimal(1000));
		
		/*Con esto se hace la conexion a la DB como se hacia 
		 * con JDBC de forma manual, JPA ya tiene las clases
		 * para no hacer la conexion tan manualmente*/
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda"); //Este nombre se configuro en la configuracion persistence.xml 
		EntityManager em = factory.createEntityManager() ;
		
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}

}
