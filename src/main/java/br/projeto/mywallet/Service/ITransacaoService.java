package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Transacao;
import java.util.List;

public interface ITransacaoService {
    TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO) throws Exception;
    TransacaoDTO atualizarTransacao(Long id, TransacaoDTO transacaoAtualizadaDTO);
    void deletarTransacao(Long id);
    TransacaoDTO buscarPorId(Long id);
    List<TransacaoDTO> listarTodas();
}
