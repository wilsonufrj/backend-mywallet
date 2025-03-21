package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface IStatusRepository extends JpaRepository<Status, Long> {
    
}
