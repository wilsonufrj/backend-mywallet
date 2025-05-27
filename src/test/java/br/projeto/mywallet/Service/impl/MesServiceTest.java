package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.repository.ICarteiraRepository;
import br.projeto.mywallet.repository.IMesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MesServiceTest {

    @Mock
    private IMesRepository mesRepository;

    @Mock
    private ICarteiraRepository carteiraRepository;

    @InjectMocks
    private MesService mesService;

    @Test
    void testCriarMes() {
        // Placeholder for criarMes test
    }

    @Test
    void testDeletarMes() {
        // Placeholder for deletarMes test
    }

    @Test
    void testBuscarPorId() {
        // Placeholder for buscarPorId test
    }

    @Test
    void testListarTodos() {
        // Placeholder for listarTodos test
    }

    @Test
    void testBalancoMes() {
        // Placeholder for balancoMes test
    }

    @Test
    void testToDto() {
        // Placeholder for toDto test
    }
}
