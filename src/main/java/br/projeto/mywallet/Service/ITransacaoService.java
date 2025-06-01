package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.TransacaoDTO;

import java.util.List;

public interface ITransacaoService {
    TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO, Long idMes) throws Exception;
    List<TransacaoDTO> editaTransacoes(List<TransacaoDTO> transacoesDTO, Long idMes) throws Exception;
    TransacaoDTO atualizarTransacao(Long id, TransacaoDTO transacaoAtualizadaDTO);
    void deletarTransacao(Long id);
    TransacaoDTO buscarPorId(Long id);
    List<TransacaoDTO> listarTodas();
}
