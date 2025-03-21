package br.projeto.mywallet.DTO;

import java.util.List;
import java.util.Set;

/**
 *
 * @author wilsonramos
 */
public class CarteiraDTO {
    
    private Long id;
   
    private String nome;
    
    private Set<UsuarioDTO> usuarios;
   
    private List<MesDTO> meses;

    public CarteiraDTO() {
    }

    public CarteiraDTO(Long id, String nome, Set<UsuarioDTO> usuarios, List<MesDTO> meses) {
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

    public Set<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<MesDTO> getMeses() {
        return meses;
    }

    public void setMeses(List<MesDTO> meses) {
        this.meses = meses;
    }
    
    
}
