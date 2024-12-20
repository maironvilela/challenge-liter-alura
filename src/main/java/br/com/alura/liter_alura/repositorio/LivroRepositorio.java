package br.com.alura.liter_alura.repositorio;

import br.com.alura.liter_alura.model.Autor;
import br.com.alura.liter_alura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT l.idioma, COUNT(l) AS quantidade FROM Livro l GROUP BY l.idioma")
    List<Object[]> findLivrosCountGroupedByIdioma();

}
