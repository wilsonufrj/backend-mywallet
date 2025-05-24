package br.projeto.mywallet.Controller;

import br.projeto.mywallet.DTO.AuthenticateDTO;
import br.projeto.mywallet.DTO.LoginDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth")
public class AutenticacaoController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody LoginDTO usuario) throws Exception{
        try{
            return ResponseEntity.ok(usuarioService.login(usuario));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity criarUsuarioELoga(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.criarUsuarioELogar(usuario));
    }
}
