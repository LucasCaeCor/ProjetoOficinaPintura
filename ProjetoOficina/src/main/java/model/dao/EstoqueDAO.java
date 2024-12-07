/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author lucas
 */

import model.bean.Estoque;
import conexao.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    // Método para criar produto
    public boolean criarProduto(Estoque produto) {
        String sql = "INSERT INTO estoque (produto, quantidade, preco_unitario) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());// Salvando a quantidade
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }return true;
    } 

    // Método para listar produtos
    public List<Estoque> listarProdutos() {
        List<Estoque> produtos = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estoque produto = new Estoque(
                    rs.getInt("id"),
                    rs.getString("produto"),
                    rs.getDouble("preco_unitario"),
                    rs.getInt("quantidade")  // Recuperando a quantidade
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para excluir produto
   public boolean excluirProduto(int id) {
    String sql = "DELETE FROM estoque WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Configura o parâmetro da consulta SQL
        stmt.setInt(1, id);
        
        // Executa a consulta DELETE no banco de dados
        int linhasAfetadas = stmt.executeUpdate();
        
        // Se as linhas afetadas for maior que 0, o produto foi excluído com sucesso
        return linhasAfetadas > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

 

    public boolean editarProduto(Estoque produto) {
     // Corrigindo a sintaxe SQL: a cláusula SET deve vir antes das colunas a serem atualizadas
     String sql = "UPDATE estoque SET produto = ?, quantidade = ?, preco_unitario = ? WHERE id = ?";
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {

         // Preencher os parâmetros da consulta SQL
         stmt.setString(1, produto.getNomeProduto()); // Nome do produto
         stmt.setInt(2, produto.getQuantidade());     // Quantidade
         stmt.setDouble(3, produto.getPreco());      // Preço unitário
         stmt.setInt(4, produto.getId());            // ID do produto para atualização

         // Executar a atualização no banco de dados
         int linhasAfetadas = stmt.executeUpdate();

         // Se as linhas afetadas forem maiores que 0, a atualização foi bem-sucedida
         return linhasAfetadas > 0;

     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
}

