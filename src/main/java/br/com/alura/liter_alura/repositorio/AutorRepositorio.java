package br.com.alura.liter_alura.repositorio;

import br.com.alura.liter_alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
}
