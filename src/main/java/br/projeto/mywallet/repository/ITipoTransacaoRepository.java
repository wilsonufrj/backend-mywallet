package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface ITipoTransacaoRepository extends JpaRepository<TipoTransacao, Long> {
    
}
