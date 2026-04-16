package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_investimento, tipo, valor, data, usuario_id_usuario " +
                    "FROM investimento ORDER BY data DESC, id_investimento DESC";

    private static final String SQL_INSERT =
            "INSERT INTO investimento (tipo, valor, data, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?)";

    public List<Investimento> getAll() throws SQLException {
        List<Investimento> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Investimento i = new Investimento();
                i.setIdInvestimento(rs.getLong("id_investimento"));
                i.setTipo(rs.getString("tipo"));
                i.setValor(rs.getBigDecimal("valor"));
                Date dt = rs.getDate("data");
                i.setData(dt != null ? dt.toLocalDate() : null);
                i.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(i);
            }
        }
        return lista;
    }

    public void insert(Investimento i) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, i.getTipo());
            ps.setBigDecimal(2, i.getValor());
            ps.setDate(3, Date.valueOf(i.getData()));
            ps.setLong(4, i.getUsuarioId());
            if (ps.executeUpdate() == 0) throw new SQLException("Nenhuma linha inserida em INVESTIMENTO.");
        }
    }
}
