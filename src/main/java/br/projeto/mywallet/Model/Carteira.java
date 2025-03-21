package br.projeto.mywallet.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author wilsonramos
 */
@Entity
@Table(name = "carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",
            nullable = false)
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_carteira",
            joinColumns = @JoinColumn(name = "carteira_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "carteira",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Mes> meses;

    public Carteira() {
    }

    public Carteira(Long id, String nome, Set<Usuario> usuarios, List<Mes> meses) {
        this.id = id;
        this.nome = nome;
        this.usuarios = usuarios;
        this.meses = meses;
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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carteira{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", usuarios=").append(usuarios);
        sb.append(", meses=").append(meses);
        sb.append('}');
        return sb.toString();
    }


}
