package br.com.fiap.view;

import br.com.fiap.dao.MetaDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.Meta;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class TesteMeta {

    private static Long buscarUsuarioIdPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE cpf = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? rs.getLong(1) : null; }
        }
    }

    private static Meta nova(String obj, double desejado, double atual, LocalDate limite, Long userId) {
        return new Meta(obj,
                new BigDecimal(String.valueOf(desejado)),
                new BigDecimal(String.valueOf(atual)),
                limite,
                userId);
    }

    public static void main(String[] args) {
        MetaDao dao = new MetaDao();
        final String CPF = "12345678911";

        try {
            Long userId = buscarUsuarioIdPorCpf(CPF);
            if (userId == null) { System.err.println("Usuário não encontrado."); return; }

            dao.insert(nova("Reserva de Emergência", 10000.00, 1500.00, LocalDate.now().plusMonths(6), userId));
            dao.insert(nova("Viagem",                 8000.00,  500.00, LocalDate.now().plusMonths(9), userId));
            dao.insert(nova("Notebook",               6000.00,  800.00, LocalDate.now().plusMonths(3), userId));
            dao.insert(nova("Curso",                  3000.00,  300.00, LocalDate.now().plusMonths(2), userId));
            dao.insert(nova("Investimento longo prazo", 50000.00, 5000.00, LocalDate.now().plusYears(2), userId));

            List<Meta> metas = dao.getAll();
            System.out.println("=== METAS ===");
            for (Meta m : metas) {
                System.out.printf("ID=%d | %s | desejado=%s | atual=%s | limite=%s | user=%d%n",
                        m.getIdMeta(), m.getObjetivo(), m.getValorDesejado(),
                        m.getValorAtual(), m.getDataLimite(), m.getUsuarioId());
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
