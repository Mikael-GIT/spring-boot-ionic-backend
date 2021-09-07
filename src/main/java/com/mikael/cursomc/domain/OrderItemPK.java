package com.mikael.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderItemPK implements Serializable {
    public static final long serialVersionUID = 1L;  

    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Order pedido;
    
    @ManyToOne
    @JoinColumn(name="produto_id")
    private Product produto;


    public Order getPedido() {
        return this.pedido;
    }

    public void setPedido(Order pedido) {
        this.pedido = pedido;
    }

    public Product getProduto() {
        return this.produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItemPK)) {
            return false;
        }
        OrderItemPK orderItemPK = (OrderItemPK) o;
        return Objects.equals(pedido, orderItemPK.pedido) && Objects.equals(produto, orderItemPK.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}