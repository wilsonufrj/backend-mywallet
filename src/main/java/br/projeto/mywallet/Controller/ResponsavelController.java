package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.Service.IResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    @Autowired
    private IResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {
        return ResponseEntity.ok(responsavelService.criarResponsavel(responsavelDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listarTodosResponsaveis() {
        List<ResponsavelDTO> responsavelDTOListd = responsavelService.listarTodos();
        return ResponseEntity.ok(responsavelDTOListd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResponsavel(@PathVariable Long id) {
        responsavelService.deletarResponsavel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ResponsavelDTO>> listarTodosAssociadosAoUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(responsavelService.listarTodos(id));
    }
}
