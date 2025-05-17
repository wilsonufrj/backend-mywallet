package br.projeto.mywallet.Model;

import jakarta.persistence.*;

import java.util.List;

/**
 *
 * @author wilsonramos
 */
@Entity
@Table(name = "responsavel")
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(
            mappedBy = "responsavel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transacao> transacoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Responsavel(){
        
    }

    public Responsavel(Long id, String nome, List<Transacao> transacoes,Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.transacoes = transacoes;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
