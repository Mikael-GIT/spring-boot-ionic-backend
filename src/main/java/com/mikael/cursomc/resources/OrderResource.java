package com.mikael.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Order;
import com.mikael.cursomc.services.CategoryService;
import com.mikael.cursomc.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<Void> adicionar(@Valid @RequestBody Order obj){
        System.out.println(obj.toString());
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri(); //Pega a URI do novo recurso que foi inserido no POST
        return ResponseEntity.created(uri).build(); //Retorna o HTTP Status 201 retornando a nova URI que passamos na linha anterior.
    }
}