package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.TipoTransacaoDTO;
import java.util.List;

public interface ITipoTransacaoService {
    TipoTransacaoDTO criarTipoTransacao(TipoTransacaoDTO tipoTransacaoDTO);
    TipoTransacaoDTO atualizarTipoTransacao(Long id, TipoTransacaoDTO tipoTransacaoDTO);
    void deletarTipoTransacao(Long id);
    TipoTransacaoDTO buscarPorId(Long id);
    List<TipoTransacaoDTO> listarTodos();
}
