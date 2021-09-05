package com.mikael.cursomc.repositories;
import com.mikael.cursomc.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}