package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

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

	/*Ahora queremos hacer un metodo para obtener los valores de una
	* tabla de 'relatorio de ventas' en una sola consulta
	* la tabla tiene la estructura: |producto|cantidad vendida| ultima venta(fecha)|*/

	/*El nombre izquierdo es un auxiliar o alias y el derecho se debe llamar
	* exactamente como se tiene en las entidades (clases)
	* -> item.cantidad <- item se puede reemplazar con lo que queramos
	* pero cantidad no se puede reemplazar porque así aparece enn la clase  */
	public List<Object[]> relatorioDeVentas(){
		String jpql = "SELECT producto.nombre," +
				"SUM(item.cantidad)," +
				"MAX(pedido.fecha) " +//máxima fecha, o bien la última
				"FROM Pedido pedido " +
				"JOIN pedido.items item " +
				"JOIN item.producto producto " +
				"GROUP BY producto.nombre " +
				"ORDER BY item.cantidad DESC";
		return em.createQuery(jpql,Object[].class).getResultList();
    }
	/*En el siguiente método se hace lo mismo que arriba pero con un VO
	* (valued object)*/
	public List<RelatorioDeVenta> relatorioDeVentasVO(){
		String jpql = "SELECT new com.latam.alura.tienda.vo.RelatorioDeVenta(" +
				"producto.nombre," +
				"SUM(item.cantidad)," +
				"MAX(pedido.fecha) " +//máxima fecha, o bien la última
				") " +
				"FROM Pedido pedido " +
				"JOIN pedido.items item " +
				"JOIN item.producto producto " +
				"GROUP BY producto.nombre " +
				"ORDER BY item.cantidad DESC";
		return em.createQuery(jpql,RelatorioDeVenta.class).getResultList();
	}
}
