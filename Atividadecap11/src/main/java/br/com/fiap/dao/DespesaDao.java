package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_despesa, descricao, valor, data, categoria, usuario_id_usuario " +
                    "FROM despesa ORDER BY data DESC, id_despesa DESC";

    private static final String SQL_INSERT =
            "INSERT INTO despesa (descricao, valor, data, categoria, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public List<Despesa> getAll() throws SQLException {
        List<Despesa> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa();
                d.setIdDespesa(rs.getLong("id_despesa"));
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getBigDecimal("valor"));
                Date dt = rs.getDate("data");
                d.setData(dt != null ? dt.toLocalDate() : null);
                d.setCategoria(rs.getString("categoria"));
                d.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(d);
            }
        }
        return lista;
    }

    public void insert(Despesa d) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, d.getDescricao());
            ps.setBigDecimal(2, d.getValor());
            ps.setDate(3, d.getData() != null ? Date.valueOf(d.getData()) : null);
            ps.setString(4, d.getCategoria());
            ps.setLong(5, d.getUsuarioId());

            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Nenhuma linha inserida em DESPESA.");
        }
    }
}
