package br.com.alura.liter_alura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T converterJsonParaClass(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, classe);
    }
}
