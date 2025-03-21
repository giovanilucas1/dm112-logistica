package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.inatel.dm112.model.EntregaStatus;
import br.inatel.dm112.services.LogisticaService;

import br.inatel.dm112.model.EntregaStatus;
import br.inatel.dm112.services.LogisticaService;

@RestController
@RequestMapping("/api/logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService service;

    @PostMapping("/confirmarEntrega/{cpf:.+}/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
	public EntregaStatus confirmarEntrega(@PathVariable("cpf") String cpf, @PathVariable("orderNumber") Integer orderNumber){
	System.out.println("Confirmação de entrega para CPF " + cpf + " e pedido " + orderNumber);
		return new EntregaStatus(EntregaStatus.ENTREGA_STATUS.SUCESSO.ordinal(), cpf, orderNumber);
	}
	

}
