package br.projeto.mywallet.Service.impl;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.repository.IResponsavelRepository;
import br.projeto.mywallet.repository.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResponsavelServiceTest {

    @Mock
    private IResponsavelRepository responsavelRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private ResponsavelService responsavelService;

    @Test
    void testCriarResponsavel() {
        // Placeholder for criarResponsavel test
    }

    @Test
    void testDeletarResponsavel() {
        // Placeholder for deletarResponsavel test
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
    void testToDto() {
        // Placeholder for toDto test
    }
}
