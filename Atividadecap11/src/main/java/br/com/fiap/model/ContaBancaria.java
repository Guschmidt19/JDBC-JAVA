package br.com.fiap.model;

import java.math.BigDecimal;

public class ContaBancaria {
    private Long idConta;
    private String banco;
    private String agencia;
    private String numero;
    private String tipo;
    private BigDecimal saldo;
    private Long usuarioId;

    public ContaBancaria() {}

    public ContaBancaria(String banco, String agencia, String numero, String tipo, BigDecimal saldo, Long usuarioId) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.usuarioId = usuarioId;
    }

    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}

