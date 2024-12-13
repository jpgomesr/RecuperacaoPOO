package org.example.database;

import org.example.Modals.Alimento;
import org.example.exceptions.alimento.AlimentoJaExistenteException;
import org.example.exceptions.alimento.AlimentoNaoEncontradoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDAlimento {
    private Database db = new Database();

    public Alimento adicionarAlimento(Alimento alimento) throws AlimentoJaExistenteException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_alimento (nome, nutricao) VALUES (?, ?);"
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alimento.getNome());
            ps.setInt(2, alimento.getNutricao());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new AlimentoJaExistenteException("Nenhuma linha alterada!");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alimento.setCodigo(rs.getInt("codigo"));
            }
            return alimento;
        } catch (SQLException e) {
            System.err.println("Erro ao criar Alimento: " + e.getMessage());
            throw new AlimentoJaExistenteException(e.getMessage());
        }
    }

    public Alimento atualizarAlimento(Alimento alimento) throws AlimentoNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE tb_alimento SET nome = ?, nutricao = ? " +
                    "WHERE codigo = ?;");
            ps.setString(1, alimento.getNome());
            ps.setInt(2, alimento.getNutricao());
            ps.setInt(3, alimento.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new AlimentoNaoEncontradoException("Nenhuma linhas alterada!");
            }
            return alimento;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Alimento: " + e.getMessage());
            throw new AlimentoNaoEncontradoException(e.getMessage());
        }
    }

    public Alimento buscarAlimentoPorCodigo(int codigo) throws AlimentoNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_alimento WHERE codigo = ?;");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            return new Alimento(rs.getInt("codigo"), rs.getString("nome"),
                    rs.getInt("nutricao"));
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alimento: " + e.getMessage());
            throw new AlimentoNaoEncontradoException(e.getMessage());
        }
    }

    public Alimento buscarAlimentoPorNome(String nome) throws AlimentoNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_alimento WHERE nome = ?;");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            Alimento alimento = new Alimento();
            if (rs.next()) {
                alimento.setCodigo(rs.getInt("codigo"));
                alimento.setNome(rs.getString("nome"));
                alimento.setNutricao(rs.getInt("nutricao"));
            }
            return alimento;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Alimento: " + e.getMessage());
            throw new AlimentoNaoEncontradoException(e.getMessage());
        }
    }

    public ArrayList<Alimento> buscarTodosAlimentos() throws AlimentoNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_alimento;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Alimento> alimentos = new ArrayList<>();
            while (rs.next()) {
                alimentos.add(new Alimento(rs.getInt("codigo"), rs.getString("nome"),
                        rs.getInt("nutricao")));
            }
            return alimentos;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos alimentos: " + e.getMessage());
            throw new AlimentoNaoEncontradoException(e.getMessage());
        }
    }

    public boolean removerAlimento(Alimento alimento) throws AlimentoNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_alimento WHERE codigo = ?;");
            ps.setInt(1, alimento.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new AlimentoNaoEncontradoException("Nenhuma linha alterada!");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover Alimento: " + e.getMessage());
            throw new AlimentoNaoEncontradoException(e.getMessage());
        }
    }
}
