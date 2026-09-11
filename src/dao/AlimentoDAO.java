package dao;

import model.Alimento;
import util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    public void cadastrar(Alimento alimento) {

        String sql = "INSERT INTO alimento "
                + "(nome, descricao, quantidade, unidade_medida, "
                + "data_validade, status, fk_doador, fk_categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNome());
            stmt.setString(2, alimento.getDescricao());
            stmt.setBigDecimal(3, alimento.getQuantidade());
            stmt.setString(4, alimento.getUnidade_medida());
            stmt.setDate(5, Date.valueOf(alimento.getData_validade()));
            stmt.setString(6, alimento.getStatus());
            stmt.setInt(7, alimento.getFk_doador());
            stmt.setInt(8, alimento.getFk_categoria());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Alimento> listar() {

        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT * FROM alimento ORDER BY id_alimento";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Alimento alimento = new Alimento();

                alimento.setId_alimento(
                        rs.getInt("id_alimento")
                );

                alimento.setNome(
                        rs.getString("nome")
                );

                alimento.setDescricao(
                        rs.getString("descricao")
                );

                alimento.setQuantidade(
                        rs.getBigDecimal("quantidade")
                );

                alimento.setUnidade_medida(
                        rs.getString("unidade_medida")
                );

                Date dataValidade =
                        rs.getDate("data_validade");

                if (dataValidade != null) {
                    alimento.setData_validade(
                            dataValidade.toLocalDate()
                    );
                }

                Date dataCadastro =
                        rs.getDate("data_cadastro");

                if (dataCadastro != null) {
                    alimento.setData_cadastro(
                            dataCadastro.toLocalDate()
                    );
                }

                alimento.setStatus(
                        rs.getString("status")
                );

                alimento.setFk_doador(
                        rs.getInt("fk_doador")
                );

                alimento.setFk_categoria(
                        rs.getInt("fk_categoria")
                );

                lista.add(alimento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Alimento buscarPorId(int id) {

        String sql = "SELECT * FROM alimento "
                + "WHERE id_alimento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Alimento alimento = new Alimento();

                    alimento.setId_alimento(
                            rs.getInt("id_alimento")
                    );

                    alimento.setNome(
                            rs.getString("nome")
                    );

                    alimento.setDescricao(
                            rs.getString("descricao")
                    );

                    alimento.setQuantidade(
                            rs.getBigDecimal("quantidade")
                    );

                    alimento.setUnidade_medida(
                            rs.getString("unidade_medida")
                    );

                    Date dataValidade =
                            rs.getDate("data_validade");

                    if (dataValidade != null) {
                        alimento.setData_validade(
                                dataValidade.toLocalDate()
                        );
                    }

                    Date dataCadastro =
                            rs.getDate("data_cadastro");

                    if (dataCadastro != null) {
                        alimento.setData_cadastro(
                                dataCadastro.toLocalDate()
                        );
                    }

                    alimento.setStatus(
                            rs.getString("status")
                    );

                    alimento.setFk_doador(
                            rs.getInt("fk_doador")
                    );

                    alimento.setFk_categoria(
                            rs.getInt("fk_categoria")
                    );

                    return alimento;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Alimento alimento) {

        String sql = "UPDATE alimento SET "
                + "nome = ?, "
                + "descricao = ?, "
                + "quantidade = ?, "
                + "unidade_medida = ?, "
                + "data_validade = ?, "
                + "status = ?, "
                + "fk_doador = ?, "
                + "fk_categoria = ? "
                + "WHERE id_alimento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNome());
            stmt.setString(2, alimento.getDescricao());
            stmt.setBigDecimal(3, alimento.getQuantidade());
            stmt.setString(4, alimento.getUnidade_medida());
            stmt.setDate(
                    5,
                    Date.valueOf(alimento.getData_validade())
            );
            stmt.setString(6, alimento.getStatus());
            stmt.setInt(7, alimento.getFk_doador());
            stmt.setInt(8, alimento.getFk_categoria());
            stmt.setInt(9, alimento.getId_alimento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM alimento "
                + "WHERE id_alimento = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}