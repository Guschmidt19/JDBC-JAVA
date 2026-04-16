package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receita {
    private Long idReceita;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Long usuarioId;

    public Receita() {}

    public Receita(String descricao, BigDecimal valor, LocalDate data, Long usuarioId) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.usuarioId = usuarioId;
    }

    public Long getIdReceita() { return idReceita; }
    public void setIdReceita(Long idReceita) { this.idReceita = idReceita; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
