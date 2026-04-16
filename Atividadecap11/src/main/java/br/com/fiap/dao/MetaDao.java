package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Meta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_meta, objetivo, valor_desejado, valor_atual, data_limite, usuario_id_usuario " +
                    "FROM meta ORDER BY data_limite DESC, id_meta DESC";

    private static final String SQL_INSERT =
            "INSERT INTO meta (objetivo, valor_desejado, valor_atual, data_limite, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public List<Meta> getAll() throws SQLException {
        List<Meta> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Meta m = new Meta();
                m.setIdMeta(rs.getLong("id_meta"));
                m.setObjetivo(rs.getString("objetivo"));
                m.setValorDesejado(rs.getBigDecimal("valor_desejado"));
                m.setValorAtual(rs.getBigDecimal("valor_atual"));
                Date dl = rs.getDate("data_limite");
                m.setDataLimite(dl != null ? dl.toLocalDate() : null);
                m.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(m);
            }
        }
        return lista;
    }

    public void insert(Meta m) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, m.getObjetivo());
            ps.setBigDecimal(2, m.getValorDesejado());
            ps.setBigDecimal(3, m.getValorAtual());
            ps.setDate(4, Date.valueOf(m.getDataLimite()));
            ps.setLong(5, m.getUsuarioId());
            if (ps.executeUpdate() == 0) throw new SQLException("Nenhuma linha inserida em META.");
        }
    }
}
