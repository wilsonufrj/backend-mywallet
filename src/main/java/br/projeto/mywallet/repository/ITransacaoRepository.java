package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface ITransacaoRepository extends JpaRepository<Transacao, Long> {
    
}
