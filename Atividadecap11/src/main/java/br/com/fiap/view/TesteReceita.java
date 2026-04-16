package br.com.fiap.view;

import br.com.fiap.dao.ReceitaDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TesteReceita {

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

    private static Receita nova(String desc, double valor, Long userId) {
        return new Receita(
                desc,
                new BigDecimal(String.valueOf(valor)),
                LocalDate.now(),
                userId
        );
    }

    public static void main(String[] args) {
        ReceitaDao dao = new ReceitaDao();

        final String CPF_EXISTENTE = "12345678911";

        try {
            Long usuarioId = buscarUsuarioIdPorCpf(CPF_EXISTENTE);
            if (usuarioId == null) {
                System.err.println("Usuário não encontrado para o CPF " + CPF_EXISTENTE);
                return;
            }

            dao.insert(nova("Salário",          5000.00, usuarioId));
            dao.insert(nova("Freelancer",       1200.50, usuarioId));
            dao.insert(nova("Rendimento CDB",    350.25, usuarioId));
            dao.insert(nova("Venda de Item",     400.00, usuarioId));
            dao.insert(nova("Cashback",           50.00, usuarioId));

            List<Receita> receitas = dao.getAll();
            System.out.println("=== RECEITAS ===");
            for (Receita r : receitas) {
                System.out.printf("ID=%d | %s | R$ %s | %s | user=%d%n",
                        r.getIdReceita(), r.getDescricao(),
                        r.getValor(), r.getData(), r.getUsuarioId());
            }

        } catch (SQLException e) {
            System.err.println("[ERRO JDBC] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
