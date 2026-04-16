package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Divida {
    private Long idDivida;
    private String credor;
    private BigDecimal valorTotal;
    private BigDecimal valorPaga;
    private LocalDate vencimento;
    private Long usuarioId;

    public Divida() {}

    public Divida(String credor, BigDecimal valorTotal, BigDecimal valorPaga,
                  LocalDate vencimento, Long usuarioId) {
        this.credor = credor;
        this.valorTotal = valorTotal;
        this.valorPaga = valorPaga;
        this.vencimento = vencimento;
        this.usuarioId = usuarioId;
    }

    public Long getIdDivida() { return idDivida; }
    public void setIdDivida(Long idDivida) { this.idDivida = idDivida; }
    public String getCredor() { return credor; }
    public void setCredor(String credor) { this.credor = credor; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public BigDecimal getValorPaga() { return valorPaga; }
    public void setValorPaga(BigDecimal valorPaga) { this.valorPaga = valorPaga; }
    public LocalDate getVencimento() { return vencimento; }
    public void setVencimento(LocalDate vencimento) { this.vencimento = vencimento; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}

