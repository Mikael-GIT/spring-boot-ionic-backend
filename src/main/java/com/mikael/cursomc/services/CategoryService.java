package com.mikael.cursomc.services;

import java.util.Optional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public Category find(Integer id){
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category update(Category categoria){
        find(categoria.getId());
        return repository.save(categoria);
    }

    public Category insert(Category categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }
}