package br.inatel.dm112.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class EmailClient {

    @Value("${utility.rest.email.url}")
    private String restURL;

    private String mailEndpoint = "/mail";

	public void enviarEmail(String email, byte[] content) {
		System.out.println("Simulando envio de email para: " + email);
		System.out.println("Conteúdo do e-mail: " + new String(content));
	}
	
}
