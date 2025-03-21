package br.inatel.dm112.client.runner;

import br.inatel.dm112.client.EmailClient;

public class EmailClientRunner {

    public static void main(String[] args) {
        EmailClient client = new EmailClient();

        String email = "teste@exemplo.com"; // Email de teste
        byte[] bytes = "Teste de e-mail sem anexo".getBytes();

        client.enviarEmail(email, bytes);
        System.out.println("Sucesso na chamada para envio do email.");
    }
}
