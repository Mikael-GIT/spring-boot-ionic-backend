package com.mikael.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.dtos.CategoryDTO;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.services.exceptions.DataIntegrityException;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public Category find(Integer id){
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category update(Category categoria){
        Category newObj = find(categoria.getId());
        updateData(newObj, categoria); //Método auxiliar para atualizar apenas o nome e email recebendo o objeto do banco e o objeto que novo que foi
        //passado na requisição, neste método pegaremos o que vem do objeto da requisição e setaremos os valores no objeto que será atualizado no banco.
        return repository.save(newObj);
    }

    public Category insert(Category categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }
    
    public void delete(Integer id){
        find(id);
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException e ) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    } 

    public Category fromDTO(CategoryDTO objDTO){
        return new Category(objDTO.getId(), objDTO.getNome());
    }

    private void updateData(Category newObj, Category obj){
        newObj.setNome(obj.getNome());
    }
}