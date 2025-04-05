package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.Model.Mes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = {CarteiraMapper.class,TransacaoMapper.class})
public interface MesMapper {

    MesMapper INSTANCE = Mappers.getMapper(MesMapper.class);

    @Mapping(target = "carteira", ignore = true)
    MesDTO toDTO(Mes mes);

    @Mapping(target = "carteira", ignore = true)
    Mes toEntity(MesDTO mes);

}
