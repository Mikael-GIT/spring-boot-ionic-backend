package com.mikael.cursomc.resources;

import java.util.List;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Product;
import com.mikael.cursomc.domain.dtos.ProductDTO;
import com.mikael.cursomc.resources.utils.URL;
import com.mikael.cursomc.services.CategoryService;
import com.mikael.cursomc.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/produtos")
public class ProductResource {
    
    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> listar(@PathVariable Integer id){
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Product> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}

}