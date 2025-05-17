package br.projeto.mywallet.DTO;

import java.util.List;

/**
 *
 * @author wilsonramos
 */
public class BancoDTO {
    private Long id;
    
    private String nome;

    public BancoDTO(){}

    public BancoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
