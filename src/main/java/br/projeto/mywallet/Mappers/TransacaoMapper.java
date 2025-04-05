package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = {
    BancoMapper.class,
    FormaPagamentoMapper.class,
    StatusMapper.class,
    ResponsavelMapper.class,
    MesMapper.class,
    TipoTransacaoMapper.class})
public interface TransacaoMapper {

    TransacaoMapper INSTANCE = Mappers.getMapper(TransacaoMapper.class);

    @Mapping(target = "mes", ignore = true)
    TransacaoDTO toDTO(Transacao transacao);

    @Mapping(target = "mes", ignore = true)
    Transacao toEntity(TransacaoDTO transacaoDTO);

}
