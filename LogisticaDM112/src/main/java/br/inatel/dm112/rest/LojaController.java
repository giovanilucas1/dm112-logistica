package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.inatel.dm112.services.LojaService;

@RestController
@RequestMapping("/api/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @PostMapping("/comprar")
    public String comprarProduto(@RequestParam String email, @RequestParam String metodoPagamento) {
        return lojaService.processarCompra(email, metodoPagamento);
    }
}
