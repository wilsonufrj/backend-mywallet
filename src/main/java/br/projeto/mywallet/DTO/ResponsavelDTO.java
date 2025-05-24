package br.projeto.mywallet.DTO;

import br.projeto.mywallet.Model.Responsavel;
import java.util.List;

/**
 *
 * @author wilsonramos
 */
public class ResponsavelDTO {
    private Long id;
    
    private String nome;

    private UsuarioInfo usuarioInfo;

    public ResponsavelDTO(){}

    public ResponsavelDTO(Long id, String nome, UsuarioInfo usuarioInfo) {
        this.id = id;
        this.nome = nome;
        this.usuarioInfo = usuarioInfo;
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
