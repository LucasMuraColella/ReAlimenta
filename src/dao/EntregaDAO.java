package dao;

import model.Entrega;
import util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public void cadastrar(Entrega entrega) {

        String sql =
                "INSERT INTO entrega "
              + "(data_entrega, responsavel_entrega, "
              + "observacao, status, fk_solicitacao) "
              + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    Date.valueOf(
                            entrega.getData_entrega()
                    )
            );

            stmt.setString(
                    2,
                    entrega.getResponsavel_entrega()
            );

            stmt.setString(
                    3,
                    entrega.getObservacao()
            );

            stmt.setString(
                    4,
                    entrega.getStatus()
            );

            stmt.setInt(
                    5,
                    entrega.getFk_solicitacao()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entrega> listar() {

        List<Entrega> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM entrega "
              + "ORDER BY id_entrega";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                Entrega entrega =
                        new Entrega();

                entrega.setId_entrega(
                        rs.getInt("id_entrega")
                );

                Date data =
                        rs.getDate("data_entrega");

                if (data != null) {

                    entrega.setData_entrega(
                            data.toLocalDate()
                    );
                }

                entrega.setResponsavel_entrega(
                        rs.getString(
                                "responsavel_entrega"
                        )
                );

                entrega.setObservacao(
                        rs.getString(
                                "observacao"
                        )
                );

                entrega.setStatus(
                        rs.getString("status")
                );

                entrega.setFk_solicitacao(
                        rs.getInt(
                                "fk_solicitacao"
                        )
                );

                lista.add(entrega);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Entrega buscarPorId(int id) {

        String sql =
                "SELECT * FROM entrega "
              + "WHERE id_entrega = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                if (rs.next()) {

                    Entrega entrega =
                            new Entrega();

                    entrega.setId_entrega(
                            rs.getInt("id_entrega")
                    );

                    Date data =
                            rs.getDate(
                                    "data_entrega"
                            );

                    if (data != null) {

                        entrega.setData_entrega(
                                data.toLocalDate()
                        );
                    }

                    entrega.setResponsavel_entrega(
                            rs.getString(
                                    "responsavel_entrega"
                            )
                    );

                    entrega.setObservacao(
                            rs.getString(
                                    "observacao"
                            )
                    );

                    entrega.setStatus(
                            rs.getString("status")
                    );

                    entrega.setFk_solicitacao(
                            rs.getInt(
                                    "fk_solicitacao"
                            )
                    );

                    return entrega;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Entrega entrega) {

        String sql =
                "UPDATE entrega SET "
              + "data_entrega = ?, "
              + "responsavel_entrega = ?, "
              + "observacao = ?, "
              + "status = ?, "
              + "fk_solicitacao = ? "
              + "WHERE id_entrega = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    Date.valueOf(
                            entrega.getData_entrega()
                    )
            );

            stmt.setString(
                    2,
                    entrega.getResponsavel_entrega()
            );

            stmt.setString(
                    3,
                    entrega.getObservacao()
            );

            stmt.setString(
                    4,
                    entrega.getStatus()
            );

            stmt.setInt(
                    5,
                    entrega.getFk_solicitacao()
            );

            stmt.setInt(
                    6,
                    entrega.getId_entrega()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql =
                "DELETE FROM entrega "
              + "WHERE id_entrega = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> listarSolicitacoesDisponiveis() {

        List<Integer> lista =
                new ArrayList<>();

        String sql =
                "SELECT s.id_solicitacao "
              + "FROM solicitacao s "
              + "LEFT JOIN entrega e "
              + "ON e.fk_solicitacao = "
              + "s.id_solicitacao "
              + "WHERE s.status = 'APROVADA' "
              + "AND e.id_entrega IS NULL "
              + "ORDER BY s.id_solicitacao";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(
                        rs.getInt(
                                "id_solicitacao"
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}