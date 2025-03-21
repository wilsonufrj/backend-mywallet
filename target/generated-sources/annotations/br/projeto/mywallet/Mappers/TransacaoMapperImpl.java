package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Transacao;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class TransacaoMapperImpl implements TransacaoMapper {

    @Autowired
    private BancoMapper bancoMapper;
    @Autowired
    private FormaPagamentoMapper formaPagamentoMapper;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private ResponsavelMapper responsavelMapper;
    @Autowired
    private MesMapper mesMapper;
    @Autowired
    private TipoTransacaoMapper tipoTransacaoMapper;

    @Override
    public TransacaoDTO toDTO(Transacao transacao) {
        if ( transacao == null ) {
            return null;
        }

        TransacaoDTO transacaoDTO = new TransacaoDTO();

        transacaoDTO.setId( transacao.getId() );
        transacaoDTO.setData( transacao.getData() );
        transacaoDTO.setDescricao( transacao.getDescricao() );
        transacaoDTO.setValor( transacao.getValor() );
        transacaoDTO.setQuantasVezes( transacao.getQuantasVezes() );
        transacaoDTO.setBanco( bancoMapper.toDTO( transacao.getBanco() ) );
        transacaoDTO.setFormaPagamento( formaPagamentoMapper.toDTO( transacao.getFormaPagamento() ) );
        transacaoDTO.setStatus( statusMapper.toDTO( transacao.getStatus() ) );
        transacaoDTO.setResponsavel( responsavelMapper.toDTO( transacao.getResponsavel() ) );
        transacaoDTO.setMes( mesMapper.toDTO( transacao.getMes() ) );
        transacaoDTO.setTipoTransacao( tipoTransacaoMapper.toDTO( transacao.getTipoTransacao() ) );
        transacaoDTO.setReceita( transacao.getReceita() );

        return transacaoDTO;
    }

    @Override
    public Transacao toEntity(TransacaoDTO transacaoDTO) {
        if ( transacaoDTO == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setId( transacaoDTO.getId() );
        transacao.setData( transacaoDTO.getData() );
        transacao.setDescricao( transacaoDTO.getDescricao() );
        transacao.setValor( transacaoDTO.getValor() );
        transacao.setQuantasVezes( transacaoDTO.getQuantasVezes() );
        transacao.setFormaPagamento( formaPagamentoMapper.toEntity( transacaoDTO.getFormaPagamento() ) );
        transacao.setStatus( statusMapper.toEntity( transacaoDTO.getStatus() ) );
        transacao.setResponsavel( responsavelMapper.toEntity( transacaoDTO.getResponsavel() ) );
        transacao.setMes( mesMapper.toEntity( transacaoDTO.getMes() ) );
        transacao.setTipoTransacao( tipoTransacaoMapper.toEntity( transacaoDTO.getTipoTransacao() ) );
        transacao.setReceita( transacaoDTO.getReceita() );

        return transacao;
    }
}
