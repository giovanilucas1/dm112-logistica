package br.inatel.dm112.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.inatel.dm112.client.EmailClient;

@Service
public class LojaService {  

    @Autowired
    private EmailClient emailClient;

    public String processarCompra(String email, String metodoPagamento) {
        String mensagem = "Compra confirmada!\nMétodo de pagamento: " + metodoPagamento;

        // Envio do e-mail corretamente formatado
        emailClient.enviarEmail(email, mensagem.getBytes());

        return mensagem;
    }
}
