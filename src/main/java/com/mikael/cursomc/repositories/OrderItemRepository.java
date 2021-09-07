package com.mikael.cursomc.repositories;


import com.mikael.cursomc.domain.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
    
}