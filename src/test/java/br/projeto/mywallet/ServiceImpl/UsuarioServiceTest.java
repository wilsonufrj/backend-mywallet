package br.projeto.mywallet.ServiceImpl;

import br.projeto.mywallet.DTO.LoginDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Model.Usuario;
import br.projeto.mywallet.Mappers.UsuarioMapper;
import br.projeto.mywallet.repository.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDTO criarUsuarioDTO(Long id, String nome, String email) {
        return new UsuarioDTO(
                id,
                nome,
                LocalDate.of(1990, 1, 1),
                email,
                new HashSet<>()
        );
    }

    private Usuario criarUsuario(Long id, String nome, String email, String senha) {
        return new Usuario(
                id,
                nome,
                LocalDate.of(1990, 1, 1),
                email,
                senha,
                new HashSet<>()
        );
    }

    private LoginDTO criarLoginDTO(String nome, String senha) {
        return new LoginDTO(nome, senha);
    }

    @Test
    void criarUsuario_DeveRetornarUsuarioDTO_QuandoSucesso() {
        // Arrange
        UsuarioDTO dto = criarUsuarioDTO(null, "User Test", "test@email.com");
        Usuario usuarioSalvo = criarUsuario(1L, "User Test", "test@email.com", "senha123");
        UsuarioDTO dtoSalvo = criarUsuarioDTO(1L, "User Test", "test@email.com");

        when(usuarioMapper.toEntity(dto)).thenReturn(new Usuario());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);
        when(usuarioMapper.toDTO(usuarioSalvo)).thenReturn(dtoSalvo);

        // Act
        UsuarioDTO resultado = usuarioService.criarUsuario(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("User Test", resultado.getNome());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void deletarUsuario_DeveChamarDeleteById_QuandoMetodoForChamado() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        usuarioService.deletarUsuario(id);

        // Assert
        verify(usuarioRepository).deleteById(id); // Verifica se deleteById foi chamado
        verify(usuarioRepository, never()).findById(any()); // Garante que findById não é chamado
        verify(usuarioRepository, never()).delete(any()); // Garante que delete não é chamado
    }

    @Test
    void buscarPorId_DeveRetornarUsuarioDTO_QuandoEncontrado() {
        // Arrange
        Long id = 1L;
        Usuario usuario = criarUsuario(id, "User Test", "test@email.com", "senha123");
        UsuarioDTO dto = criarUsuarioDTO(id, "User Test", "test@email.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(dto);

        // Act
        UsuarioDTO resultado = usuarioService.buscarPorId(id);

        // Assert
        assertEquals(dto, resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoNaoEncontrado() {
        // Arrange
        Long id = 99L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> usuarioService.buscarPorId(id));
    }

    @Test
    void listarTodos_DeveRetornarListaVazia_QuandoNaoHouverUsuarios() {
        // Arrange
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<UsuarioDTO> resultado = usuarioService.listarTodos();

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void listarTodos_DeveRetornarListaComUsuarios_QuandoExistirem() {
        // Arrange
        Usuario usuario = criarUsuario(1L, "User 1", "user1@email.com", "senha123");
        UsuarioDTO dto = criarUsuarioDTO(1L, "User 1", "user1@email.com");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(dto);

        // Act
        List<UsuarioDTO> resultado = usuarioService.listarTodos();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals(dto, resultado.get(0));
    }

    @Test
    void login_DeveRetornarUsuarioDTO_QuandoCredenciaisCorretas() throws Exception {
        // Arrange
        String nome = "User Test";
        String senha = "senha123";
        LoginDTO loginDTO = criarLoginDTO(nome, senha);
        Usuario usuario = criarUsuario(1L, nome, "test@email.com", senha);
        UsuarioDTO dto = criarUsuarioDTO(1L, nome, "test@email.com");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(dto);

        // Act
        UsuarioDTO resultado = usuarioService.login(loginDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(nome, resultado.getNome());
    }

    @Test
    void login_DeveLancarExcecao_QuandoSenhaIncorreta() {
        // Arrange
        String nome = "User Test";
        LoginDTO loginDTO = criarLoginDTO(nome, "senhaErrada");
        when(usuarioRepository.findAll()).thenReturn(List.of(
                criarUsuario(1L, nome, "test@email.com", "senha123")
        ));

        // Act & Assert
        assertThrows(Exception.class, () -> usuarioService.login(loginDTO));
    }

    @Test
    void login_DeveLancarExcecao_QuandoUsuarioNaoExiste() {
        // Arrange
        LoginDTO loginDTO = criarLoginDTO("NomeInexistente", "senha");
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(Exception.class, () -> usuarioService.login(loginDTO));
    }

    @Test
    void login_DeveLancarExcecao_QuandoMultiplosUsuariosComMesmoNome() {
        // Arrange
        String nome = "User Duplicado";
        LoginDTO loginDTO = criarLoginDTO(nome, "senha123");
        when(usuarioRepository.findAll()).thenReturn(List.of(
                criarUsuario(1L, nome, "test1@email.com", "senha123"),
                criarUsuario(2L, nome, "test2@email.com", "senha123")
        ));

        // Act & Assert
        assertThrows(Exception.class, () -> usuarioService.login(loginDTO));
    }
}