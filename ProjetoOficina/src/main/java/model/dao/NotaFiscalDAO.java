package model.dao;

import model.bean.NotaFiscal;
import model.bean.Servico;
import conexao.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.bean.Cliente;

public class NotaFiscalDAO {

    // Método para criar uma nota fiscal
    public void criarNotaFiscal(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO nota_fiscal (cliente_id, data_emissao, valor_total) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, notaFiscal.getCliente().getId());
            stmt.setDate(2, Date.valueOf(notaFiscal.getDataEmissao()));
            stmt.setDouble(3, notaFiscal.getValorTotal());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                notaFiscal.setId(rs.getInt(1)); // Definindo o ID gerado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um serviço a uma nota fiscal
    public void adicionarServicoNaNotaFiscal(int notaFiscalId, int servicoId, int quantidade, double subtotal) {
        String sql = "INSERT INTO nota_fiscal_servicos (nota_fiscal_id, servico_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, notaFiscalId);
            stmt.setInt(2, servicoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, subtotal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar notas fiscais com seus respectivos serviços
    public ArrayList<NotaFiscal> listarNotasComServicos() {
        String sql = "SELECT nf.id AS nota_id, nf.data_emissao, nf.valor_total, " +
                     "c.id AS cliente_id, c.nome AS cliente_nome, c.telefone AS cliente_telefone, c.email AS cliente_email, c.endereco AS cliente_endereco, " +
                     "s.id AS servico_id, s.nome AS servico_nome, s.descricao AS servico_descricao, nfs.quantidade, nfs.subtotal " +
                     "FROM nota_fiscal nf " +
                     "INNER JOIN cliente c ON nf.cliente_id = c.id " +
                     "LEFT JOIN nota_fiscal_servicos nfs ON nf.id = nfs.nota_fiscal_id " +
                     "LEFT JOIN servico s ON nfs.servico_id = s.id";
        
        ArrayList<NotaFiscal> notasFiscais = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            NotaFiscal notaAtual = null;
            while (rs.next()) {
                int notaId = rs.getInt("nota_id");

                // Se uma nova nota for encontrada
                if (notaAtual == null || notaAtual.getId() != notaId) {
                    // Criar cliente
                    Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("cliente_nome"),
                        rs.getString("cliente_telefone"),
                        rs.getString("cliente_email"),
                        rs.getString("cliente_endereco")
                    );

                    // Criar nota fiscal
                    notaAtual = new NotaFiscal(
                        rs.getInt("nota_id"),
                        cliente,
                        rs.getDate("data_emissao").toLocalDate(),
                        rs.getDouble("valor_total")
                    );

                    notasFiscais.add(notaAtual);
                }

                // Adicionar serviços à nota atual (se existirem)
                int servicoId = rs.getInt("servico_id");
                if (servicoId > 0) {
                    Servico servico = new Servico(
                        rs.getInt("servico_id"),
                        rs.getString("servico_nome"),
                        rs.getString("servico_descricao"),
                        rs.getDouble("subtotal") / rs.getInt("quantidade") // Calcula o preço unitário
                    );

                    notaAtual.getServicos().add(servico); // Supondo que NotaFiscal tenha um atributo `servicos` (ArrayList)
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notasFiscais;
    }

    // Método para listar serviços de uma nota fiscal específica
    public ArrayList<Servico> listarServicosPorNota(int notaFiscalId) {
        String sql = "SELECT s.id, s.nome, s.descricao, s.preco, nfs.quantidade, nfs.subtotal " +
                     "FROM nota_fiscal_servicos nfs " +
                     "INNER JOIN servico s ON nfs.servico_id = s.id " +
                     "WHERE nfs.nota_fiscal_id = ?";
        
        ArrayList<Servico> servicos = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notaFiscalId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Servico servico = new Servico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                    );
                    servicos.add(servico);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }
}
