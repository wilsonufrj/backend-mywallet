package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.FormaPagamentoDTO;
import br.projeto.mywallet.Service.IFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private IFormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> getAllFormaPagamentos(){
        return ResponseEntity.ok(formaPagamentoService.listarTodasFormasPagamento());
    }
}
