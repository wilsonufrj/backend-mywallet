package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.repository.ICarteiraRepository;
import br.projeto.mywallet.repository.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarteiraServiceTest {

    @Mock
    private ICarteiraRepository carteiraRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private CarteiraService carteiraService;

    @Test
    void testCriarCarteira() {
        // Placeholder for criarCarteira test
    }

    @Test
    void testDeletarCarteira() {
        // Placeholder for deletarCarteira test
    }

    @Test
    void testBuscaCarteiraPorIDUsuario() {
        // Placeholder for buscaCarteiraPorIDUsuario test
    }

    @Test
    void testBuscaCarteira() {
        // Placeholder for buscaCarteira test
    }

    @Test
    void testToDto() {
        // Placeholder for toDto test
    }
}
