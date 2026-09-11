package model;

import java.time.LocalDate;

public class Entrega {
    private int id_entrega;
    private LocalDate data_entrega;
    private String responsavel_entrega;
    private String observacao;
    private String status;
    private int fk_solicitacao;

    public Entrega() {
    }

    public Entrega(int id_entrega, LocalDate data_entrega, String responsavel_entrega, String observacao, String status, int fk_solicitacao) {
        this.id_entrega = id_entrega;
        this.data_entrega = data_entrega;
        this.responsavel_entrega = responsavel_entrega;
        this.observacao = observacao;
        this.status = status;
        this.fk_solicitacao = fk_solicitacao;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getResponsavel_entrega() {
        return responsavel_entrega;
    }

    public void setResponsavel_entrega(String responsavel_entrega) {
        this.responsavel_entrega = responsavel_entrega;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFk_solicitacao() {
        return fk_solicitacao;
    }

    public void setFk_solicitacao(int fk_solicitacao) {
        this.fk_solicitacao = fk_solicitacao;
    }
    
    
}
