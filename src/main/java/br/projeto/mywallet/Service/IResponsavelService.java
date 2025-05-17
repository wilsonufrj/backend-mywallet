package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.Model.Responsavel;
import java.util.List;

public interface IResponsavelService {
    ResponsavelDTO criarResponsavel(ResponsavelDTO responsavelDTO);
    void deletarResponsavel(Long id);
    ResponsavelDTO buscarPorId(Long id);
    List<ResponsavelDTO> listarTodos();
}
