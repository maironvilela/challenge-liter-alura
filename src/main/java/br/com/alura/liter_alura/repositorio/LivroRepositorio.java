package br.com.alura.liter_alura.repositorio;

import br.com.alura.liter_alura.model.Autor;
import br.com.alura.liter_alura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
}
