//package br.projeto.mywallet.ServiceImpl;
//
//import br.projeto.mywallet.DTO.CarteiraDTO;
//import br.projeto.mywallet.Model.Carteira;
//import br.projeto.mywallet.Model.Usuario;
//import br.projeto.mywallet.Mappers.CarteiraMapper;
//import br.projeto.mywallet.Service.impl.CarteiraService;
//import br.projeto.mywallet.repository.ICarteiraRepository;
//import br.projeto.mywallet.repository.IUsuarioRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CarteiraServiceTest {
//
//    @Mock
//    private ICarteiraRepository carteiraRepository;
//
//    @Mock
//    private IUsuarioRepository usuarioRepository;
//
//    @Mock
//    private CarteiraMapper carteiraMapper;
//
//    @InjectMocks
//    private CarteiraService carteiraService;
//
//    private Usuario usuario;
//    private Carteira carteira;
//    private CarteiraDTO carteiraDTO;
//
//    @BeforeEach
//    void setUp() {
//        usuario = new Usuario(1L, "Usuário Teste", LocalDate.now(), "teste@email.com", "senha123", new HashSet<>());
//        carteira = new Carteira(1L, "Carteira Teste", new HashSet<>(), new ArrayList<>());
//        carteiraDTO = new CarteiraDTO(1L, "Carteira Teste", new HashSet<>(), new ArrayList<>());
//    }
//
//    @Test
//    void criarCarteira_DeveRetornarCarteiraDTO_QuandoSucesso() throws Exception {
//        // Arrange
//        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
//        when(carteiraMapper.toEntity(carteiraDTO)).thenReturn(carteira);
//        when(carteiraRepository.save(any(Carteira.class))).thenReturn(carteira);
//        when(carteiraMapper.toDTO(carteira)).thenReturn(carteiraDTO);
//
//        // Act
//        CarteiraDTO resultado = carteiraService.criarCarteira(1L, carteiraDTO);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(carteiraDTO, resultado);
//        verify(carteiraRepository).save(any(Carteira.class));
//    }
//
//    @Test
//    void criarCarteira_DeveLancarExcecao_QuandoUsuarioNaoEncontrado() {
//        // Arrange
//        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(Exception.class, () -> carteiraService.criarCarteira(1L, carteiraDTO));
//    }
//
//    @Test
//    void deletarCarteira_DeveDeletar_QuandoCarteiraExiste() throws Exception {
//        // Arrange
//        carteira.getUsuarios().add(usuario);
//        when(carteiraRepository.findById(1L)).thenReturn(Optional.of(carteira));
//        when(usuarioRepository.saveAll(anySet())).thenReturn(Collections.singletonList(usuario));
//
//        // Act
//        carteiraService.deletarCarteira(1L);
//
//        // Assert
//        verify(carteiraRepository).deleteById(1L);
//        verify(usuarioRepository).saveAll(anySet());
//    }
//
//    @Test
//    void deletarCarteira_DeveLancarExcecao_QuandoCarteiraNaoExiste() {
//        // Arrange
//        when(carteiraRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(Exception.class, () -> carteiraService.deletarCarteira(1L));
//    }
//
//    @Test
//    void buscaCarteiraPorIDUsuario_DeveRetornarLista_QuandoUsuarioTemCarteiras() {
//        // Arrange
//        carteira.getUsuarios().add(usuario);
//        when(carteiraRepository.findAll()).thenReturn(Collections.singletonList(carteira));
//        when(carteiraMapper.toDTO(carteira)).thenReturn(carteiraDTO);
//
//        // Act
//        List<CarteiraDTO> resultado = carteiraService.buscaCarteiraPorIDUsuario(1L);
//
//        // Assert
//        assertFalse(resultado.isEmpty());
//        assertEquals(1, resultado.size());
//        assertEquals(carteiraDTO, resultado.get(0));
//    }
//
//    @Test
//    void buscaCarteiraPorIDUsuario_DeveRetornarListaVazia_QuandoUsuarioNaoTemCarteiras() {
//        // Arrange
//        when(carteiraRepository.findAll()).thenReturn(Collections.singletonList(carteira));
//
//        // Act
//        List<CarteiraDTO> resultado = carteiraService.buscaCarteiraPorIDUsuario(2L);
//
//        // Assert
//        assertTrue(resultado.isEmpty());
//    }
//
//    @Test
//    void buscaCarteira_DeveRetornarCarteiraDTO_QuandoCarteiraExiste() throws Exception {
//        // Arrange
//        when(carteiraRepository.findById(1L)).thenReturn(Optional.of(carteira));
//        when(carteiraMapper.toDTO(carteira)).thenReturn(carteiraDTO);
//
//        // Act
//        CarteiraDTO resultado = carteiraService.buscaCarteira(1L);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(carteiraDTO, resultado);
//    }
//
//    @Test
//    void buscaCarteira_DeveLancarExcecao_QuandoCarteiraNaoExiste() {
//        // Arrange
//        when(carteiraRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(Exception.class, () -> carteiraService.buscaCarteira(1L));
//    }
//}