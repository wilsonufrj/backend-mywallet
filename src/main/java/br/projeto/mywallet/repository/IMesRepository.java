package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Mes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface IMesRepository extends JpaRepository<Mes, Long>{
    
}
