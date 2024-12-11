package br.com.alura.liter_alura.principal;

import br.com.alura.liter_alura.exeption.LivroJaExisteException;
import br.com.alura.liter_alura.repositorio.AutorRepositorio;
import br.com.alura.liter_alura.repositorio.LivroRepositorio;
import br.com.alura.liter_alura.service.AutorService;
import br.com.alura.liter_alura.service.LivroService;
import br.com.alura.liter_alura.types.DataApiResponse;
import br.com.alura.liter_alura.util.ConsumoApi;
import br.com.alura.liter_alura.util.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Scanner;

public class Main {

     private EntityManager entityManager;


    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);

     private LivroRepositorio livroRepositorio;
     private AutorRepositorio autorRepositorio;

     public Main(LivroRepositorio livroRepositorio, AutorRepositorio autorRepositorio, EntityManager entityManager){
         this.autorRepositorio = autorRepositorio;
         this.livroRepositorio = livroRepositorio;
         this.entityManager = entityManager;
     }
    public void menu() throws JsonProcessingException {
        int opcaoMenu = -1;

        while(opcaoMenu !=9){
            System.out.println("Informa a opção desejada abaixo: ");
            System.out.println("""
                        1: Pesquisar pelo Titulo
                        9: Finalizar
                    """);
            opcaoMenu = leitura.nextInt();
            leitura.nextLine();

            switch(opcaoMenu){
                case 1:
                    pesquisarPeloTitulo();
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
        LivroService service = new LivroService(livroRepositorio, autorRepositorio);
        AutorService autorService = new AutorService(autorRepositorio);
         try {
             var nomeLivro = showMessage("Informe o nome do livro: ");
             var baseUrl = "https://gutendex.com/books/?search="+nomeLivro;
             var json = ConsumoApi.obterDados(baseUrl.replace(" ", "%20"));
             var responseData = converteDados.converterJsonParaClass(json, DataApiResponse.class);

             if (responseData.count() != 0) {
                 service.savarLivro(responseData);
             } else {
                 System.out.println("Livro informado ja existe");
             }
         }catch (LivroJaExisteException ex){
             System.out.println(ex.getMessage());
         }

    }

}
