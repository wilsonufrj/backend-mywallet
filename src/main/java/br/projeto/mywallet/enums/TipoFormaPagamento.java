package br.projeto.mywallet.enums;

public enum TipoFormaPagamento {
    DINHEIRO("DINHEIRO"),
    CARTAO("CARTAO"),
    PIX("PIX");

    private String descricao;

    TipoFormaPagamento(String status) {
        this.descricao = status;
    }

    public String getDescricao() {
        return descricao;
    }
}
