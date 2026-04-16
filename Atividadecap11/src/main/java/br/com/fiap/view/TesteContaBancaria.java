package br.com.fiap.view;

import br.com.fiap.dao.ContaBancariaDao;
import br.com.fiap.infra.ConnectionFactory;
import br.com.fiap.model.ContaBancaria;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class TesteContaBancaria {

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

    private static ContaBancaria nova(String banco, String agencia, String numero,
                                      String tipo, double saldo, Long userId) {
        return new ContaBancaria(banco, agencia, numero, tipo,
                new BigDecimal(String.valueOf(saldo)), userId);
    }

    public static void main(String[] args) {
        ContaBancariaDao dao = new ContaBancariaDao();

        final String CPF_EXISTENTE = "12345678911";

        try {
            Long usuarioId = buscarUsuarioIdPorCpf(CPF_EXISTENTE);
            if (usuarioId == null) {
                System.err.println("Usuário não encontrado para o CPF " + CPF_EXISTENTE);
                return;
            }

            dao.insert(nova("Santander", "1234", "111111-1", "Corrente", 1500.00, usuarioId));
            dao.insert(nova("Santander", "1234", "222222-2", "Poupança",  300.00,  usuarioId));
            dao.insert(nova("Itaú",      "4321", "333333-3", "Corrente",  50.00,   usuarioId));
            dao.insert(nova("Bradesco",  "9999", "444444-4", "Salário",   0.00,    usuarioId));
            dao.insert(nova("Nubank",    "0001", "555555-5", "Pagamento", 10.00,   usuarioId));

            List<ContaBancaria> contas = dao.getAll();
            System.out.println("=== CONTAS BANCÁRIAS ===");
            for (ContaBancaria c : contas) {
                System.out.printf("ID=%d | %s | Ag %s | Nº %s | Tipo=%s | Saldo=%s | user=%d%n",
                        c.getIdConta(), c.getBanco(), c.getAgencia(), c.getNumero(),
                        c.getTipo(), c.getSaldo(), c.getUsuarioId());
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("[UNIQUE/FOREIGN KEY] " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("[ERRO JDBC] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
