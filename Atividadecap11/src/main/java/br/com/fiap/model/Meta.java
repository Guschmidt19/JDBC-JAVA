package br.com.fiap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Meta {
    private Long idMeta;
    private String objetivo;
    private BigDecimal valorDesejado;
    private BigDecimal valorAtual;
    private LocalDate dataLimite;
    private Long usuarioId;

    public Meta() {}

    public Meta(String objetivo, BigDecimal valorDesejado, BigDecimal valorAtual,
                LocalDate dataLimite, Long usuarioId) {
        this.objetivo = objetivo;
        this.valorDesejado = valorDesejado;
        this.valorAtual = valorAtual;
        this.dataLimite = dataLimite;
        this.usuarioId = usuarioId;
    }

    public Long getIdMeta() { return idMeta; }
    public void setIdMeta(Long idMeta) { this.idMeta = idMeta; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public BigDecimal getValorDesejado() { return valorDesejado; }
    public void setValorDesejado(BigDecimal valorDesejado) { this.valorDesejado = valorDesejado; }
    public BigDecimal getValorAtual() { return valorAtual; }
    public void setValorAtual(BigDecimal valorAtual) { this.valorAtual = valorAtual; }
    public LocalDate getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDate dataLimite) { this.dataLimite = dataLimite; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}
