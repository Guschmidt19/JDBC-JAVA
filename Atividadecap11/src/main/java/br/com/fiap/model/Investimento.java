package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento {
    private Long idInvestimento;
    private String tipo;
    private BigDecimal valor;
    private LocalDate data;
    private Long usuarioId;

    public Investimento() {}

    public Investimento(String tipo, BigDecimal valor, LocalDate data, Long usuarioId) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.usuarioId = usuarioId;
    }

    public Long getIdInvestimento() { return idInvestimento; }
    public void setIdInvestimento(Long idInvestimento) { this.idInvestimento = idInvestimento; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
