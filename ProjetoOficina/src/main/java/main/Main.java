/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author lucas
 */
import java.time.LocalDate;
import model.bean.Cliente;
import model.bean.Estoque;
import model.bean.Servico;
import model.bean.NotaFiscal;
import model.dao.ClienteDAO;
import model.dao.EstoqueDAO;
import model.dao.ServicoDAO;
import model.dao.NotaFiscalDAO;

public class Main {
    public static void main(String[] args) {
        // Criando um cliente com todos os dados
        Cliente cliente = new Cliente("Lucas", "1234-5678", "lucas@example.com", "Rua 123");

        // Exibindo os dados do cliente
        System.out.println(cliente);
        
        // Caso tenha um banco de dados, você pode salvar o cliente através do DAO
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.salvar(cliente); // Ou o método de sua escolha (como salvar ou inserir)

        // Se o cliente for salvo no banco, o ID será gerado automaticamente
        // (Dependendo da implementação do DAO e do banco de dados)
    }
}
