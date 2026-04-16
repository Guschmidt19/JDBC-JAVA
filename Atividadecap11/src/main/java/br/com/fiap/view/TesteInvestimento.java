package br.com.fiap.view;

import br.com.fiap.dao.InvestimentoDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Investimento;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TesteInvestimento {

    private static Long buscarUsuarioIdPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE cpf = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getLong(1) : null; }
        }
    }

    private static Investimento novo(String tipo, double valor, Long userId) {
        return new Investimento(tipo, new BigDecimal(String.valueOf(valor)), LocalDate.now(), userId);
    }

    public static void main(String[] args) {
        InvestimentoDao dao = new InvestimentoDao();
        final String CPF = "12345678911";

        try {
            Long userId = buscarUsuarioIdPorCpf(CPF);
            if (userId == null) { System.err.println("Usuário não encontrado."); return; }

            dao.insert(novo("Tesouro Selic", 1000.00, userId));
            dao.insert(novo("CDB Liquidez",   500.00,  userId));
            dao.insert(novo("LCI",            750.50,  userId));
            dao.insert(novo("Fundo DI",       320.10,  userId));
            dao.insert(novo("Ações",         1200.00,  userId));

            List<Investimento> todos = dao.getAll();
            System.out.println("=== INVESTIMENTOS ===");
            for (Investimento i : todos) {
                System.out.printf("ID=%d | %s | R$ %s | %s | user=%d%n",
                        i.getIdInvestimento(), i.getTipo(), i.getValor(), i.getData(), i.getUsuarioId());
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }
}
