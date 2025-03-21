package br.inatel.dm112;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.inatel.dm112")
public class LogisticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticaApplication.class, args);
        System.out.println("🚀 Aplicação de Logística iniciada com sucesso!");
    }
}
