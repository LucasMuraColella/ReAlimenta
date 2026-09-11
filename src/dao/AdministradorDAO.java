package dao;

import model.Administrador;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    public void cadastrar(Administrador administrador) {

        String sql = "INSERT INTO administrador "
                + "(nome, email, senha, telefone, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setString(
                    1,
                    administrador.getNome()
            );

            stmt.setString(
                    2,
                    administrador.getEmail()
            );

            stmt.setString(
                    3,
                    administrador.getSenha()
            );

            stmt.setString(
                    4,
                    administrador.getTelefone()
            );

            stmt.setBoolean(
                    5,
                    administrador.isStatus()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Administrador> listar() {

        List<Administrador> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM administrador "
                + "ORDER BY id_adm";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Administrador administrador
                        = new Administrador();

                administrador.setId_adm(
                        rs.getInt("id_adm")
                );

                administrador.setNome(
                        rs.getString("nome")
                );

                administrador.setEmail(
                        rs.getString("email")
                );

                administrador.setSenha(
                        rs.getString("senha")
                );

                administrador.setTelefone(
                        rs.getString("telefone")
                );

                administrador.setStatus(
                        rs.getBoolean("status")
                );

                lista.add(administrador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Administrador buscarPorId(int id) {

        String sql
                = "SELECT * FROM administrador "
                + "WHERE id_adm = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Administrador administrador
                            = new Administrador();

                    administrador.setId_adm(
                            rs.getInt("id_adm")
                    );

                    administrador.setNome(
                            rs.getString("nome")
                    );

                    administrador.setEmail(
                            rs.getString("email")
                    );

                    administrador.setSenha(
                            rs.getString("senha")
                    );

                    administrador.setTelefone(
                            rs.getString("telefone")
                    );

                    administrador.setStatus(
                            rs.getBoolean("status")
                    );

                    return administrador;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(
            Administrador administrador) {

        String sql
                = "UPDATE administrador SET "
                + "nome = ?, "
                + "email = ?, "
                + "senha = ?, "
                + "telefone = ?, "
                + "status = ? "
                + "WHERE id_adm = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setString(
                    1,
                    administrador.getNome()
            );

            stmt.setString(
                    2,
                    administrador.getEmail()
            );

            stmt.setString(
                    3,
                    administrador.getSenha()
            );

            stmt.setString(
                    4,
                    administrador.getTelefone()
            );

            stmt.setBoolean(
                    5,
                    administrador.isStatus()
            );

            stmt.setInt(
                    6,
                    administrador.getId_adm()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql
                = "DELETE FROM administrador "
                + "WHERE id_adm = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Administrador buscarPorEmail(String email) {

        String sql
                = "SELECT * FROM administrador "
                + "WHERE email = ? "
                + "AND status = TRUE";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt
                = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs
                    = stmt.executeQuery()) {

                if (rs.next()) {

                    Administrador administrador
                            = new Administrador();

                    administrador.setId_adm(
                            rs.getInt("id_adm")
                    );

                    administrador.setNome(
                            rs.getString("nome")
                    );

                    administrador.setEmail(
                            rs.getString("email")
                    );

                    administrador.setSenha(
                            rs.getString("senha")
                    );

                    administrador.setTelefone(
                            rs.getString("telefone")
                    );

                    administrador.setStatus(
                            rs.getBoolean("status")
                    );

                    return administrador;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}
