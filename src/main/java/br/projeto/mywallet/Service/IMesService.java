package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.BalancoDTO;
import br.projeto.mywallet.DTO.MesDTO;
import java.util.List;

public interface IMesService {
    MesDTO criarMes(MesDTO mesDTO) throws Exception;
    MesDTO atualizaPorcentagemMes(Integer porcentagemMes, Long idMes);
    void deletarMes(Long id);
    MesDTO buscarPorId(Long id);
    List<MesDTO> listarTodos();

    BalancoDTO balancoMes(Long id) throws Exception;
}
