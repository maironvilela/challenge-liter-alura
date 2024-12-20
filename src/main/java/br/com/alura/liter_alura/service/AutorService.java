package br.com.alura.liter_alura.service;


import br.com.alura.liter_alura.model.Autor;
import br.com.alura.liter_alura.repositorio.AutorRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {




    private AutorRepositorio autorRepositorio;

    public AutorService(AutorRepositorio autorRepositorio){
        this.autorRepositorio = autorRepositorio;
    }

    public Optional<Autor> findByName(String nome){
        return this.autorRepositorio.findByNome(nome);
    }

    public List<Autor> pesquisarAutores(){
        return this.autorRepositorio.findAll();
    }

    public List<Autor> pesquisarAutoresVivos(Integer ano){
        return this.autorRepositorio.findByAnoFalecimentoGreaterThanEqual(ano);
    }

}
