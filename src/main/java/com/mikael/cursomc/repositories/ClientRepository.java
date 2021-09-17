package com.mikael.cursomc.repositories;


import com.mikael.cursomc.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    Client findByEmail(String email);
}