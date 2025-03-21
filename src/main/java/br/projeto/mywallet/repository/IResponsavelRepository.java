package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface IResponsavelRepository extends JpaRepository<Responsavel, Long> {
    
}
