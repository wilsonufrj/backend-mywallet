package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Mappers.UsuarioMapper;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Service.IUsuarioService;
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

        usuarioDTO.setCarteiras(new HashSet<>());

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        return usuarioMapper
                .toDTO(usuarioRepository.save(usuario));
    }
//
//    @Override
//    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado) {
//
//        UsuarioDTO usuarioDTO = buscarPorId(id);
//
//        usuarioDTO.setNome(usuarioAtualizado.getNome());
//        usuarioDTO.setDataNascimento(usuarioAtualizado.getDataNascimento());
//        usuarioDTO.setEmail(usuarioAtualizado.getEmail());
//        usuarioDTO.setSenha(usuarioAtualizado.getSenha());
//        usuarioDTO.setCarteiras(usuarioAtualizado.getCarteiras());
//
//        return usuarioMapper
//                .toDTO(usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO)));
//    }

    @Override
    public void deletarUsuario(Long id) {

        UsuarioDTO usuarioDTO = buscarPorId(id);

        usuarioRepository.delete(usuarioMapper.toEntity(usuarioDTO));
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
    public UsuarioDTO login(UsuarioDTO usuarioDTO) throws Exception {
        
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNome().equals(usuarioDTO.getNome())
                        && usuario.getSenha().equals(usuarioDTO.getSenha()))
                .findFirst()
                .map(usuarioMapper::toDTO)
                .orElseThrow(()-> new Exception("Usuario não encontrado"));
                

    }
}
