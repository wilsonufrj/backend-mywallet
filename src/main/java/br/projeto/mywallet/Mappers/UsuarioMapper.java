package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.UsuarioDTO;
import br.projeto.mywallet.Model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring",uses = CarteiraMapper.class)
public interface UsuarioMapper {
    
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "carteiras", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "carteiras", ignore = true)
    Usuario toEntity(UsuarioDTO usuario);
    
}
