package com.mikael.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.mikael.cursomc.domain.Client;
import com.mikael.cursomc.domain.dtos.ClientDTO;
import com.mikael.cursomc.repositories.ClientRepository;
import com.mikael.cursomc.services.exceptions.DataIntegrityException;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public Client find(Integer id){
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client update(Client categoria){
        Client newObj = find(categoria.getId());
        updateData(newObj, categoria); //Método auxiliar para atualizar apenas o nome e email recebendo o objeto do banco e o objeto que novo que foi
        //passado na requisição, neste método pegaremos o que vem do objeto da requisição e setaremos os valores no objeto que será atualizado no banco.
        return repository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException e ) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
        }
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    } 

    public Client fromDTO(ClientDTO objDTO){
        return new Client(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Client newObj, Client obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}