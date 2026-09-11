package dao;

import model.Solicitacao;
import util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDAO {

    public int cadastrar(Solicitacao solicitacao) {

        String sql = "INSERT INTO solicitacao "
                + "(data_solicitacao, observacao, status, "
                + "fk_instituicao, fk_administrador) "
                + "VALUES (?, ?, ?, ?, ?) "
                + "RETURNING id_solicitacao";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    Date.valueOf(
                            solicitacao.getData_solicitacao()
                    )
            );

            stmt.setString(
                    2,
                    solicitacao.getObservacao()
            );

            stmt.setString(
                    3,
                    solicitacao.getStatus()
            );

            stmt.setInt(
                    4,
                    solicitacao.getFk_instituicao()
            );

            if (solicitacao.getFk_administrador() == null) {
                stmt.setNull(
                        5,
                        java.sql.Types.INTEGER
                );
            } else {
                stmt.setInt(
                        5,
                        solicitacao.getFk_administrador()
                );
            }

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(
                            "id_solicitacao"
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Solicitacao> listar() {

        List<Solicitacao> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM solicitacao "
                + "ORDER BY id_solicitacao";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Solicitacao solicitacao
                        = new Solicitacao();

                solicitacao.setId_solicitacao(
                        rs.getInt("id_solicitacao")
                );

                Date data
                        = rs.getDate("data_solicitacao");

                if (data != null) {
                    solicitacao.setData_solicitacao(
                            data.toLocalDate()
                    );
                }

                solicitacao.setObservacao(
                        rs.getString("observacao")
                );

                solicitacao.setStatus(
                        rs.getString("status")
                );

                solicitacao.setFk_instituicao(
                        rs.getInt("fk_instituicao")
                );

                int fk_administrador
                        = rs.getInt("fk_administrador");

                if (rs.wasNull()) {
                    solicitacao.setFk_administrador(null);
                } else {
                    solicitacao.setFk_administrador(
                            fk_administrador
                    );
                }

                lista.add(solicitacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Solicitacao> listarPendentes() {

        List<Solicitacao> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM solicitacao "
                + "WHERE status = 'PENDENTE' "
                + "ORDER BY id_solicitacao";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs
                = stmt.executeQuery()) {

            while (rs.next()) {

                Solicitacao solicitacao
                        = new Solicitacao();

                solicitacao.setId_solicitacao(
                        rs.getInt("id_solicitacao")
                );

                Date data
                        = rs.getDate("data_solicitacao");

                if (data != null) {

                    solicitacao.setData_solicitacao(
                            data.toLocalDate()
                    );
                }

                solicitacao.setObservacao(
                        rs.getString("observacao")
                );

                solicitacao.setStatus(
                        rs.getString("status")
                );

                solicitacao.setFk_instituicao(
                        rs.getInt("fk_instituicao")
                );

                lista.add(solicitacao);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    public Solicitacao buscarPorId(int id) {

        String sql
                = "SELECT * FROM solicitacao "
                + "WHERE id_solicitacao = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Solicitacao solicitacao
                            = new Solicitacao();

                    solicitacao.setId_solicitacao(
                            rs.getInt("id_solicitacao")
                    );

                    Date data
                            = rs.getDate("data_solicitacao");

                    if (data != null) {
                        solicitacao.setData_solicitacao(
                                data.toLocalDate()
                        );
                    }

                    solicitacao.setObservacao(
                            rs.getString("observacao")
                    );

                    solicitacao.setStatus(
                            rs.getString("status")
                    );

                    solicitacao.setFk_instituicao(
                            rs.getInt("fk_instituicao")
                    );

                    int fk_administrador
                            = rs.getInt("fk_administrador");

                    if (rs.wasNull()) {
                        solicitacao.setFk_administrador(null);
                    } else {
                        solicitacao.setFk_administrador(
                                fk_administrador
                        );
                    }

                    return solicitacao;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizar(
            Solicitacao solicitacao) {

        String sql
                = "UPDATE solicitacao SET "
                + "observacao = ?, "
                + "fk_instituicao = ? "
                + "WHERE id_solicitacao = ? "
                + "AND status = 'PENDENTE'";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setString(
                    1,
                    solicitacao.getObservacao()
            );

            stmt.setInt(
                    2,
                    solicitacao.getFk_instituicao()
            );

            stmt.setInt(
                    3,
                    solicitacao.getId_solicitacao()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean cancelar(int id_solicitacao) {

        String sql
                = "UPDATE solicitacao "
                + "SET status = 'CANCELADA' "
                + "WHERE id_solicitacao = ? "
                + "AND status = 'PENDENTE'";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id_solicitacao);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean aprovar(
            int id_solicitacao,
            int fk_administrador) {

        String sqlItens
                = "SELECT i.fk_alimento, "
                + "i.quantidade_solicitada, "
                + "a.quantidade, "
                + "a.data_validade "
                + "FROM item_solicitacao i "
                + "INNER JOIN alimento a "
                + "ON a.id_alimento = i.fk_alimento "
                + "WHERE i.fk_solicitacao = ?";

        String sqlAtualizaAlimento
                = "UPDATE alimento "
                + "SET quantidade = ?, "
                + "status = ? "
                + "WHERE id_alimento = ?";

        String sqlSolicitacao
                = "UPDATE solicitacao "
                + "SET status = 'APROVADA', "
                + "fk_administrador = ? "
                + "WHERE id_solicitacao = ? "
                + "AND status = 'PENDENTE'";

        Connection conexao = null;

        try {

            conexao = Conexao.conectar();

            // Começa uma transação
            conexao.setAutoCommit(false);

            // =====================================================
            // 1. Verifica se existe estoque suficiente
            // =====================================================
            try (PreparedStatement stmtItens
                    = conexao.prepareStatement(sqlItens)) {

                stmtItens.setInt(
                        1,
                        id_solicitacao
                );

                try (ResultSet rs
                        = stmtItens.executeQuery()) {

                    while (rs.next()) {

                        java.math.BigDecimal quantidade_solicitada
                                = rs.getBigDecimal(
                                        "quantidade_solicitada"
                                );

                        java.math.BigDecimal quantidade_disponivel
                                = rs.getBigDecimal(
                                        "quantidade"
                                );

                        // Não permite aprovação
                        // maior que o estoque
                        if (quantidade_solicitada.compareTo(
                                quantidade_disponivel
                        ) > 0) {

                            conexao.rollback();

                            return false;
                        }
                    }
                }
            }

            // =====================================================
            // 2. Dá baixa no estoque e atualiza o status
            // =====================================================
            try (PreparedStatement stmtItens
                    = conexao.prepareStatement(sqlItens); PreparedStatement stmtAtualiza
                    = conexao.prepareStatement(
                            sqlAtualizaAlimento)) {

                stmtItens.setInt(
                        1,
                        id_solicitacao
                );

                try (ResultSet rs
                        = stmtItens.executeQuery()) {

                    while (rs.next()) {

                        int fk_alimento
                                = rs.getInt(
                                        "fk_alimento"
                                );

                        java.math.BigDecimal quantidade_solicitada
                                = rs.getBigDecimal(
                                        "quantidade_solicitada"
                                );

                        java.math.BigDecimal quantidade_disponivel
                                = rs.getBigDecimal(
                                        "quantidade"
                                );

                        java.sql.Date data_validade_sql
                                = rs.getDate(
                                        "data_validade"
                                );

                        java.time.LocalDate data_validade
                                = data_validade_sql
                                        .toLocalDate();

                        // Calcula a nova quantidade
                        java.math.BigDecimal nova_quantidade
                                = quantidade_disponivel
                                        .subtract(
                                                quantidade_solicitada
                                        );

                        // =================================================
                        // Define automaticamente o status
                        // =================================================
                        String novo_status;

                        if (data_validade.isBefore(
                                java.time.LocalDate.now()
                        )) {

                            novo_status = "VENCIDO";

                        } else if (nova_quantidade.compareTo(
                                java.math.BigDecimal.ZERO
                        ) == 0) {

                            novo_status = "ESGOTADO";

                        } else {

                            novo_status = "DISPONIVEL";
                        }

                        // Atualiza quantidade e status
                        stmtAtualiza.setBigDecimal(
                                1,
                                nova_quantidade
                        );

                        stmtAtualiza.setString(
                                2,
                                novo_status
                        );

                        stmtAtualiza.setInt(
                                3,
                                fk_alimento
                        );

                        stmtAtualiza.executeUpdate();
                    }
                }
            }

            // =====================================================
            // 3. Aprova a solicitação
            // =====================================================
            try (PreparedStatement stmtSolicitacao
                    = conexao.prepareStatement(
                            sqlSolicitacao)) {

                stmtSolicitacao.setInt(
                        1,
                        fk_administrador
                );

                stmtSolicitacao.setInt(
                        2,
                        id_solicitacao
                );

                int linhas
                        = stmtSolicitacao.executeUpdate();

                if (linhas == 0) {

                    conexao.rollback();

                    return false;
                }
            }

            // =====================================================
            // 4. Confirma tudo
            // =====================================================
            conexao.commit();

            return true;

        } catch (SQLException e) {

            try {

                if (conexao != null) {
                    conexao.rollback();
                }

            } catch (SQLException ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;

        } finally {

            try {

                if (conexao != null) {

                    conexao.setAutoCommit(true);
                    conexao.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public boolean rejeitar(
            int id_solicitacao,
            int fk_administrador) {

        String sql
                = "UPDATE solicitacao "
                + "SET status = 'REJEITADA', "
                + "fk_administrador = ? "
                + "WHERE id_solicitacao = ? "
                + "AND status = 'PENDENTE'";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setInt(1, fk_administrador);
            stmt.setInt(2, id_solicitacao);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}
