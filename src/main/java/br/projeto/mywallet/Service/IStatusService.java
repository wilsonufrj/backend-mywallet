package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.StatusDTO;
import java.util.List;

public interface IStatusService {
    StatusDTO criarStatus(StatusDTO status);
    StatusDTO atualizarStatus(Long id, StatusDTO status);
    void deletarStatus(Long id);
    StatusDTO buscarPorId(Long id);
    List<StatusDTO> listarTodos();
}
