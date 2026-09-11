package model;

import java.math.BigDecimal;

public class ItemSolicitacao {
    private int id_item;
    private BigDecimal quantidade_solicitada;
    private int fk_solicitacao;
    private int fk_alimento;

    public ItemSolicitacao() {
    }

    public ItemSolicitacao(int id_item, BigDecimal quantidade_solicitada, int fk_solicitacao, int fk_alimento) {
        this.id_item = id_item;
        this.quantidade_solicitada = quantidade_solicitada;
        this.fk_solicitacao = fk_solicitacao;
        this.fk_alimento = fk_alimento;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public BigDecimal getQuantidade_solicitada() {
        return quantidade_solicitada;
    }

    public void setQuantidade_solicitada(BigDecimal quantidade_solicitada) {
        this.quantidade_solicitada = quantidade_solicitada;
    }

    public int getFk_solicitacao() {
        return fk_solicitacao;
    }

    public void setFk_solicitacao(int fk_solicitacao) {
        this.fk_solicitacao = fk_solicitacao;
    }

    public int getFk_alimento() {
        return fk_alimento;
    }

    public void setFk_alimento(int fk_alimento) {
        this.fk_alimento = fk_alimento;
    }
}
