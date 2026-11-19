package model;

import enums.Categoria;
import java.math.BigDecimal;

public class ServicoRecurso {
    private int id;
    private String descricao;
    private Categoria categoria;
    private BigDecimal valorReferencia;

    public ServicoRecurso() {}

    public ServicoRecurso(int id, String descricao, Categoria categoria, BigDecimal valorReferencia) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valorReferencia = valorReferencia;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Categoria getCategoria() { return categoria; }
    
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    
    public BigDecimal getValorReferencia() { return valorReferencia; }
    public void setValorReferencia(BigDecimal valorReferencia) { this.valorReferencia = valorReferencia; }

    @Override
    public String toString() {
        return descricao + " [" + categoria + "]";
    }
}