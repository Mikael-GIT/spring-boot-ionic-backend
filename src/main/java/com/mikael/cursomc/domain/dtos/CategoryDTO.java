package com.mikael.cursomc.domain.dtos;

import java.io.Serializable;

import com.mikael.cursomc.domain.Category;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;


    public CategoryDTO() {
    }


    public CategoryDTO(Category categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
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


}