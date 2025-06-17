package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.*;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Service.IUsuarioService;
import br.projeto.mywallet.Utils.JwtUtil;
import br.projeto.mywallet.repository.IUsuarioRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCarteiras(new HashSet<>());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setResponsaveis(
                List.of(
                    new Responsavel(usuario.getNome(),new ArrayList<>(),usuario)
        ));

        return toDto(usuarioRepository.save(usuario));
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.map(UsuarioService::toDto)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado."));
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioService::toDto)
                .toList();
    }

    public AuthenticateDTO criarUsuarioELogar(UsuarioDTO usuarioDTO){
        try {

            UsuarioDTO auxUsuario = criarUsuario(usuarioDTO);

            Usuario usuario= usuarioRepository.findById(auxUsuario.getId())
                    .orElseThrow(()-> new Exception("O Usuario nao foi cadastrado"));

            return login(new LoginDTO(usuario.getNome(),usuario.getSenha()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public AuthenticateDTO login(LoginDTO loginDTO) throws Exception {

        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNome().equals(loginDTO.getNome())
                        && usuario.getSenha().equals(loginDTO.getSenha()))
                .findFirst()
                .map((usuario -> {

                    Long idResponsavelUsuario = usuario.getResponsaveis().stream()
                            .filter(responsavel-> responsavel.getNome().equals(usuario.getNome()))
                            .findFirst()
                            .get()
                            .getId();

                    return new AuthenticateDTO(
                            JwtUtil.generateToken(usuario.getNome()),
                            usuario.getNome(),
                            usuario.getId(),
                            idResponsavelUsuario
                    );
                }))
                .orElseThrow(() -> new Exception("Credenciais Inválidas"));

    }

    public static UsuarioInfo toInfo(Usuario usuario){
        return new UsuarioInfo(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getEmail()
        );
    }

    public static UsuarioDTO toDto(Usuario usuario){

        Set<CarteiraDTO> carteiras = usuario.getCarteiras()
                .stream()
                .map(CarteiraService::toDto)
                .collect(Collectors.toSet());

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getSenha(),
                usuario.getEmail(),
                carteiras
        );
    }
}
