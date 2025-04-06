package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.LoginDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO);

    void deletarUsuario(Long id) ;

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO login(LoginDTO usuarioDTO) throws Exception;

    List<UsuarioDTO> listarTodos();
}
