package br.projeto.mywallet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author wilsonramos
 */

public class UsuarioDTO {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String email;

    private Set<CarteiraDTO> carteiras;
    
    public UsuarioDTO(){}

    public UsuarioDTO(Long id, String nome, LocalDate dataNascimento, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.carteiras = carteiras;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<CarteiraDTO> getCarteiras() {
        return carteiras;
    }

    public void setCarteiras(Set<CarteiraDTO> carteiras) {
        this.carteiras = carteiras;
    }
    
    
}
