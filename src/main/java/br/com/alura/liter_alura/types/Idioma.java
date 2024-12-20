package br.com.alura.liter_alura.types;

public enum Idioma {

    PT("pt", "Português"),
    ZH("zh", "Chinês Mandarim"),
    ES("es", "Espanhol"),
    EN("en", "Inglês"),
    HI("hi", "Hindi"),
    AR("ar", "Árabe"),
    BN("bn", "Bengali"),
    RU("ru", "Russo"),
    PR("pt", "Português Brasileiro"),
    ID("id", "Indonésio"),
    DE("de", "Alemão"),
    JP("ja", "Japonês"),
    FR("fr", "Francês"),
    TR("tr", "Turco"),
    IT("it", "Italiano"),
    PL("pl", "Polonês"),
    UK("uk", "Ucraniano"),
    RO("ro", "Romeno"),
    NL("nl", "Holandês"),
    TH("th", "Tailandês"),
    KO("ko", "Coreano"),
    VI("vi", "Vietnamita"),
    EG("el", "Grego");

    private final String sigla;
    private final String descricao;

     Idioma(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

     public String getSigla() {
        return sigla;
    }

     public String getDescricao() {
        return descricao;
    }

     public static Idioma fromSigla(String sigla) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.getSigla().equalsIgnoreCase(sigla)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Sigla não reconhecida: " + sigla);
    }
}
