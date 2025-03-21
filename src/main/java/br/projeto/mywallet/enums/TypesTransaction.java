package br.projeto.mywallet.enums;

/**
 *
 * @author wilson
 */

public enum TypesTransaction {
    GAIN("GAIN"),
    SPEND("SPEND"),
    INVESTIMENT("INVESTIMENT");
    
    private String typeTransaction;

    private TypesTransaction(String label) {
        
        this.typeTransaction = label;
    }

    public String getLabel() {
        return typeTransaction;
    }
    
}
