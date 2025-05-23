package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.AuthenticateDTO;
import br.projeto.mywallet.DTO.LoginDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO);

    void deletarUsuario(Long id) ;

    AuthenticateDTO criarUsuarioELogar(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarPorId(Long id);

    AuthenticateDTO login(LoginDTO usuarioDTO) throws Exception;

    List<UsuarioDTO> listarTodos();
}
