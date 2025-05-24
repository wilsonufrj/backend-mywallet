package br.projeto.mywallet.DTO;

public class BalancoDTO {
    private Double totalGanhoMes;
    private Double totalGastosMes;
    private Double saldoAtual;
    private Double investimentoMes;
    private Double saldoMesSeguinte;

    public Double getTotalGanhoMes() {
        return totalGanhoMes;
    }

    public void setTotalGanhoMes(Double totalGanhoMes) {
        this.totalGanhoMes = totalGanhoMes;
    }

    public Double getTotalGastosMes() {
        return totalGastosMes;
    }

    public void setTotalGastosMes(Double totalGastosMes) {
        this.totalGastosMes = totalGastosMes;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Double getInvestimentoMes() {
        return investimentoMes;
    }

    public void setInvestimentoMes(Double investimentoMes) {
        this.investimentoMes = investimentoMes;
    }

    public Double getSaldoMesSeguinte() {
        return saldoMesSeguinte;
    }

    public void setSaldoMesSeguinte(Double saldoMesSeguinte) {
        this.saldoMesSeguinte = saldoMesSeguinte;
    }
}
