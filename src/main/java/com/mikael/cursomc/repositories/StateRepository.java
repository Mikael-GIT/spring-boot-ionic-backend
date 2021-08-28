package com.mikael.cursomc.repositories;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
    
}