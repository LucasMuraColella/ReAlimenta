package model;

import java.time.LocalDate;

public class Solicitacao {
    private int id_solicitacao;
    private LocalDate data_solicitacao;
    private String observacao;
    private String status;
    private int fk_instituicao;
    private Integer fk_administrador;

    public Solicitacao() {
    }

    public Solicitacao(int id_solicitacao, LocalDate data_solicitacao, String observacao, String status, int fk_instituicao, Integer fk_administrador) {
        this.id_solicitacao = id_solicitacao;
        this.data_solicitacao = data_solicitacao;
        this.observacao = observacao;
        this.status = status;
        this.fk_instituicao = fk_instituicao;
        this.fk_administrador = fk_administrador;
    }

    public int getId_solicitacao() {
        return id_solicitacao;
    }

    public void setId_solicitacao(int id_solicitacao) {
        this.id_solicitacao = id_solicitacao;
    }

    public LocalDate getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(LocalDate data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
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

    public int getFk_instituicao() {
        return fk_instituicao;
    }

    public void setFk_instituicao(int fk_instituicao) {
        this.fk_instituicao = fk_instituicao;
    }

    public Integer getFk_administrador() {
        return fk_administrador;
    }

    public void setFk_administrador(Integer fk_administrador) {
        this.fk_administrador = fk_administrador;
    }
    
    
}
