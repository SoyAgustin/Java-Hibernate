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
	private LocalDate Fecha =LocalDate.now();
	private BigDecimal valorTotal;
	
	@ManyToOne // Un cliente tiene muchos pedidos
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")//Ya se tiene la tabla items_pedido y no queremos que se genere otra tabla
	private List<ItemsPedido> items=new ArrayList<>();
	
	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Pedido() {}

	public void agregarItems(ItemsPedido item){
		item.setPedido(this);//con esto se relacionan las entidades pedido e items
		this.items.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
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
