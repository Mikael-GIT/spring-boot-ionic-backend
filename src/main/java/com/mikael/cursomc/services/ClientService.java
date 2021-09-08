package com.mikael.cursomc.services;

import java.util.Optional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Client;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.repositories.ClientRepository;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public Client find(Integer id){
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
}