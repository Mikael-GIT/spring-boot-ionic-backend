package com.mikael.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
      
    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public OrderItem() {
    }

    public Double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    public OrderItem(Order pedido, Product produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getPedido();
    }

    public void setOrder(Order order){
        id.setPedido(order);
    }

    public void setProduct(Product product){
        id.setProduto(product);
    }

    public Product getProduct(){
        return id.getProduto();
    }

    public OrderItemPK getId() {
        return this.id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItem)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}