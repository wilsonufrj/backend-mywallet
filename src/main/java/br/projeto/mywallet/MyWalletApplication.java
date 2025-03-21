package br.projeto.mywallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.projeto.mywallet") // Garante que os mapeadores sejam encontrados
public class MyWalletApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(MyWalletApplication.class, args);                
	}

}
