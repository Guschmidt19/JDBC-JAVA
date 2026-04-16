package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Divida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DividaDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_divida, credor, valor_total, valor_paga, vencimento, usuario_id_usuario " +
                    "FROM divida ORDER BY vencimento DESC, id_divida DESC";

    private static final String SQL_INSERT =
            "INSERT INTO divida (credor, valor_total, valor_paga, vencimento, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public List<Divida> getAll() throws SQLException {
        List<Divida> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Divida d = new Divida();
                d.setIdDivida(rs.getLong("id_divida"));
                d.setCredor(rs.getString("credor"));
                d.setValorTotal(rs.getBigDecimal("valor_total"));
                d.setValorPaga(rs.getBigDecimal("valor_paga"));
                Date v = rs.getDate("vencimento");
                d.setVencimento(v != null ? v.toLocalDate() : null);
                d.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(d);
            }
        }
        return lista;
    }

    public void insert(Divida d) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, d.getCredor());
            ps.setBigDecimal(2, d.getValorTotal());
            ps.setBigDecimal(3, d.getValorPaga());
            ps.setDate(4, Date.valueOf(d.getVencimento()));
            ps.setLong(5, d.getUsuarioId());
            if (ps.executeUpdate() == 0) throw new SQLException("Nenhuma linha inserida em DIVIDA.");
        }
    }
}
