package br.projeto.mywallet.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author wilsonramos
 */

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(
            mappedBy = "formaPagamento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transacao> transacoes;

    public FormaPagamento() {

    }

    public FormaPagamento(Long id, String nome, List<Transacao> transacoes) {
        this.id = id;
        this.nome = nome;
        this.transacoes = transacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
    
    
}
