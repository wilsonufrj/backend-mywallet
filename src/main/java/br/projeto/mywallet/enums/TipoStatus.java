package br.projeto.mywallet.enums;

public enum TipoStatus {
    PAGO("PAGO"),
    NAO_PAGO("NAO_PAGO");

    private String descricao;

    TipoStatus(String status) {
        this.descricao = status;
    }

    public String getDescricao() {
        return descricao;
    }
}
