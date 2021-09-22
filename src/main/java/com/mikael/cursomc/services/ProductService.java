package com.mikael.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Product;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.repositories.ProductRepository;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id){
        Optional<Product> Product = repository.findById(id);
        return Product.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Page<Product> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categorias = categoryRepository.findAllById(ids);
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
}