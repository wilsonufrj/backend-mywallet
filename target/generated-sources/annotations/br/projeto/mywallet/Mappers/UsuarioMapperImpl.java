package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.CarteiraDTO;
import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Model.Carteira;
import br.projeto.mywallet.Model.Usuario;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private CarteiraMapper carteiraMapper;

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setNome( usuario.getNome() );
        usuarioDTO.setDataNascimento( usuario.getDataNascimento() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setSenha( usuario.getSenha() );
        usuarioDTO.setCarteiras( carteiraSetToCarteiraDTOSet( usuario.getCarteiras() ) );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuario) {
        if ( usuario == null ) {
            return null;
        }

        Usuario usuario1 = new Usuario();

        usuario1.setId( usuario.getId() );
        usuario1.setNome( usuario.getNome() );
        usuario1.setSenha( usuario.getSenha() );
        usuario1.setDataNascimento( usuario.getDataNascimento() );
        usuario1.setEmail( usuario.getEmail() );
        usuario1.setCarteiras( carteiraDTOSetToCarteiraSet( usuario.getCarteiras() ) );

        return usuario1;
    }

    protected Set<CarteiraDTO> carteiraSetToCarteiraDTOSet(Set<Carteira> set) {
        if ( set == null ) {
            return null;
        }

        Set<CarteiraDTO> set1 = new LinkedHashSet<CarteiraDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Carteira carteira : set ) {
            set1.add( carteiraMapper.toDTO( carteira ) );
        }

        return set1;
    }

    protected Set<Carteira> carteiraDTOSetToCarteiraSet(Set<CarteiraDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Carteira> set1 = new LinkedHashSet<Carteira>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CarteiraDTO carteiraDTO : set ) {
            set1.add( carteiraMapper.toEntity( carteiraDTO ) );
        }

        return set1;
    }
}
