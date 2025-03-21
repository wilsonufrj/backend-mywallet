package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.Mappers.BancoMapper;
import br.projeto.mywallet.Model.Banco;
import br.projeto.mywallet.Service.IBancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

    @Autowired
    private IBancoService bancoService;

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> buscarBancoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bancoService.buscarBancoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<BancoDTO>> listarBancos() {
        return ResponseEntity.ok(bancoService.listarTodosBancos());
    }



}
