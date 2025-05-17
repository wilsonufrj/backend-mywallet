package br.projeto.mywallet.DTO;

import br.projeto.mywallet.enums.TipoFormaPagamento;
import br.projeto.mywallet.enums.TipoStatus;
import br.projeto.mywallet.enums.TipoTransacao;

import java.time.LocalDate;

/**
 *
 * @author wilsonramos
 */
public class TransacaoDTO {
    private Long id;
    
    private LocalDate data;
    
    private String descricao;
    
    private Float valor;
    
    private Integer quantasVezes;

    private BancoDTO banco;

    private TipoFormaPagamento formaPagamento;

    private TipoStatus status;

    private ResponsavelDTO responsavel;

    private TipoTransacao tipoTransacao;

    private Boolean isReceita;

    public TransacaoDTO(){}

    public TransacaoDTO(Long id, LocalDate data, String descricao, Float valor, Integer quantasVezes, BancoDTO banco, TipoFormaPagamento formaPagamento, TipoStatus status, ResponsavelDTO responsavel, TipoTransacao tipoTransacao, Boolean isReceita) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.quantasVezes = quantasVezes;
        this.banco = banco;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.responsavel = responsavel;
        this.tipoTransacao = tipoTransacao;
        this.isReceita = isReceita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getQuantasVezes() {
        return quantasVezes;
    }

    public void setQuantasVezes(Integer quantasVezes) {
        this.quantasVezes = quantasVezes;
    }

    public BancoDTO getBanco() {
        return banco;
    }

    public void setBanco(BancoDTO banco) {
        this.banco = banco;
    }

    public TipoFormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(TipoFormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public ResponsavelDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelDTO responsavel) {
        this.responsavel = responsavel;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Boolean getReceita() {
        return isReceita;
    }

    public void setReceita(Boolean receita) {
        isReceita = receita;
    }
}
