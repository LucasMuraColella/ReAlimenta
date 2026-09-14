package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public List<Object[]> relatorioDoacoes() {

        List<Object[]> lista = new ArrayList<>();

        String sql
                = "SELECT "
                + "d.nome AS doador, "
                + "a.nome AS alimento, "
                + "a.quantidade, "
                + "a.unidade_medida, "
                + "a.data_validade "
                + "FROM doador d "
                + "INNER JOIN alimento a "
                + "ON d.id_doador = a.fk_doador "
                + "ORDER BY d.nome, a.nome";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs
                = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("doador"),
                    rs.getString("alimento"),
                    rs.getBigDecimal("quantidade"),
                    rs.getString("unidade_medida"),
                    rs.getDate("data_validade")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Object[]> relatorioSolicitacoes() {

        List<Object[]> lista = new ArrayList<>();

        String sql
                = "SELECT "
                + "s.id_solicitacao, "
                + "i.nome AS instituicao, "
                + "s.data_solicitacao, "
                + "s.status "
                + "FROM solicitacao s "
                + "INNER JOIN instituicao i "
                + "ON i.id_instituicao = "
                + "s.fk_instituicao "
                + "ORDER BY s.id_solicitacao";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs
                = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getInt("id_solicitacao"),
                    rs.getString("instituicao"),
                    rs.getDate("data_solicitacao"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Object[]> relatorioEntregas() {

        List<Object[]> lista = new ArrayList<>();

        String sql
                = "SELECT "
                + "id_entrega, "
                + "fk_solicitacao, "
                + "data_entrega, "
                + "responsavel_entrega, "
                + "status "
                + "FROM entrega "
                + "ORDER BY data_entrega";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs
                = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getInt("id_entrega"),
                    rs.getInt("fk_solicitacao"),
                    rs.getDate("data_entrega"),
                    rs.getString(
                    "responsavel_entrega"
                    ),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Object[]> relatorioEstoque() {

        List<Object[]> lista = new ArrayList<>();

        String sql
                = "SELECT "
                + "a.nome AS alimento, "
                + "c.nome AS categoria, "
                + "a.quantidade, "
                + "a.unidade_medida, "
                + "a.data_validade, "
                + "a.status "
                + "FROM alimento a "
                + "INNER JOIN categoria c "
                + "ON c.id_categoria = "
                + "a.fk_categoria "
                + "ORDER BY a.data_validade";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs
                = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("alimento"),
                    rs.getString("categoria"),
                    rs.getBigDecimal("quantidade"),
                    rs.getString("unidade_medida"),
                    rs.getDate("data_validade"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
