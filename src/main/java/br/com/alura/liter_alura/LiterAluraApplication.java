package br.com.alura.liter_alura;

import br.com.alura.liter_alura.principal.Main;
import br.com.alura.liter_alura.repositorio.AutorRepositorio;
import br.com.alura.liter_alura.repositorio.LivroRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepositorio livroRepositorio;

	@Autowired
	private AutorRepositorio autorRepositorio;

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(livroRepositorio, autorRepositorio, entityManager);
		main.menu();
	}
}
