package br.projeto.mywallet.DTO;

import java.time.LocalDate;

public class UsuarioInfo {
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String email;

    public UsuarioInfo() {
    }

    public UsuarioInfo(Long id, String nome, LocalDate dataNascimento, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
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
}
