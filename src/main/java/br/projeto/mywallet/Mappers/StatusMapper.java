package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.StatusDTO;
import br.projeto.mywallet.Model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = TransacaoMapper.class)
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    @Mapping(target = "transacoes", ignore = true)
    StatusDTO toDTO(Status status);

    @Mapping(target = "transacoes", ignore = true)
    Status toEntity(StatusDTO statusDTO);

}
