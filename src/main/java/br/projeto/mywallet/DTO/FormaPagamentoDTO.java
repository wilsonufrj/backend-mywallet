package br.projeto.mywallet.DTO;


import java.util.List;

/**
 *
 * @author wilsonramos
 */
public class FormaPagamentoDTO {
    private Long id;
    
    private String nome;
    
    private List<TransacaoDTO> transacoes;

    public FormaPagamentoDTO() {
    }

    public FormaPagamentoDTO(Long id, String nome, List<TransacaoDTO> transacoes) {
        this.id = id;
        this.nome = nome;
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

    public List<TransacaoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoDTO> transacoes) {
        this.transacoes = transacoes;
    }
    
    
    
}
