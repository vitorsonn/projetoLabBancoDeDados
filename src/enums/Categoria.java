/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author User
 */
public enum Categoria {
    COMBUSTIVEL("Combustíveis e Lubrificantes"),
    PASSAGENS_HOSPEDAGEM("Passagens e Hospedagem"),
    DIVULGACAO("Divulgação e Comunicação"),
    TELEFONIA_CORREIOS("Serviços Postais e Telefone"),
    CONSULTORIA("Consultorias e Pesquisas"),
    ALUGUEL_ESCRITORIO("Aluguel e Manutenção de Escritório"),
    ALIMENTACAO("Alimentação e Refeições");
    
    private final String descricaoCategoria;
    
    Categoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
    
    @Override
    public String toString() {
        return descricaoCategoria;
    }
}
