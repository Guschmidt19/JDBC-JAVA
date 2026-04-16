package br.com.fiap.view;

import br.com.fiap.dao.DividaDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Divida;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TesteDivida {

    private static Long buscarUsuarioIdPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE cpf = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getLong(1) : null; }
        }
    }

    private static Divida nova(String credor, double total, double paga, LocalDate venc, Long userId) {
        return new Divida(credor,
                new BigDecimal(String.valueOf(total)),
                new BigDecimal(String.valueOf(paga)),
                venc,
                userId);
    }

    public static void main(String[] args) {
        DividaDao dao = new DividaDao();
        final String CPF = "12345678911";

        try {
            Long userId = buscarUsuarioIdPorCpf(CPF);
            if (userId == null) { System.err.println("Usuário não encontrado."); return; }

            dao.insert(nova("Banco X",  5000.00, 1000.00, LocalDate.now().plusDays(10), userId));
            dao.insert(nova("Loja Y",    800.00,  200.00, LocalDate.now().plusDays(5),  userId));
            dao.insert(nova("Cartão Z", 1500.00,  150.00, LocalDate.now().plusDays(20), userId));
            dao.insert(nova("Financi.", 30000.00, 5000.00,LocalDate.now().plusMonths(1), userId));
            dao.insert(nova("Emprést.", 10000.00, 1000.00,LocalDate.now().plusWeeks(3),  userId));

            List<Divida> todas = dao.getAll();
            System.out.println("=== DÍVIDAS ===");
            for (Divida d : todas) {
                System.out.printf("ID=%d | %s | Total=%s | Pago=%s | Venc=%s | user=%d%n",
                        d.getIdDivida(), d.getCredor(), d.getValorTotal(), d.getValorPaga(),
                        d.getVencimento(), d.getUsuarioId());
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
