package com.mikael.cursomc.services;

import java.util.Optional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Order;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.repositories.OrderRepository;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public Order buscar(Integer id){
        Optional<Order> order = repository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }
}