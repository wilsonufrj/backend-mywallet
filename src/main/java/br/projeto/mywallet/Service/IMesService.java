package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.MesDTO;
import java.util.List;

public interface IMesService {
    MesDTO criarMes(MesDTO mesDTO) throws Exception;
    MesDTO atualizarMes(Long id, MesDTO mesDTO);
    void deletarMes(Long id);
    MesDTO buscarPorId(Long id);
    List<MesDTO> listarTodos();
}
