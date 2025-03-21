package br.projeto.mywallet.DTO;

/**
 *
 * @author wilsonramos
 */
public class LoginDTO {
    private String nome;
    private String senha;

    public LoginDTO(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public LoginDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
