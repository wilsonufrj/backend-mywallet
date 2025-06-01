package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.BalancoDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.Service.IMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mes")
public class MesController {

    @Autowired
    private IMesService mesService;

    @PostMapping
    public ResponseEntity<MesDTO> criarMes(@RequestBody MesDTO mesDTO) throws Exception{
        return ResponseEntity.ok(mesService.criarMes(mesDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesDTO> selecionaMes(@PathVariable Long id){
        return ResponseEntity.ok(mesService.buscarPorId(id));
    }

    @GetMapping("balanco/{id}")
    public ResponseEntity<BalancoDTO> getBalancoMes(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(mesService.balancoMes(id));
    }

    @PutMapping("investimento/{id}")
    public ResponseEntity<Integer> atualizaPorcentagemInvestimento(@RequestBody Integer porcentagemInvestimento,@PathVariable Long id){
        return ResponseEntity.ok(mesService.atualizaPorcentagemMes(porcentagemInvestimento,id).getPorcentagemInvestimento());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMes(@PathVariable Long id) {
        mesService.deletarMes(id);
        return ResponseEntity.noContent().build();
    }
}
