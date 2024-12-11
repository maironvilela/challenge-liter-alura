package br.com.alura.liter_alura.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoresApiResponse(
       @JsonAlias("name") String nome,
       @JsonAlias("birth_year") Integer anaNascimento,
       @JsonAlias("death_year") Integer anoFalecimento
) {
}
