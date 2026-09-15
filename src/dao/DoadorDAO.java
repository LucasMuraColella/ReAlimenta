package dao;

import model.Doador;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {

    public void cadastrar(Doador doador) {

        String sql = "INSERT INTO doador "
                + "(nome, cpf_cnpj, telefone, cep, rua, numero, bairro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, doador.getNome());
            stmt.setString(2, doador.getCpf_cnpj());
            stmt.setString(3, doador.getTelefone());
            stmt.setString(4, doador.getCep());
            stmt.setString(5, doador.getRua());
            stmt.setString(6, doador.getNumero());
            stmt.setString(7, doador.getBairro());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doador> listar() {

        List<Doador> lista = new ArrayList<>();

        String sql = "SELECT * FROM doador ORDER BY id_doador";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Doador doador = new Doador();

                doador.setId_doador(rs.getInt("id_doador"));
                doador.setNome(rs.getString("nome"));
                doador.setCpf_cnpj(rs.getString("cpf_cnpj"));
                doador.setTelefone(rs.getString("telefone"));
                doador.setCep(rs.getString("cep"));
                doador.setRua(rs.getString("rua"));
                doador.setNumero(rs.getString("numero"));
                doador.setBairro(rs.getString("bairro"));

                lista.add(doador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Doador buscarPorId(int id) {

        String sql = "SELECT * FROM doador WHERE id_doador = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Doador doador = new Doador();

                    doador.setId_doador(
                            rs.getInt("id_doador")
                    );

                    doador.setNome(
                            rs.getString("nome")
                    );

                    doador.setCpf_cnpj(
                            rs.getString("cpf_cnpj")
                    );

                    doador.setTelefone(
                            rs.getString("telefone")
                    );

                    doador.setCep(
                            rs.getString("cep")
                    );

                    doador.setRua(
                            rs.getString("rua")
                    );

                    doador.setNumero(
                            rs.getString("numero")
                    );

                    doador.setBairro(
                            rs.getString("bairro")
                    );

                    return doador;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Doador doador) {

        String sql = "UPDATE doador SET "
                + "nome = ?, "
                + "cpf_cnpj = ?, "
                + "telefone = ?, "
                + "cep = ?, "
                + "rua = ?, "
                + "numero = ?, "
                + "bairro = ? "
                + "WHERE id_doador = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, doador.getNome());
            stmt.setString(2, doador.getCpf_cnpj());
            stmt.setString(3, doador.getTelefone());
            stmt.setString(4, doador.getCep());
            stmt.setString(5, doador.getRua());
            stmt.setString(6, doador.getNumero());
            stmt.setString(7, doador.getBairro());
            stmt.setInt(8, doador.getId_doador());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM doador WHERE id_doador = ?";

        try (Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            // Violação de chave estrangeira
            if ("23503".equals(e.getSQLState())) {
                return false;
            }

            e.printStackTrace();
        }

        return false;
    }
}
