package br.projeto.mywallet.repository;

import br.projeto.mywallet.Model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
s *
 * @author wilsonramos
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByNome(String nome);
}
