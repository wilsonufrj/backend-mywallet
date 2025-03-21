package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.TipoTransacaoDTO;
import br.projeto.mywallet.Model.TipoTransacao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class TipoTransacaoMapperImpl implements TipoTransacaoMapper {

    @Override
    public TipoTransacaoDTO toDTO(TipoTransacao tipoTransacao) {
        if ( tipoTransacao == null ) {
            return null;
        }

        TipoTransacaoDTO tipoTransacaoDTO = new TipoTransacaoDTO();

        tipoTransacaoDTO.setId( tipoTransacao.getId() );
        tipoTransacaoDTO.setNome( tipoTransacao.getNome() );

        return tipoTransacaoDTO;
    }

    @Override
    public TipoTransacao toEntity(TipoTransacaoDTO tipoTransacaoDTO) {
        if ( tipoTransacaoDTO == null ) {
            return null;
        }

        TipoTransacao tipoTransacao = new TipoTransacao();

        tipoTransacao.setId( tipoTransacaoDTO.getId() );
        tipoTransacao.setNome( tipoTransacaoDTO.getNome() );

        return tipoTransacao;
    }
}
