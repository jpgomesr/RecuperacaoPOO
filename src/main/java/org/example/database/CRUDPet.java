package org.example.database;

import org.example.Modals.Pet;
import org.example.exceptions.pet.PetJaExistenteException;
import org.example.exceptions.pet.PetNaoEncontradoException;

import java.sql.*;
import java.util.ArrayList;

public class CRUDPet {
    private final Database db = new Database();

    public Pet adicionarPet(Pet pet) throws PetJaExistenteException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_pet (nome, vivo, acordado, sede, fome, " +
                            "energia, diversao, higiene, vontade_banheiro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getNome());
            ps.setBoolean(2, pet.getVivo());
            ps.setBoolean(3, pet.getAcordado());
            ps.setInt(4, pet.getSede());
            ps.setInt(5, pet.getFome());
            ps.setInt(6, pet.getEnergia());
            ps.setInt(7, pet.getDiversao());
            ps.setInt(8, pet.getHigiene());
            ps.setInt(9, pet.getVontadeBanheiro());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PetJaExistenteException("Nenhuma linha alterada!");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pet.setCodigo(rs.getInt("codigo"));
            }
            return pet;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar Pet: " + e.getMessage());
            throw new PetJaExistenteException(e.getMessage());
        }
    }

    public Pet atualizarPet(Pet pet) throws PetNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE tb_pet SET nome = ?, vivo = ?, acordado = ?, " +
                    "sede = ?, fome = ?, energia = ?, diversao = ?, higiene = ?, vontade_banheiro = ? " +
                    "WHERE codigo = ?;");
            ps.setString(1, pet.getNome());
            ps.setBoolean(2, pet.getVivo());
            ps.setBoolean(3, pet.getAcordado());
            ps.setInt(4, pet.getSede());
            ps.setInt(5, pet.getFome());
            ps.setInt(6, pet.getEnergia());
            ps.setInt(7, pet.getDiversao());
            ps.setInt(8, pet.getHigiene());
            ps.setInt(9, pet.getVontadeBanheiro());
            ps.setInt(10, pet.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PetNaoEncontradoException("Nenhuma linha alterada!");
            }
            return pet;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Pet: " + e.getMessage());
            throw new PetNaoEncontradoException(e.getMessage());
        }
    }

    public Pet buscarPetPorCodigo(int codigo) throws PetNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_pet WHERE codigo = ?;");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            Pet pet = new Pet();
            if (rs.next()) {
                pet = new Pet(rs.getInt("codigo"), rs.getString("nome"),
                        rs.getBoolean("vivo"), rs.getBoolean("acordado"),
                        rs.getInt("sede"), rs.getInt("fome"),
                        rs.getInt("energia"), rs.getInt("diversao"),
                        rs.getInt("higiene"), rs.getInt("vontade_banheiro"));
            }
            return pet;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Pet: " + e.getMessage());
            throw new PetNaoEncontradoException(e.getMessage());
        }
    }

    public Pet buscarPetPorNome(String nome) throws PetNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_pet WHERE nome = ?;");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            Pet pet = new Pet();
            if (rs.next()) {
                pet = new Pet(rs.getInt("codigo"), rs.getString("nome"),
                        rs.getBoolean("vivo"), rs.getBoolean("acordado"),
                        rs.getInt("sede"), rs.getInt("fome"),
                        rs.getInt("energia"), rs.getInt("diversao"),
                        rs.getInt("higiene"), rs.getInt("vontade_banheiro"));
            }
            return pet;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Pet: " + e.getMessage());
            throw new PetNaoEncontradoException(e.getMessage());
        }
    }

    public ArrayList<Pet> buscarTodosPets() throws PetNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_pet;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Pet> pets = new ArrayList<>();
            while (rs.next()) {
                pets.add(new Pet(rs.getInt("codigo"), rs.getString("nome"),
                        rs.getBoolean("vivo"), rs.getBoolean("acordado"),
                        rs.getInt("sede"), rs.getInt("fome"),
                        rs.getInt("energia"), rs.getInt("diversao"),
                        rs.getInt("higiene"), rs.getInt("vontade_banheiro")));
            }
            return pets;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos Pets: " + e.getMessage());
            throw new PetNaoEncontradoException(e.getMessage());
        }
    }

    public boolean removerPet(Pet pet) throws PetNaoEncontradoException {
        try (Connection con = db.getConnection()) {
            PreparedStatement ps = con.prepareStatement("REMOVE FROM tb_pet WHERE codigo = ?;");
            ps.setInt(1, pet.getCodigo());
            int linhasAlteradas = ps.executeUpdate();
            if (linhasAlteradas < 1) {
                throw new PetNaoEncontradoException("Nenhuma linha alterada!");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover Pet: " + e.getMessage());
            throw new PetNaoEncontradoException(e.getMessage());
        }
    }
}
