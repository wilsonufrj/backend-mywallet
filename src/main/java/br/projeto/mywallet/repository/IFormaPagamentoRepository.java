package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface IFormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
    
}
