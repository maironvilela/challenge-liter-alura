package br.com.alura.liter_alura.service;

import br.com.alura.liter_alura.exeption.LivroJaExisteException;
import br.com.alura.liter_alura.model.Autor;
import br.com.alura.liter_alura.model.Livro;
import br.com.alura.liter_alura.repositorio.AutorRepositorio;
import br.com.alura.liter_alura.repositorio.LivroRepositorio;
import br.com.alura.liter_alura.types.DataApiResponse;
import br.com.alura.liter_alura.types.LivroApiResponse;
import br.com.alura.liter_alura.types.LivroDTO;
import br.com.alura.liter_alura.util.ConsumoApi;
import br.com.alura.liter_alura.util.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class LivroService {





     private LivroRepositorio livroRepositorio;
     private AutorRepositorio autorRepositorio;

      public LivroService(LivroRepositorio livroRepositorio, AutorRepositorio autorRepositorio){
         this.livroRepositorio = livroRepositorio;
         this.autorRepositorio = autorRepositorio;
      }



    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);


    public Optional<Livro> pesquisarLivro(String titulo, Autor autor){
        return this.livroRepositorio.findByTitulo(titulo);
    }


    public Livro savarLivro(DataApiResponse data){


        var optionalLivroSalvo = this.livroRepositorio.findByTitulo(data.livros()[0].titulo());

        if(optionalLivroSalvo.isPresent()){
            throw new LivroJaExisteException("Livro '"+data.livros()[0].titulo()+"' ja existe", 400);
        }

        Autor autor = this.autorRepositorio.findByNome(data.livros()[0].autores()[0].nome())
                .orElseGet(() -> {
                      Autor novoAutor = Autor.create(data);
                    return autorRepositorio.save(novoAutor);
                });


        var livro = Livro.criarLivro(data);
        livro.setAutor(autor);
      return this.livroRepositorio.save(livro);

     }
}
