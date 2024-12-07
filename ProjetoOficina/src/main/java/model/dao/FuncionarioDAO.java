/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.DBConnection;
import model.bean.Funcionario;

/**
 *
 * @author lucas
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Método para adicionar um novo funcionário
    public void adicionarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome, cargo, telefone, email, salario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setDouble(5, funcionario.getSalario());
            
            stmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cargo"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getDouble("salario")
                );
                funcionarios.add(funcionario); // Adiciona à lista de funcionários
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios; // Retorna a lista de funcionários
    }

    // Método para atualizar as informações de um funcionário
    public boolean atualizarFuncionario(Funcionario funcionario) {
    String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, telefone = ?, email = ?, salario = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCargo());
        stmt.setString(3, funcionario.getTelefone());
        stmt.setString(4, funcionario.getEmail());
        stmt.setDouble(5, funcionario.getSalario());
        stmt.setInt(6, funcionario.getId());
        return stmt.executeUpdate() > 0; // Retorna true se a atualização afetou linhas
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Retorna false se houver um erro
    }
}

// Método para excluir um funcionário
public boolean excluirFuncionario(int id) {
    String sqlServico = "DELETE FROM servicos WHERE funcionarios_id = ?";
    String sqlFuncionario = "DELETE FROM funcionarios WHERE id = ?";

    try (Connection conn = DBConnection.getConnection()) {
        // Inicia uma transação
        conn.setAutoCommit(false);

        try (PreparedStatement stmtServico = conn.prepareStatement(sqlServico)) {
            stmtServico.setInt(1, id);
            stmtServico.executeUpdate();  // Exclui os serviços relacionados

            // Agora exclui o funcionário
            try (PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {
                stmtFuncionario.setInt(1, id);
                int linhasAfetadas = stmtFuncionario.executeUpdate();  // Exclui o funcionário

                // Confirma a transação se tudo ocorrer bem
                conn.commit();

                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            conn.rollback(); // Desfaz a transação em caso de erro
            e.printStackTrace();
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna falso se algo der errado
}


    // Método para buscar um funcionário pelo ID
    public Funcionario buscarFuncionarioPorId(int id) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionarios WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cargo"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getDouble("salario")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario; // Retorna o funcionário encontrado
    }
}

