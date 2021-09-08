package com.mikael.cursomc.resources;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Order;
import com.mikael.cursomc.services.CategoryService;
import com.mikael.cursomc.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pedidos")
public class OrderResource {
    
    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<Order> listar(@PathVariable Integer id){
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}