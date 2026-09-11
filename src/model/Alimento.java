package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Alimento {
    private int id_alimento;
    private String nome;
    private String descricao;
    private BigDecimal quantidade;
    private String unidade_medida;
    private LocalDate data_validade;
    private LocalDate data_cadastro;
    private String status;
    private int fk_doador;
    private int fk_categoria;

    public Alimento() {
    }

    public Alimento(int id_alimento, String nome, String descricao, BigDecimal quantidade, String unidade_medida, LocalDate data_validade, LocalDate data_cadastro, String status, int fk_doador, int fk_categoria) {
        this.id_alimento = id_alimento;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.unidade_medida = unidade_medida;
        this.data_validade = data_validade;
        this.data_cadastro = data_cadastro;
        this.status = status;
        this.fk_doador = fk_doador;
        this.fk_categoria = fk_categoria;
    }

    public int getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(int id_alimento) {
        this.id_alimento = id_alimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

    public LocalDate getData_validade() {
        return data_validade;
    }

    public void setData_validade(LocalDate data_validade) {
        this.data_validade = data_validade;
    }

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFk_doador() {
        return fk_doador;
    }

    public void setFk_doador(int fk_doador) {
        this.fk_doador = fk_doador;
    }

    public int getFk_categoria() {
        return fk_categoria;
    }

    public void setFk_categoria(int fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

    
}
