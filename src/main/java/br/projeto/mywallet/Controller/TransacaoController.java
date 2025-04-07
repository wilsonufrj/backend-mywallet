package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Transacao;
import br.projeto.mywallet.Service.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired
    private ITransacaoService transacaoService;

    @PostMapping("/{idMes}")
    public ResponseEntity<TransacaoDTO> criarTransacao(@RequestBody TransacaoDTO transacao, @PathVariable Long idMes) {
        try{
            return ResponseEntity.ok(transacaoService.criarTransacao(transacao,idMes));
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoDTO> atualizarTransacao(@PathVariable Long id, @RequestBody TransacaoDTO transacaoAtualizada) {
        return ResponseEntity.ok(transacaoService.atualizarTransacao(id, transacaoAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> listarTodas() {
        return ResponseEntity.ok(transacaoService.listarTodas());
    }
}
