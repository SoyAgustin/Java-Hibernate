package com.latam.alura.tienda.prueba;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.*;
import com.latam.alura.tienda.utils.JPAUtils;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

public class RegistroDeProducto {

	public static void main(String[] args) throws FileNotFoundException {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();

		Categoria find = em.find(Categoria.class,new CategoriaId("CELULARES","456"));

		System.out.println(find.getNombre());
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		
		Producto celular= new Producto(
				"Samsung",
				"telefono usado", 
				new BigDecimal("1000"),
				celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
	    ProductoDao productoDao = new ProductoDao(em);
	    CategoriaDao categoriaDao = new CategoriaDao(em);
	    
	    em.getTransaction().begin();
	 
	    productoDao.guardar(celular);

	    categoriaDao.guardar(celulares);
	    
	    em.getTransaction().commit();


	    em.close();
	}

}
