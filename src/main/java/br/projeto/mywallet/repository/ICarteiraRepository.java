package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wilsonramos
 */
public interface ICarteiraRepository extends JpaRepository<Carteira, Long>{
    
}
