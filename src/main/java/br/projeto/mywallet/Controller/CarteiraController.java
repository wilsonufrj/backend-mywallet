package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.Service.ICarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RestController
@RequestMapping("/api/carteira")
public class CarteiraController {

    @Autowired
    private ICarteiraService carteiraService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<CarteiraDTO> criarCarteira(@PathVariable Long usuarioId, @RequestBody CarteiraDTO carteiraDTO) throws Exception {
        return ResponseEntity.ok(carteiraService.criarCarteira(usuarioId, carteiraDTO));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<CarteiraDTO>> buscaCarteirasPorIDUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carteiraService.buscaCarteiraPorIDUsuario(idUsuario));
    }

    @GetMapping("/{idCarteira}")
    public ResponseEntity<CarteiraDTO> getCarteiraPorId(@PathVariable Long idCarteira) throws Exception {
        return ResponseEntity.ok(carteiraService.buscaCarteira(idCarteira));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarteira(@PathVariable Long id) throws Exception {
        carteiraService.deletarCarteira(id);
        return ResponseEntity.noContent().build();
    }
}
