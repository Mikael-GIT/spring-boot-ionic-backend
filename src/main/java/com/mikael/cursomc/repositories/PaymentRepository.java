package com.mikael.cursomc.repositories;
import com.mikael.cursomc.domain.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
    
}