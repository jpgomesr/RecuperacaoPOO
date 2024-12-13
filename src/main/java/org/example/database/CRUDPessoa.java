package org.example.database;

import org.example.Modals.Pessoa;
import org.example.exceptions.pessoa.PessoaJaExistenteException;
import org.example.exceptions.pessoa.PessoaNaoEncontradaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDPessoa {
    private final Database db = new Database();

    public Pessoa adicionarPessoa(Pessoa pessoa) throws PessoaJaExistenteException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_pessoa (cpf, nome, senha) VALUES (?, ?, ?);"
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getSenha());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PessoaJaExistenteException("Nenhuma linha alterada!");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pessoa.setCodigo(rs.getInt("codigo"));
            }
            return pessoa;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar Pessoa: " + e.getMessage());
            throw new PessoaJaExistenteException(e.getMessage());
        }
    }

    public Pessoa atualizarPessoa(Pessoa pessoa) throws PessoaNaoEncontradaException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE tb_pessoa SET cpf = ?, nome = ?, senha = ?," +
                    " codigo_pet = ? WHERE codigo = ?");
            ps.setLong(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getSenha());
            ps.setInt(4, pessoa.getPet().getCodigo());
            ps.setInt(5, pessoa.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PessoaNaoEncontradaException("Nenhuma linha alterada!");
            }
            return pessoa;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Pessoa: " + e.getMessage());
            throw new PessoaNaoEncontradaException(e.getMessage());
        }
    }

    public Pessoa buscarPessoaPorCpf(long cpf) throws PessoaNaoEncontradaException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_pessoa WHERE cpf = ?;");
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            Pessoa pessoa = new Pessoa();
            if (rs.next()) {
                pessoa = new Pessoa(rs.getInt("codigo"), rs.getLong("cpf"),
                        rs.getString("nome"), rs.getString("senha"),
                        rs.getInt("codigo_pet"));
            }
            return pessoa;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Pessoa: " + e.getMessage());
            throw new PessoaNaoEncontradaException(e.getMessage());
        }
    }

    public ArrayList<Pessoa> buscarTodasPessoas() throws PessoaNaoEncontradaException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_pessoa;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            while (rs.next()) {
                pessoas.add(new Pessoa(rs.getInt("codigo"), rs.getLong("cpf"),
                        rs.getString("nome"), rs.getString("senha"),
                        rs.getInt("codigo_pet")));
            }
            return pessoas;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todas Pessoas: " + e.getMessage());
            throw new PessoaNaoEncontradaException(e.getMessage());
        }
    }

    public boolean removerPessoa(Pessoa pessoa) {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_pessoa WHERE codigo = ?;");
            ps.setInt(1, pessoa.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PessoaNaoEncontradaException("Nenhuma linha alterada!");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover Pessoa: " + e.getMessage());
            throw new PessoaNaoEncontradaException(e.getMessage());
        }
    }
}
