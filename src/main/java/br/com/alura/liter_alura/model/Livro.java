package br.com.alura.liter_alura.model;

import br.com.alura.liter_alura.types.DataApiResponse;
import br.com.alura.liter_alura.types.LivroApiResponse;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "autor_id",  nullable = false)
    private Autor autor;

    public Livro() {
     }

    private Livro(String titulo, String idioma) {
        this.titulo = titulo;
        this.idioma = idioma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma;

    }

    public static Livro criarLivro(DataApiResponse apiResponse){
        var livro = apiResponse.livros()[0];
        return new Livro(livro.titulo(), livro.idiomas()[0]);
    }


}
