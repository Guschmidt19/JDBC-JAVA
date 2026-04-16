package br.com.fiap.dao;

import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDao {

    private static final String SQL_SELECT_ALL =
            "SELECT id_receita, descricao, valor, data, usuario_id_usuario " +
                    "FROM receita ORDER BY data DESC, id_receita DESC";

    private static final String SQL_INSERT =
            "INSERT INTO receita (descricao, valor, data, usuario_id_usuario) " +
                    "VALUES (?, ?, ?, ?)";

    public List<Receita> getAll() throws SQLException {
        List<Receita> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Receita r = new Receita();
                r.setIdReceita(rs.getLong("id_receita"));
                r.setDescricao(rs.getString("descricao"));
                r.setValor(rs.getBigDecimal("valor"));
                Date dt = rs.getDate("data");
                r.setData(dt != null ? dt.toLocalDate() : null);
                r.setUsuarioId(rs.getLong("usuario_id_usuario"));
                lista.add(r);
            }
        }
        return lista;
    }

    public void insert(Receita r) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, r.getDescricao());
            ps.setBigDecimal(2, r.getValor());
            ps.setDate(3, Date.valueOf(r.getData()));
            ps.setLong(4, r.getUsuarioId());

            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Nenhuma linha inserida em RECEITA.");
        }
    }
}
