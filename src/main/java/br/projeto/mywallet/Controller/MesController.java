package br.projeto.mywallet.Controller;

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

    @PutMapping("/{id}")
    public ResponseEntity<MesDTO> atualizarMes(@PathVariable Long id, @RequestBody MesDTO mesDTO) {
        return ResponseEntity.ok(mesService.atualizarMes(id, mesDTO));
    }

    public ResponseEntity<MesDTO> selecionaMes(@PathVariable Long id){
        return ResponseEntity.ok(mesService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMes(@PathVariable Long id) {
        mesService.deletarMes(id);
        return ResponseEntity.noContent().build();
    }
}
