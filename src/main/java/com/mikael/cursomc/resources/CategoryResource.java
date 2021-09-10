package com.mikael.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.dtos.CategoryDTO;
import com.mikael.cursomc.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {
    
    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
        Category obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/")
    public ResponseEntity<List<CategoryDTO>> listarTodos(){
        List<Category> categorias = service.findAll();
        List<CategoryDTO> categoriasDTO = categorias.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriasDTO);
    }

    @GetMapping(value="/page")
    public ResponseEntity<Page<CategoryDTO>> listarPorPagina(
        @RequestParam(value="page", defaultValue = "0") Integer page, 
        @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
        @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
        @RequestParam(value="direction", defaultValue = "ASC") String direction){
        Page<Category> categorias = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> categoriasDTO = categorias.map(obj -> new CategoryDTO(obj));
        return ResponseEntity.ok().body(categoriasDTO);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody CategoryDTO objDTO, @PathVariable Integer id) {
        Category categoria = service.fromDTO(objDTO);
        categoria.setId(id);
        service.update(categoria);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Void> adicionar(@Valid @RequestBody CategoryDTO objDTO){
        Category obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri(); //Pega a URI do novo recurso que foi inserido no POST
        return ResponseEntity.created(uri).build(); //Retorna o HTTP Status 201 retornando a nova URI que passamos na linha anterior.
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}