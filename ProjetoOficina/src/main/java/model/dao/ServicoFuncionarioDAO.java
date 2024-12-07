/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author lucas
 */
import conexao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.ServicoFuncionario;

public class ServicoFuncionarioDAO {

    // Adicionar um relacionamento
    public void adicionarRelacionamento(int servicoId, int funcionarioId) {
        String sql = "INSERT INTO servicos_has_funcionarios (servico_id, funcionario_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            stmt.setInt(2, funcionarioId);
            stmt.executeUpdate();
            System.out.println("Relacionamento adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os relacionamentos
    public List<ServicoFuncionario> listarTodos() {
        String sql = "SELECT servico_id, funcionario_id FROM servicos_has_funcionarios";
        List<ServicoFuncionario> relacionamentos = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int servicoId = rs.getInt("servico_id");
                int funcionarioId = rs.getInt("funcionario_id");
                relacionamentos.add(new ServicoFuncionario(servicoId, funcionarioId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relacionamentos;
    }

    // Atualizar um relacionamento
    public void atualizarRelacionamento(int servicoId, int funcionarioId, int novoServicoId, int novoFuncionarioId) {
        String sql = "UPDATE servicos_has_funcionarios SET servico_id = ?, funcionario_id = ? WHERE servico_id = ? AND funcionario_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoServicoId);
            stmt.setInt(2, novoFuncionarioId);
            stmt.setInt(3, servicoId);
            stmt.setInt(4, funcionarioId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relacionamento atualizado com sucesso!");
            } else {
                System.out.println("Nenhum relacionamento encontrado para atualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir um relacionamento específico
    public void excluirRelacionamento(int servicoId, int funcionarioId) {
        String sql = "DELETE FROM servicos_has_funcionarios WHERE servico_id = ? AND funcionario_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            stmt.setInt(2, funcionarioId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relacionamento excluído com sucesso!");
            } else {
                System.out.println("Nenhum relacionamento encontrado para excluir.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir todos os relacionamentos de um funcionário
    public void excluirTodosRelacionamentosFuncionario(int funcionarioId) {
        String sql = "DELETE FROM servicos_has_funcionarios WHERE funcionario_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionarioId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " relacionamentos excluídos para o funcionário ID " + funcionarioId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir todos os relacionamentos de um serviço
    public void excluirTodosRelacionamentosServico(int servicoId) {
        String sql = "DELETE FROM servicos_has_funcionarios WHERE servico_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " relacionamentos excluídos para o serviço ID " + servicoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

