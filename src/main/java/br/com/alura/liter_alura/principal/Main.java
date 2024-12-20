package br.com.alura.liter_alura.principal;

import br.com.alura.liter_alura.exeption.LivroJaExisteException;
import br.com.alura.liter_alura.model.Autor;
import br.com.alura.liter_alura.repositorio.AutorRepositorio;
import br.com.alura.liter_alura.repositorio.LivroRepositorio;
import br.com.alura.liter_alura.service.AutorService;
import br.com.alura.liter_alura.service.LivroService;
import br.com.alura.liter_alura.types.DataApiResponse;
import br.com.alura.liter_alura.types.EstatisticasIdioma;
import br.com.alura.liter_alura.types.Idioma;
import br.com.alura.liter_alura.util.ConsumoApi;
import br.com.alura.liter_alura.util.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

     private final EntityManager entityManager;


    private final ConverteDados converteDados = new ConverteDados();
    private final Scanner leitura = new Scanner(System.in);

     private final LivroRepositorio livroRepositorio;
     private final AutorRepositorio autorRepositorio;

    private final LivroService livroService;
    private final AutorService autorService;




     public Main(LivroRepositorio livroRepositorio, AutorRepositorio autorRepositorio, EntityManager entityManager){
         this.autorRepositorio = autorRepositorio;
         this.livroRepositorio = livroRepositorio;
         this.entityManager = entityManager;

         livroService = new LivroService(livroRepositorio, autorRepositorio);
          autorService = new AutorService(autorRepositorio);
     }
    public void menu() throws JsonProcessingException {
        int opcaoMenu = -1;

        while(opcaoMenu !=9){
            System.out.println("Informa a opção desejada abaixo: ");
            System.out.println("""
                        1: Pesquisar pelo Titulo
                        2: Listar Autores
                        3: Listar Autores Vivos
                        4: Exibir Quantidade de Livros por idioma
                        9: Finalizar
                    """);
            opcaoMenu = leitura.nextInt();
            leitura.nextLine();

            switch(opcaoMenu){
                case 1:
                    pesquisarPeloTitulo();
                    break;
                case 2:
                    lilstarAutores();
                    break;
                case 3:
                    listarAutoresVivos();
                    break;

                case 4  :
                    ExibirQuantidadeDeLivrosPorIdioma();
                    break;

                case 9:
                     break;
            }

         }

        System.out.println("Finalizado");

     }

    private String showMessage(String mensagem){
         System.out.print(mensagem);
         return leitura.nextLine();
     }

    private void pesquisarPeloTitulo() throws JsonProcessingException {

         try {
             var nomeLivro = showMessage("Informe o nome do livro: ");
             var baseUrl = "https://gutendex.com/books/?search="+nomeLivro;
             var json = ConsumoApi.obterDados(baseUrl.replace(" ", "%20"));
             var responseData = converteDados.converterJsonParaClass(json, DataApiResponse.class);

             if (responseData.count() != 0) {
                 livroService.savarLivro(responseData);
             } else {
                 System.out.println("Livro informado ja existe");
             }
         }catch (LivroJaExisteException ex){
             System.out.println(ex.getMessage());
         }

    }

    private void lilstarAutores(){
        List<Autor> autores = this.autorService.pesquisarAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos(){
         var ano = Integer.parseInt(showMessage("Informe o ano desejado: "));
         List<Autor> autores = this.autorService.pesquisarAutoresVivos(ano);

         if(autores.isEmpty()){
             System.out.println("\nNenhum Autor encontrado\n");

         }else{
             var mensagem = autores.size() == 1?" Autor Encontrado\n":" Autores Encontrados\n";
             System.out.println("\n "+autores.size()+mensagem);
             autores.forEach(System.out::println);
         }
     }


     private void ExibirQuantidadeDeLivrosPorIdioma(){

         List<EstatisticasIdioma> estatisticasIdiomas = new ArrayList<>();
         var result = this.livroRepositorio.findLivrosCountGroupedByIdioma();

         System.out.println("\n");
         for (Object[] resultado : result) {

             String sigla = (String) resultado[0];
             Integer quantidade = Integer.parseInt(resultado[1].toString());
             String descricao = Idioma.fromSigla(sigla).getDescricao();

             System.out.println(sigla + "  - "+descricao);


             estatisticasIdiomas.add(new EstatisticasIdioma(sigla, quantidade, descricao));
          }

         var opcaoIdioma = showMessage("Informe a sigla do idioma desejado (Ex: pt para portugues): \n");

         var optionalIdiomaEscolhlido = estatisticasIdiomas.stream().filter(estatisticasIdioma ->  estatisticasIdioma.idioma().equalsIgnoreCase(opcaoIdioma)).findFirst();

         var idiomaEscolhido = optionalIdiomaEscolhlido.orElseThrow();

         System.out.printf("""
                        Idioma: %s
                        Quantidade de livros: %d
                 %n""", idiomaEscolhido.descricao(), idiomaEscolhido.quantidadeLivros());


     }
}

