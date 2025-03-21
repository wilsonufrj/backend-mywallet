package br.projeto.mywallet.Mappers;

import br.projeto.mywallet.DTO.BancoDTO;
import br.projeto.mywallet.DTO.FormaPagamentoDTO;
import br.projeto.mywallet.DTO.MesDTO;
import br.projeto.mywallet.DTO.ResponsavelDTO;
import br.projeto.mywallet.DTO.StatusDTO;
import br.projeto.mywallet.DTO.TipoTransacaoDTO;
import br.projeto.mywallet.DTO.TransacaoDTO;
import br.projeto.mywallet.Model.Banco;
import br.projeto.mywallet.Model.FormaPagamento;
import br.projeto.mywallet.Model.Mes;
import br.projeto.mywallet.Model.Responsavel;
import br.projeto.mywallet.Model.Status;
import br.projeto.mywallet.Model.TipoTransacao;
import br.projeto.mywallet.Model.Transacao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-21T18:26:18-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
@Component
public class MesMapperImpl implements MesMapper {

    @Override
    public MesDTO toDTO(Mes mes) {
        if ( mes == null ) {
            return null;
        }

        MesDTO mesDTO = new MesDTO();

        mesDTO.setId( mes.getId() );
        mesDTO.setNome( mes.getNome() );
        mesDTO.setAno( mes.getAno() );
        mesDTO.setTransacoes( transacaoListToTransacaoDTOList( mes.getTransacoes() ) );

        return mesDTO;
    }

    @Override
    public Mes toEntity(MesDTO mes) {
        if ( mes == null ) {
            return null;
        }

        Mes mes1 = new Mes();

        mes1.setId( mes.getId() );
        mes1.setNome( mes.getNome() );
        mes1.setAno( mes.getAno() );
        mes1.setTransacoes( transacaoDTOListToTransacaoList( mes.getTransacoes() ) );

        return mes1;
    }

    protected List<TransacaoDTO> transacaoListToTransacaoDTOList(List<Transacao> list) {
        if ( list == null ) {
            return null;
        }

        List<TransacaoDTO> list1 = new ArrayList<TransacaoDTO>( list.size() );
        for ( Transacao transacao : list ) {
            list1.add( transacaoToTransacaoDTO( transacao ) );
        }

        return list1;
    }

    protected BancoDTO bancoToBancoDTO(Banco banco) {
        if ( banco == null ) {
            return null;
        }

        BancoDTO bancoDTO = new BancoDTO();

        bancoDTO.setId( banco.getId() );
        bancoDTO.setNome( banco.getNome() );
        bancoDTO.setTransacoes( transacaoListToTransacaoDTOList( banco.getTransacoes() ) );

        return bancoDTO;
    }

    protected FormaPagamentoDTO formaPagamentoToFormaPagamentoDTO(FormaPagamento formaPagamento) {
        if ( formaPagamento == null ) {
            return null;
        }

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();

        formaPagamentoDTO.setId( formaPagamento.getId() );
        formaPagamentoDTO.setNome( formaPagamento.getNome() );
        formaPagamentoDTO.setTransacoes( transacaoListToTransacaoDTOList( formaPagamento.getTransacoes() ) );

        return formaPagamentoDTO;
    }

    protected StatusDTO statusToStatusDTO(Status status) {
        if ( status == null ) {
            return null;
        }

        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setId( status.getId() );
        statusDTO.setNome( status.getNome() );
        statusDTO.setTransacoes( transacaoListToTransacaoDTOList( status.getTransacoes() ) );

        return statusDTO;
    }

    protected ResponsavelDTO responsavelToResponsavelDTO(Responsavel responsavel) {
        if ( responsavel == null ) {
            return null;
        }

        ResponsavelDTO responsavelDTO = new ResponsavelDTO();

        responsavelDTO.setId( responsavel.getId() );
        responsavelDTO.setNome( responsavel.getNome() );
        responsavelDTO.setTransacoes( transacaoListToTransacaoDTOList( responsavel.getTransacoes() ) );

        return responsavelDTO;
    }

    protected TipoTransacaoDTO tipoTransacaoToTipoTransacaoDTO(TipoTransacao tipoTransacao) {
        if ( tipoTransacao == null ) {
            return null;
        }

        TipoTransacaoDTO tipoTransacaoDTO = new TipoTransacaoDTO();

        tipoTransacaoDTO.setId( tipoTransacao.getId() );
        tipoTransacaoDTO.setNome( tipoTransacao.getNome() );
        tipoTransacaoDTO.setTransacoes( transacaoListToTransacaoDTOList( tipoTransacao.getTransacoes() ) );

        return tipoTransacaoDTO;
    }

    protected TransacaoDTO transacaoToTransacaoDTO(Transacao transacao) {
        if ( transacao == null ) {
            return null;
        }

        TransacaoDTO transacaoDTO = new TransacaoDTO();

        transacaoDTO.setId( transacao.getId() );
        transacaoDTO.setData( transacao.getData() );
        transacaoDTO.setDescricao( transacao.getDescricao() );
        transacaoDTO.setValor( transacao.getValor() );
        transacaoDTO.setQuantasVezes( transacao.getQuantasVezes() );
        transacaoDTO.setBanco( bancoToBancoDTO( transacao.getBanco() ) );
        transacaoDTO.setFormaPagamento( formaPagamentoToFormaPagamentoDTO( transacao.getFormaPagamento() ) );
        transacaoDTO.setStatus( statusToStatusDTO( transacao.getStatus() ) );
        transacaoDTO.setResponsavel( responsavelToResponsavelDTO( transacao.getResponsavel() ) );
        transacaoDTO.setMes( toDTO( transacao.getMes() ) );
        transacaoDTO.setTipoTransacao( tipoTransacaoToTipoTransacaoDTO( transacao.getTipoTransacao() ) );
        transacaoDTO.setReceita( transacao.getReceita() );

        return transacaoDTO;
    }

    protected List<Transacao> transacaoDTOListToTransacaoList(List<TransacaoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Transacao> list1 = new ArrayList<Transacao>( list.size() );
        for ( TransacaoDTO transacaoDTO : list ) {
            list1.add( transacaoDTOToTransacao( transacaoDTO ) );
        }

        return list1;
    }

    protected Banco bancoDTOToBanco(BancoDTO bancoDTO) {
        if ( bancoDTO == null ) {
            return null;
        }

        Banco banco = new Banco();

        banco.setId( bancoDTO.getId() );
        banco.setNome( bancoDTO.getNome() );
        banco.setTransacoes( transacaoDTOListToTransacaoList( bancoDTO.getTransacoes() ) );

        return banco;
    }

    protected FormaPagamento formaPagamentoDTOToFormaPagamento(FormaPagamentoDTO formaPagamentoDTO) {
        if ( formaPagamentoDTO == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        List<Transacao> transacoes = null;

        id = formaPagamentoDTO.getId();
        nome = formaPagamentoDTO.getNome();
        transacoes = transacaoDTOListToTransacaoList( formaPagamentoDTO.getTransacoes() );

        FormaPagamento formaPagamento = new FormaPagamento( id, nome, transacoes );

        return formaPagamento;
    }

    protected Status statusDTOToStatus(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status status = new Status();

        status.setId( statusDTO.getId() );
        status.setNome( statusDTO.getNome() );
        status.setTransacoes( transacaoDTOListToTransacaoList( statusDTO.getTransacoes() ) );

        return status;
    }

    protected Responsavel responsavelDTOToResponsavel(ResponsavelDTO responsavelDTO) {
        if ( responsavelDTO == null ) {
            return null;
        }

        Responsavel responsavel = new Responsavel();

        responsavel.setId( responsavelDTO.getId() );
        responsavel.setNome( responsavelDTO.getNome() );
        responsavel.setTransacoes( transacaoDTOListToTransacaoList( responsavelDTO.getTransacoes() ) );

        return responsavel;
    }

    protected TipoTransacao tipoTransacaoDTOToTipoTransacao(TipoTransacaoDTO tipoTransacaoDTO) {
        if ( tipoTransacaoDTO == null ) {
            return null;
        }

        TipoTransacao tipoTransacao = new TipoTransacao();

        tipoTransacao.setId( tipoTransacaoDTO.getId() );
        tipoTransacao.setNome( tipoTransacaoDTO.getNome() );
        tipoTransacao.setTransacoes( transacaoDTOListToTransacaoList( tipoTransacaoDTO.getTransacoes() ) );

        return tipoTransacao;
    }

    protected Transacao transacaoDTOToTransacao(TransacaoDTO transacaoDTO) {
        if ( transacaoDTO == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setId( transacaoDTO.getId() );
        transacao.setData( transacaoDTO.getData() );
        transacao.setDescricao( transacaoDTO.getDescricao() );
        transacao.setValor( transacaoDTO.getValor() );
        transacao.setQuantasVezes( transacaoDTO.getQuantasVezes() );
        transacao.setBanco( bancoDTOToBanco( transacaoDTO.getBanco() ) );
        transacao.setFormaPagamento( formaPagamentoDTOToFormaPagamento( transacaoDTO.getFormaPagamento() ) );
        transacao.setStatus( statusDTOToStatus( transacaoDTO.getStatus() ) );
        transacao.setResponsavel( responsavelDTOToResponsavel( transacaoDTO.getResponsavel() ) );
        transacao.setMes( toEntity( transacaoDTO.getMes() ) );
        transacao.setTipoTransacao( tipoTransacaoDTOToTipoTransacao( transacaoDTO.getTipoTransacao() ) );
        transacao.setReceita( transacaoDTO.getReceita() );

        return transacao;
    }
}
