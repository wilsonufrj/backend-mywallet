package br.projeto.mywallet.DTO;

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
    
    
    private FormaPagamentoDTO formaPagamento;
    
   
    private StatusDTO status;
    
    
    private ResponsavelDTO responsavel;

    private MesDTO mes;
    
    private TipoTransacaoDTO tipoTransacao;

    private Boolean isReceita;

    public TransacaoDTO(){}

    public TransacaoDTO(Long id, LocalDate data, String descricao, Float valor, Integer quantasVezes, BancoDTO banco, FormaPagamentoDTO formaPagamento, StatusDTO status, ResponsavelDTO responsavel, MesDTO mes, TipoTransacaoDTO tipoTransacao, Boolean isReceita) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.quantasVezes = quantasVezes;
        this.banco = banco;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.responsavel = responsavel;
        this.mes = mes;
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

    public FormaPagamentoDTO getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public ResponsavelDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelDTO responsavel) {
        this.responsavel = responsavel;
    }

    public MesDTO getMes() {
        return mes;
    }

    public void setMes(MesDTO mes) {
        this.mes = mes;
    }

    public TipoTransacaoDTO getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacaoDTO tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Boolean getReceita() {
        return isReceita;
    }

    public void setReceita(Boolean receita) {
        isReceita = receita;
    }
}
