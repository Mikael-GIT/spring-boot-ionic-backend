package com.mikael.cursomc.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.mikael.cursomc.domain.Client;

import org.hibernate.validator.constraints.Length;


public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="E-mail Inválido")
    private String email;


    public ClientDTO() {
    }

    public ClientDTO(Client obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}