package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa {
    private Long idDespesa;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoria;
    private Long usuarioId;

    public Despesa() {}

    public Despesa(String descricao, BigDecimal valor, LocalDate data, String categoria, Long usuarioId) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.usuarioId = usuarioId;
    }

    public Long getIdDespesa() { return idDespesa; }
    public void setIdDespesa(Long idDespesa) { this.idDespesa = idDespesa; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
