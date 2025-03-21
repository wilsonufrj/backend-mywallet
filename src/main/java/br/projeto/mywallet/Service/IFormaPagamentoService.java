package br.projeto.mywallet.Service;

import br.projeto.mywallet.DTO.FormaPagamentoDTO;
import java.util.List;

public interface IFormaPagamentoService {
    FormaPagamentoDTO criarFormaPagamento(FormaPagamentoDTO formaPagamentoDTO); 
    FormaPagamentoDTO buscarFormaPagamentoPorId(Long id); 
    List<FormaPagamentoDTO> listarTodasFormasPagamento(); 
    FormaPagamentoDTO atualizarFormaPagamento(Long id, FormaPagamentoDTO formaPagamento); 
    void deletarFormaPagamento(Long id); 
}
