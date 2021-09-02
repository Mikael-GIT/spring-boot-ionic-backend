package com.mikael.cursomc.resources;

import javax.websocket.server.PathParam;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Client;
import com.mikael.cursomc.services.CategoryService;
import com.mikael.cursomc.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientes")
public class ClientResource {
    
    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
        Client obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
}