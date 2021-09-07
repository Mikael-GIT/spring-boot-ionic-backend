package com.mikael.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikael.cursomc.domain.enums.PaymentState;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Order pedido;


    public Payment() {
    }


    public Payment(Integer id, PaymentState estado, Order pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getEstado() {
        return PaymentState.toEnum(estado);
    }

    public void setEstado(PaymentState estado) {
        this.estado = estado.getCod();
    }

    public Order getPedido() {
        return this.pedido;
    }

    public void setPedido(Order pedido) {
        this.pedido = pedido;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Payment)) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}