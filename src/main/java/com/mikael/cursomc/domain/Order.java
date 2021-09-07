package com.mikael.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "order_table")
public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Payment pagamento;

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Client cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Address enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<OrderItem> itens = new HashSet<>();

    public Order() {
    }


    public Order(Integer id, Date instante, Client cliente, Address enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return this.instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Payment getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(Payment pagamento) {
        this.pagamento = pagamento;
    }

    public Client getCliente() {
        return this.cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Address getEnderecoDeEntrega() {
        return this.enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Address enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }


    public Set<OrderItem> getItens() {
        return this.itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}