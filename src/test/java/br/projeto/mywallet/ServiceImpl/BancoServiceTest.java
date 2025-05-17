//package br.projeto.mywallet.ServiceImpl;
//
//import br.projeto.mywallet.DTO.BancoDTO;
//import br.projeto.mywallet.Model.Banco;
//import br.projeto.mywallet.Service.impl.BancoService;
//import br.projeto.mywallet.repository.IBancoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class BancoDTOServiceTest {
//
//    @InjectMocks
//    private BancoService bancoService;
//
//    @Mock
//    private IBancoRepository bancoRepository;
//
//    private BancoDTO bancoDTO;
//
//    private Banco banco;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void criarBanco_DeveRetornarBancoDTO() {
//        // Arrange
//        BancoDTO bancoDTO = new BancoDTO();
//        bancoDTO.setNome("Banco Teste");
//        bancoDTO.setTransacoes(new ArrayList<>());
//
//        Banco banco = new Banco();
//        banco.setNome("Banco Teste");
//        banco.setTransacoes(new ArrayList<>());
//
//        when(bancoRepository.save(any(Banco.class))).thenReturn(banco);
//
//        // Act
//        BancoDTO resultado = bancoService.criarBanco(bancoDTO);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Banco Teste", resultado.getNome());
//        verify(bancoRepository, times(1)).save(any(Banco.class));
//    }
//
//    @Test
//    void buscarBancoPorId_QuandoExistir_DeveRetornarBancoDTO() {
//        // Arrange
//        Banco banco = new Banco();
//        banco.setNome("Banco Teste");
//        banco.setTransacoes(new ArrayList<>());
//
//        when(bancoRepository.findById(1L)).thenReturn(Optional.of(banco));
//
//        // Act
//        BancoDTO resultado = bancoService.buscarBancoPorId(1L);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Banco Teste", resultado.getNome());
//        verify(bancoRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void buscarBancoPorId_QuandoNaoExistir_DeveLancarExcecao() {
//        // Arrange
//        when(bancoRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> bancoService.buscarBancoPorId(1L));
//        verify(bancoRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void listarTodosBancos_DeveRetornarListaDeBancoDTO() {
//        // Arrange
//        Banco banco1 = new Banco();
//        banco1.setNome("Banco 1");
//        banco1.setTransacoes(new ArrayList<>());
//
//        Banco banco2 = new Banco();
//        banco2.setNome("Banco 2");
//        banco2.setTransacoes(new ArrayList<>());
//
//        List<Banco> bancos = List.of(banco1, banco2);
//
//        when(bancoRepository.findAll()).thenReturn(bancos);
//
//        // Act
//        List<BancoDTO> resultado = bancoService.listarTodosBancos();
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(2, resultado.size());
//        verify(bancoRepository, times(1)).findAll();
//    }
//
//    @Test
//    void atualizarBanco_DeveRetornarBancoDTOAtualizado() {
//        // Arrange
//        Banco banco = new Banco();
//        banco.setNome("Banco Original");
//        banco.setTransacoes(new ArrayList<>());
//
//        Banco bancoAtualizado = new Banco();
//        bancoAtualizado.setNome("Banco Atualizado");
//        bancoAtualizado.setTransacoes(new ArrayList<>());
//
//        BancoDTO bancoDTOAtualizado = new BancoDTO();
//        bancoDTOAtualizado.setNome("Banco Atualizado");
//        bancoDTOAtualizado.setTransacoes(new ArrayList<>());
//
//        when(bancoRepository.findById(1L)).thenReturn(Optional.of(banco));
//        when(bancoRepository.save(any(Banco.class))).thenReturn(bancoAtualizado);
//
//        // Act
//        BancoDTO resultado = bancoService.atualizarBanco(1L, bancoDTOAtualizado);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals("Banco Atualizado", resultado.getNome());
//        verify(bancoRepository, times(1)).findById(1L);
//        verify(bancoRepository, times(1)).save(any(Banco.class));
//    }
//
//    @Test
//    void atualizarBancoDTO_DeveLancarExcecaoQuandoBancoDTONaoEncontrado() {
//        BancoDTO bancoAtualizado = new BancoDTO();
//        bancoAtualizado.setNome("BancoDTO Atualizado");
//        bancoAtualizado.setTransacoes(new ArrayList<>());
//
//        when(bancoRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            bancoService.atualizarBanco(1L, bancoAtualizado);
//        });
//
//        assertEquals("Banco não encontrado com ID: 1", exception.getMessage());
//        verify(bancoRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void deletarBanco_DeveChamarDeleteNoRepository() {
//        // Arrange
//        Banco banco = new Banco();
//        banco.setNome("Banco Teste");
//        banco.setTransacoes(new ArrayList<>());
//
//        when(bancoRepository.findById(1L)).thenReturn(Optional.of(banco));
//        doNothing().when(bancoRepository).delete(any(Banco.class));
//
//        // Act
//        bancoService.deletarBanco(1L);
//
//        // Assert
//        verify(bancoRepository, times(1)).findById(1L);
//        verify(bancoRepository, times(1)).delete(any(Banco.class));
//    }
//}
