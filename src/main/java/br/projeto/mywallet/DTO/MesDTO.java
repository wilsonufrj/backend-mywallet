package br.projeto.mywallet.DTO;

import java.util.List;

/**
 *
 * @author wilsonramos
 */
public class MesDTO {
    private Long id;
    
    private String nome;
    
    private Integer ano;

    private Integer porcentagemInvestimento;

    private CarteiraDTO carteira;
    
    private List<TransacaoDTO> transacoes;
    
    public MesDTO(){}

    public MesDTO(Long id, String nome, Integer ano, Integer porcentagemInvestimento,List<TransacaoDTO> transacoes) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.transacoes = transacoes;
    }

    public MesDTO(Long id, String nome, Integer ano, Integer porcentagemInvestimento,CarteiraDTO carteira, List<TransacaoDTO> transacoes) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.transacoes = transacoes;
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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public CarteiraDTO getCarteira() {
        return carteira;
    }
    public void setCarteira(CarteiraDTO carteira) {
        this.carteira = carteira;
    }

    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }

    public Integer getPorcentagemInvestimento() {
        return porcentagemInvestimento;
    }

    public void setPorcentagemInvestimento(Integer porcentagemInvestimento) {
        this.porcentagemInvestimento = porcentagemInvestimento;
    }
}
