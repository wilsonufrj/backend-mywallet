/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.TipoTransacaoDTO;
import br.projeto.mywallet.Model.TipoTransacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author wilsonramos
 */
@Mapper(componentModel = "spring", uses = TransacaoMapper.class)
public interface TipoTransacaoMapper {

    TipoTransacaoMapper INSTANCE = Mappers.getMapper(TipoTransacaoMapper.class);

    @Mapping(target = "transacoes", ignore = true)
    TipoTransacaoDTO toDTO(TipoTransacao tipoTransacao);

    @Mapping(target = "transacoes", ignore = true)
    TipoTransacao toEntity(TipoTransacaoDTO tipoTransacaoDTO);

}
