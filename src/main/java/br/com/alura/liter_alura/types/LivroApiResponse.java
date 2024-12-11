package br.com.alura.liter_alura.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroApiResponse(
        @JsonAlias("title")String titulo,
        @JsonAlias("authors") AutoresApiResponse[] autores,
        @JsonAlias("languages") String[] idiomas,
        @JsonAlias("download_count") Long numeroDownloads
        ) {



}
