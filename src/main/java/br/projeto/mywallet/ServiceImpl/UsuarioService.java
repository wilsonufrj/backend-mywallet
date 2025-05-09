package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.AuthenticateDTO;
import br.projeto.mywallet.DTO.LoginDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Mappers.UsuarioMapper;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Service.IUsuarioService;
import br.projeto.mywallet.Utils.JwtUtil;
import br.projeto.mywallet.repository.IUsuarioRepository;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @Override
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setCarteiras(new HashSet<>());

        return usuarioMapper
                .toDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado."));
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Override
    public AuthenticateDTO login(LoginDTO loginDTO) throws Exception {

        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNome().equals(loginDTO.getNome())
                        && usuario.getSenha().equals(loginDTO.getSenha()))
                .findFirst()
                .map((usuario -> new AuthenticateDTO(
                        JwtUtil.generateToken(usuario.getNome()),
                        usuario.getNome(),
                        usuario.getId()
                )))
                .orElseThrow(() -> new Exception("Credenciais Inválidas"));

    }
}
