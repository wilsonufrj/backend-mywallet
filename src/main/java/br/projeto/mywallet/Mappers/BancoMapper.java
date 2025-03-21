package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.Model.Banco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = {TransacaoMapper.class})
public interface BancoMapper {

    BancoMapper INSTANCE = Mappers.getMapper(BancoMapper.class);

    @Mapping(target = "transacoes", ignore = true)
    BancoDTO toDTO(Banco banco);

    @Mapping(target = "transacoes", ignore = true)
    Banco toEntity(BancoDTO banco);

}
