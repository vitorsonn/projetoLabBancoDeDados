package model;

import java.math.BigDecimal;

public class ItemDespesa {
    private int id;
    private int idDespesa;
    private ServicoRecurso servicoRecurso;
    private int quantidade;
    private BigDecimal valorUnitario;

    public ItemDespesa() {}

    public ItemDespesa(ServicoRecurso servicoRecurso, int quantidade, BigDecimal valorUnitario) {
        this.servicoRecurso = servicoRecurso;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getSubtotal() {
        if (valorUnitario == null) {
            return BigDecimal.ZERO;
        }
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdDespesa() { return idDespesa; }
    public void setIdDespesa(int idDespesa) { this.idDespesa = idDespesa; }

    public ServicoRecurso getServicoRecurso() { return servicoRecurso; }
    public void setServicoRecurso(ServicoRecurso servicoRecurso) { this.servicoRecurso = servicoRecurso; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }
}