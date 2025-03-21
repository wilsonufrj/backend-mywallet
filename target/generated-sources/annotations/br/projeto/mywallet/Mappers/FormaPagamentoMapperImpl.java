package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.FormaPagamentoDTO;
import br.projeto.mywallet.Model.FormaPagamento;
import br.projeto.mywallet.Model.Transacao;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class FormaPagamentoMapperImpl implements FormaPagamentoMapper {

    @Override
    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
        if ( formaPagamento == null ) {
            return null;
        }

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();

        formaPagamentoDTO.setId( formaPagamento.getId() );
        formaPagamentoDTO.setNome( formaPagamento.getNome() );

        return formaPagamentoDTO;
    }

    @Override
    public FormaPagamento toEntity(FormaPagamentoDTO formaPagamentoDTO) {
        if ( formaPagamentoDTO == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = formaPagamentoDTO.getId();
        nome = formaPagamentoDTO.getNome();

        List<Transacao> transacoes = null;

        FormaPagamento formaPagamento = new FormaPagamento( id, nome, transacoes );

        return formaPagamento;
    }
}
