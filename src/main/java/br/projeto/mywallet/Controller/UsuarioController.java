package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/criar-usuario")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.criarUsuario(usuario));
    }
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> loginUsuario(@RequestBody UsuarioDTO usuario) throws Exception{
        return ResponseEntity.ok(usuarioService.login(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
}
