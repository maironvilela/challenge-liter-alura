package br.com.alura.liter_alura.repositorio;

import br.com.alura.liter_alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    //@Query("SELECT a FROM Ator WHERE a.anoFalecimento > :ano")
    //List<Autor> pesquisarAutoresVivos(Integer ano);
    List<Autor> findByAnoFalecimentoGreaterThanEqual(Integer ano);

}
