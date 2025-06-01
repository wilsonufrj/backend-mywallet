package br.projeto.mywallet.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_id_seq_generator")
    @SequenceGenerator(name = "responsavel_id_seq_generator", sequenceName = "responsavel_id_seq", allocationSize = 1)
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
    @JsonIgnoreProperties
    private Usuario usuario;

    public Responsavel(){
        
    }

    public Responsavel(String nome, List<Transacao> transacoes, Usuario usuario) {
        this.nome = nome;
        this.transacoes = transacoes;
        this.usuario = usuario;
    }

    public Responsavel(Long id, String nome, List<Transacao> transacoes, Usuario usuario) {
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
