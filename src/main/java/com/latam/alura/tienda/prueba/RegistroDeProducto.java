package com.latam.alura.tienda.prueba;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.*;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) throws FileNotFoundException {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
	    ProductoDao productoDao = new ProductoDao(em);
	    Producto producto = productoDao.consultaPorId(1l);//como el id es autogenerado, el primer valor generado es 1 
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		Cliente cliente = new Cliente("Juan","Jepa00225");
		Pedido pedido = new Pedido(cliente);

	    pedido.agregarItems( new ItemsPedido(4,producto,pedido));

		em.getTransaction().begin();
		clienteDao.guardar(cliente);
		pedidoDao.guardar(pedido);

		em.getTransaction().commit();
		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		System.out.println("Valor total: "+valorTotal);
	    
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
