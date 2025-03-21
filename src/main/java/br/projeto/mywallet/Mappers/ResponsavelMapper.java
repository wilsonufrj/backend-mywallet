package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.Model.Responsavel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = TransacaoMapper.class)
public interface ResponsavelMapper {

    ResponsavelMapper INSTANCE = Mappers.getMapper(ResponsavelMapper.class);

    @Mapping(target = "transacoes", ignore = true)
    ResponsavelDTO toDTO(Responsavel responsavel);

    @Mapping(target = "transacoes", ignore = true)
    Responsavel toEntity(ResponsavelDTO responsavelDTO);

}
