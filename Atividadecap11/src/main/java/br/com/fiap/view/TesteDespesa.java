package br.com.fiap.view;

import br.com.fiap.dao.DespesaDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TesteDespesa {

    private static Long buscarUsuarioIdPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE cpf = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getLong(1) : null;
            }
        }
    }

    private static Despesa nova(String desc, String cat, double valor, Long userId) {
        return new Despesa(
                desc,
                new BigDecimal(String.valueOf(valor)),
                LocalDate.now(),
                cat,
                userId
        );
    }

    public static void main(String[] args) {
        DespesaDao dao = new DespesaDao();

        final String CPF_EXISTENTE = "12345678911";

        try {
            Long usuarioId = buscarUsuarioIdPorCpf(CPF_EXISTENTE);
            if (usuarioId == null) {
                System.err.println("Usuário não encontrado para o CPF " + CPF_EXISTENTE);
                return;
            }

            dao.insert(nova("Aluguel",     "Moradia",      1800.00, usuarioId));
            dao.insert(nova("Mercado",     "Alimentação",   350.75, usuarioId));
            dao.insert(nova("Transporte",  "Mobilidade",     22.50, usuarioId));
            dao.insert(nova("Energia",     "Moradia",       230.40, usuarioId));
            dao.insert(nova("Internet",    "Serviços",      120.00, usuarioId));

            List<Despesa> todas = dao.getAll();
            System.out.println("=== DESPESAS ===");
            for (Despesa d : todas) {
                System.out.printf("ID=%d | %s | R$ %s | %s | cat=%s | user=%d%n",
                        d.getIdDespesa(), d.getDescricao(), d.getValor(),
                        d.getData(), d.getCategoria(), d.getUsuarioId());
            }

        } catch (SQLException e) {
            System.err.println("[ERRO JDBC] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
