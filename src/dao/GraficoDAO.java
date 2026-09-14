package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GraficoDAO {

    // ============================================================
    // ALIMENTOS CADASTRADOS POR MÊS
    // ============================================================
    public List<Object[]> doacoesPorMes(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> lista
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "TO_CHAR(data_cadastro, 'YYYY-MM') AS mes, "
                + "COUNT(*) AS total "
                + "FROM alimento "
                + "WHERE data_cadastro BETWEEN ? AND ? "
                + "GROUP BY TO_CHAR(data_cadastro, 'YYYY-MM') "
                + "ORDER BY mes";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    java.sql.Date.valueOf(data_inicial)
            );

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(data_final)
            );

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("mes"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // ============================================================
    // ALIMENTOS POR CATEGORIA
    // ============================================================
    public List<Object[]> alimentosPorCategoria(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> lista
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "c.nome AS categoria, "
                + "COUNT(a.id_alimento) AS total "
                + "FROM categoria c "
                + "LEFT JOIN alimento a "
                + "ON a.fk_categoria = c.id_categoria "
                + "AND a.data_cadastro BETWEEN ? AND ? "
                + "GROUP BY c.nome "
                + "ORDER BY total DESC";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    java.sql.Date.valueOf(data_inicial)
            );

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(data_final)
            );

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("categoria"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // ============================================================
    // SOLICITAÇÕES POR STATUS
    // ============================================================
    public List<Object[]> solicitacoesPorStatus(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> lista
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "status, "
                + "COUNT(*) AS total "
                + "FROM solicitacao "
                + "WHERE data_solicitacao BETWEEN ? AND ? "
                + "GROUP BY status "
                + "ORDER BY status";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    java.sql.Date.valueOf(data_inicial)
            );

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(data_final)
            );

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("status"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // ============================================================
    // ALIMENTOS POR STATUS
    // ============================================================
    public List<Object[]> alimentosPorStatus(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> lista
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "status, "
                + "COUNT(*) AS total "
                + "FROM alimento "
                + "WHERE data_cadastro BETWEEN ? AND ? "
                + "GROUP BY status "
                + "ORDER BY status";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setDate(
                    1,
                    java.sql.Date.valueOf(data_inicial)
            );

            stmt.setDate(
                    2,
                    java.sql.Date.valueOf(data_final)
            );

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{
                    rs.getString("status"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }
}