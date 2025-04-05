package br.projeto.mywallet.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author wilsonramos
 */
@Entity
@Table(name = "mes")
public class Mes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "ano")
    private Integer ano;
    
    @ManyToOne
    @JoinColumn(name = "carteira_id", nullable = false)
    private Carteira carteira;
    
    @OneToMany(
            mappedBy = "mes",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transacao> transacoes;
    
    public Mes(){
        
    }

    public Mes(String nome, Integer ano,Carteira carteira, List<Transacao> transacoes) {
        this.nome = nome;
        this.ano = ano;
        this.carteira = carteira;
        this.transacoes = transacoes;
    }

    public Mes(Long id, String nome, Integer ano, Carteira carteira, List<Transacao> transacoes) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.carteira = carteira;
        this.transacoes = transacoes;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
    
    
}
