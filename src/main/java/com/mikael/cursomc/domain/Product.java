package com.mikael.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name="produto_id"),
    inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private List<Category> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto")
    private Set<OrderItem> itens = new HashSet<>();

    public Product() {
    }

    public Product(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Category> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(List<Category> categorias) {
        this.categorias = categorias;
    }


    public Set<OrderItem> getItens() {
        return this.itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }

    public List<Order> getOrders(){
        List<Order> lista = new ArrayList<>();
        for (OrderItem x : itens) {
            lista.add(x.getOrder());
        }
        return lista;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}