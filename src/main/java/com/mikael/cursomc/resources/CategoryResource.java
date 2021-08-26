package com.mikael.cursomc.resources;

import com.mikael.cursomc.Category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {
    
    @RequestMapping(method = RequestMethod.GET)
    public String listar(){
        Category categoria1 = new Category(1, "Informática");
        Category categoria2 = new Category(2, "Escritório");
        return null;
    }
}