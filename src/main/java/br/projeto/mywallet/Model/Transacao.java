package br.projeto.mywallet.Model;

import br.projeto.mywallet.enums.TipoFormaPagamento;
import br.projeto.mywallet.enums.TipoStatus;
import br.projeto.mywallet.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @author wilsonramos
 */
@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "quantas_vezes")
    private Integer quantasVezes;

    @Column(name = "receita")
    private Boolean isReceita;

    @ManyToOne
    @JoinColumn(name = "banco_id", nullable = false)
    @JsonIgnoreProperties
    private Banco banco;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "forma_pagamento", nullable = false)
    private TipoFormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status", nullable = false)
    private TipoStatus status;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    @JsonIgnoreProperties
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "mes_id", nullable = false)
    @JsonIgnoreProperties
    private Mes mes;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "tipo_transacao", nullable = false)
    private TipoTransacao tipoTransacao;

    public Transacao() {
    }

    public Transacao(Long id, LocalDate data, String descricao, Float valor, Integer quantasVezes, Boolean isReceita, Banco banco, TipoFormaPagamento formaPagamento, TipoStatus status, Responsavel responsavel, Mes mes, TipoTransacao tipoTransacao) {
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
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

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
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
