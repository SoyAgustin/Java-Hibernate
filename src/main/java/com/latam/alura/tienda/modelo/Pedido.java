package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha =LocalDate.now();
	private BigDecimal valorTotal = new BigDecimal(0);

	/*Los elementos que sean ToOne se cargan de forma Eager lo que hace que
	* se carguen de forma anticipada, generando joins entre las tablas que
	* sean igual ToOne, por eso se cambia a Lazy, en donde esto no ocurre
	* y hace que la aplicación sea optimice mejor los procesos*/
	@ManyToOne(fetch=FetchType.LAZY) // Un cliente tiene muchos pedidos
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido",cascade=CascadeType.ALL)
	private List<ItemsPedido> items=new ArrayList<>();
	
	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Pedido() {}

	public void agregarItems(ItemsPedido item){
		item.setPedido(this);//con esto se relacionan las entidades pedido e items
		this.items.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getfecha() {
		return fecha;
	}

	public void setfecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
