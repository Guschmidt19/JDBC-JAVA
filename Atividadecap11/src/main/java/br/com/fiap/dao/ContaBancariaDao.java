package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.ContaBancaria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaBancariaDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_conta, banco, agencia, numero, tipo, saldo, usuario_id_usuario " +
                    "FROM conta_bancaria ORDER BY id_conta DESC";

    private static final String SQL_INSERT =
            "INSERT INTO conta_bancaria (banco, agencia, numero, tipo, saldo, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public List<ContaBancaria> getAll() throws SQLException {
        List<ContaBancaria> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ContaBancaria c = new ContaBancaria();
                c.setIdConta(rs.getLong("id_conta"));
                c.setBanco(rs.getString("banco"));
                c.setAgencia(rs.getString("agencia"));
                c.setNumero(rs.getString("numero"));
                c.setTipo(rs.getString("tipo"));
                c.setSaldo(rs.getBigDecimal("saldo"));
                c.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(c);
            }
        }
        return lista;
    }

    public void insert(ContaBancaria c) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, c.getBanco());
            ps.setString(2, c.getAgencia());
            ps.setString(3, c.getNumero());
            ps.setString(4, c.getTipo());
            ps.setBigDecimal(5, c.getSaldo());
            ps.setLong(6, c.getUsuarioId());

            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Nenhuma linha inserida em CONTA_BANCARIA.");
        }
    }
}
