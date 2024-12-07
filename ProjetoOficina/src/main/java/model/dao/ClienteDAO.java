/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author lucas
 */
import model.bean.Cliente;
import conexao.DBConnection;
import java.util.ArrayList;
import java.sql.*;

public class ClienteDAO {
    public void criarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, email, endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Cliente cliente) {
    String sql = "INSERT INTO clientes (nome, telefone, email, endereco) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getTelefone());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getEndereco());

        stmt.executeUpdate();
        System.out.println("Cliente salvo com sucesso!");
    } catch (SQLException e) {
        System.err.println("Erro ao salvar cliente: " + e.getMessage());
    }
}

     public ArrayList<Cliente> read() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre os resultados e cria os objetos Cliente
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");

                System.out.println("ID: " + id + ", Nome: " + nome); // Para verificar os dados

                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setEndereco(endereco);

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler clientes: " + e.getMessage());
        }
        
        // Retorna a lista de clientes
        return clientes;
    }
      // Método para atualizar os dados de um cliente
    public boolean editar(Cliente cliente) {
    String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, endereco = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getTelefone());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getEndereco());
        stmt.setInt(5, cliente.getId());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Retorna true se a atualização foi bem-sucedida
    } catch (SQLException e) {
        System.err.println("Erro ao editar cliente: " + e.getMessage());
        return false;
    }
}

    // Método para excluir um cliente pelo ID e retornar se foi bem-sucedido
public boolean excluir(int id) {
    String sql = "DELETE FROM clientes WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Retorna true se algum registro foi excluído
    } catch (SQLException e) {
        System.err.println("Erro ao excluir cliente: " + e.getMessage());
        return false; // Retorna false se ocorrer algum erro
    }
}

    // Método para buscar um cliente pelo ID
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setEndereco(rs.getString("endereco"));

                    return cliente;
                } else {
                    System.out.println("Nenhum cliente encontrado com o ID especificado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null; // Retorna null caso o cliente não seja encontrado
    }
    
    public boolean adicionar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, email, endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se a inserção foi bem-sucedida
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar cliente: " + e.getMessage());
            return false;
        }
    }
}
