package com.mikael.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mikael.cursomc.domain.enums.PaymentState;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;


    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, PaymentState estado, Order pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }


    public Integer getNumeroDeParcelas() {
        return this.numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

}