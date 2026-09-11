package dao;

import model.ItemSolicitacao;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemSolicitacaoDAO {

    public void cadastrar(
            ItemSolicitacao item) {

        String sql =
                "INSERT INTO item_solicitacao "
                + "(quantidade_solicitada, "
                + "fk_solicitacao, fk_alimento) "
                + "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setBigDecimal(
                    1,
                    item.getQuantidade_solicitada()
            );

            stmt.setInt(
                    2,
                    item.getFk_solicitacao()
            );

            stmt.setInt(
                    3,
                    item.getFk_alimento()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemSolicitacao>
            listarPorSolicitacao(
                    int fk_solicitacao) {

        List<ItemSolicitacao> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM item_solicitacao "
                + "WHERE fk_solicitacao = ? "
                + "ORDER BY id_item";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, fk_solicitacao);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    ItemSolicitacao item =
                            new ItemSolicitacao();

                    item.setId_item(
                            rs.getInt("id_item")
                    );

                    item.setQuantidade_solicitada(
                            rs.getBigDecimal(
                                    "quantidade_solicitada"
                            )
                    );

                    item.setFk_solicitacao(
                            rs.getInt("fk_solicitacao")
                    );

                    item.setFk_alimento(
                            rs.getInt("fk_alimento")
                    );

                    lista.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void excluirPorSolicitacao(
            int fk_solicitacao) {

        String sql =
                "DELETE FROM item_solicitacao "
                + "WHERE fk_solicitacao = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, fk_solicitacao);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}