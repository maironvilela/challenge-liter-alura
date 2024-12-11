package br.com.alura.liter_alura.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataApiResponse(
        @JsonAlias("count") Integer count,
        @JsonAlias("results") LivroApiResponse[] livros
){


}
