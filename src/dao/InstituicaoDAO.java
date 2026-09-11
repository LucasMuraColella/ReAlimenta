package dao;

import model.Instituicao;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO {

    public void cadastrar(Instituicao instituicao) {

        String sql = "INSERT INTO instituicao "
                + "(nome, cnpj, responsavel, cep, rua, numero, bairro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, instituicao.getNome());
            stmt.setString(2, instituicao.getCnpj());
            stmt.setString(3, instituicao.getResponsavel());
            stmt.setString(4, instituicao.getCep());
            stmt.setString(5, instituicao.getRua());
            stmt.setString(6, instituicao.getNumero());
            stmt.setString(7, instituicao.getBairro());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Instituicao> listar() {

        List<Instituicao> lista = new ArrayList<>();

        String sql = "SELECT * FROM instituicao ORDER BY id_instituicao";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Instituicao instituicao = new Instituicao();

                instituicao.setId_instituicao(
                        rs.getInt("id_instituicao")
                );

                instituicao.setNome(
                        rs.getString("nome")
                );
                
                instituicao.setCnpj(
                        rs.getString("cnpj")
                );

                instituicao.setResponsavel(
                        rs.getString("responsavel")
                );

                instituicao.setCep(
                        rs.getString("cep")
                );

                instituicao.setRua(
                        rs.getString("rua")
                );

                instituicao.setNumero(
                        rs.getString("numero")
                );

                instituicao.setBairro(
                        rs.getString("bairro")
                );

                lista.add(instituicao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Instituicao buscarPorId(int id) {

        String sql = "SELECT * FROM instituicao "
                + "WHERE id_instituicao = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Instituicao instituicao = new Instituicao();

                    instituicao.setId_instituicao(
                            rs.getInt("id_instituicao")
                    );
                    
                    instituicao.setNome(
                            rs.getString("nome")
                    );

                    instituicao.setCnpj(
                            rs.getString("cnpj")
                    );

                    instituicao.setResponsavel(
                            rs.getString("responsavel")
                    );

                    instituicao.setCep(
                            rs.getString("cep")
                    );

                    instituicao.setRua(
                            rs.getString("rua")
                    );

                    instituicao.setNumero(
                            rs.getString("numero")
                    );

                    instituicao.setBairro(
                            rs.getString("bairro")
                    );

                    return instituicao;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Instituicao instituicao) {

        String sql = "UPDATE instituicao SET "
                + "nome = ?, "
                + "cnpj = ?, "
                + "responsavel = ?, "
                + "cep = ?, "
                + "rua = ?, "
                + "numero = ?, "
                + "bairro = ? "
                + "WHERE id_instituicao = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, instituicao.getNome());
            stmt.setString(2, instituicao.getCnpj());
            stmt.setString(3, instituicao.getResponsavel());
            stmt.setString(4, instituicao.getCep());
            stmt.setString(5, instituicao.getRua());
            stmt.setString(6, instituicao.getNumero());
            stmt.setString(7, instituicao.getBairro());
            stmt.setInt(8, instituicao.getId_instituicao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM instituicao "
                + "WHERE id_instituicao = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}