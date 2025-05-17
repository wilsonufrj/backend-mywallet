package br.projeto.mywallet.enums;

public enum TipoTransacao {
    DEBITO("DEBITO"),
    CREDITO("CREDITO"),
    INVESTIMENTO("INVESTIMENTO");

    private String descricao;

    TipoTransacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
