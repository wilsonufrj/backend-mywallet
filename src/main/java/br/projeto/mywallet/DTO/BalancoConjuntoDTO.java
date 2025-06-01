package br.projeto.mywallet.DTO;

public class BalancoConjuntoDTO {
    ResponsavelDTO responsavelDTO;
    Double porcentagemDosCustos;
    Double gastosConjunto;
    Double investimentoConjunto;
    Double totalGasto;
    Double saldoFinal;

    public BalancoConjuntoDTO(ResponsavelDTO responsavelDTO, Double porcentagemDosCustos, Double gastosConjunto, Double investimentoConjunto, Double totalGasto, Double saldoFinal) {
        this.responsavelDTO = responsavelDTO;
        this.porcentagemDosCustos = porcentagemDosCustos;
        this.gastosConjunto = gastosConjunto;
        this.investimentoConjunto = investimentoConjunto;
        this.totalGasto = totalGasto;
        this.saldoFinal = saldoFinal;
    }

    public ResponsavelDTO getResponsavelDTO() {
        return responsavelDTO;
    }

    public void setResponsavelDTO(ResponsavelDTO responsavelDTO) {
        this.responsavelDTO = responsavelDTO;
    }

    public Double getPorcentagemDosCustos() {
        return porcentagemDosCustos;
    }

    public void setPorcentagemDosCustos(Double porcentagemDosCustos) {
        this.porcentagemDosCustos = porcentagemDosCustos;
    }

    public Double getGastosConjunto() {
        return gastosConjunto;
    }

    public void setGastosConjunto(Double gastosConjunto) {
        this.gastosConjunto = gastosConjunto;
    }

    public Double getInvestimentoConjunto() {
        return investimentoConjunto;
    }

    public void setInvestimentoConjunto(Double investimentoConjunto) {
        this.investimentoConjunto = investimentoConjunto;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}
