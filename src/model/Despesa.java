package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Despesa {
    private int id;
    private Politico politico;
    private BigDecimal valor; 
    private String descricao; 
    private LocalDate dataRegistro;
    private List<ItemDespesa> itens = new ArrayList<>();

    public Despesa() {
        this.valor = BigDecimal.ZERO;
    }

    public void adicionarItem(ItemDespesa item) {
        this.itens.add(item);
        recalcularTotal();
    }

    public void removerItem(int index) {
        if (index >= 0 && index < this.itens.size()) {
            this.itens.remove(index);
            recalcularTotal();
        }
    }

    public void recalcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemDespesa item : itens) {
            total = total.add(item.getSubtotal());
        }
        this.valor = total;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Politico getPolitico() { return politico; }
    public void setPolitico(Politico politico) { this.politico = politico; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public List<ItemDespesa> getItens() { return itens; }
    public void setItens(List<ItemDespesa> itens) {
        this.itens = itens;
        recalcularTotal();
    }
}