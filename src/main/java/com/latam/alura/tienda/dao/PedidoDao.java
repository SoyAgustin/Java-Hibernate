package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> consultarTodos(){
		String jpql = "SELECT P FROM Pedido AS P";
		return em.createQuery(jpql,Pedido.class).getResultList();
	}
	
	public BigDecimal valorTotalVendido(){
		/*Aqui usamos la función SUM que retorna la suma
		* y la operación se hace dentro de la base de datos*/
		String jpql="SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}

	public Double valorPromedioVendido(){
		String jpql= "SELECT AVG(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,Double.class).getSingleResult();
	}
	/*la sintaxis del metodo createQuery recibe como primer
	 * parametro la consulta jpql y como segundo el tipo 
	 * de retorno.*/
}
