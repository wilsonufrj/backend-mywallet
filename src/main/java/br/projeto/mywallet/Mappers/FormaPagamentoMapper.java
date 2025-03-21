package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.FormaPagamentoDTO;
import br.projeto.mywallet.Model.FormaPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = TransacaoMapper.class)
public interface FormaPagamentoMapper {

    FormaPagamentoMapper INSTANCE = Mappers.getMapper(FormaPagamentoMapper.class);

    @Mapping(target = "transacoes", ignore = true)
    FormaPagamentoDTO toDTO(FormaPagamento formaPagamento);

    @Mapping(target = "transacoes", ignore = true)
    FormaPagamento toEntity(FormaPagamentoDTO formaPagamentoDTO);

}
