/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Servico;
import model.dao.ServicoDAO;



/**
 *
 * @author Aluno
 */
public class TelaHistoricoNotaFiscal extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaFornecedor
     */
    public TelaHistoricoNotaFiscal() {
        initComponents();
        atualizarTabela();

        

    }
    
    
    
    
    
    private void excluirNota(int idNotaFiscal) {
    try {
        // Conectar ao banco de dados
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "");
        
        // SQL para excluir a nota fiscal pelo ID
        String sql = "DELETE FROM notas_fiscais WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idNotaFiscal);
        
        // Executar o comando de exclusão
        int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            // Exibe uma mensagem de sucesso se a exclusão for bem-sucedida
            JOptionPane.showMessageDialog(null, "Nota fiscal excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Atualiza a tabela
            atualizarTabela();
        } else {
            // Caso não tenha ocorrido nenhuma exclusão (possivelmente ID inválido)
            JOptionPane.showMessageDialog(null, "Erro ao excluir a nota fiscal.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        // Fecha a conexão com o banco de dados
        connection.close();
    } catch (SQLException e) {
        // Exibe uma mensagem de erro em caso de falha na conexão ou execução do SQL
        JOptionPane.showMessageDialog(null, "Erro ao excluir a nota fiscal: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
    
 // Método para atualizar a tabela após a exclusão
private void atualizarTabela() {
    try {
        // Conectar ao banco de dados
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "");
        
        // SQL para pegar todas as notas fiscais com o nome do cliente
        String sql = "SELECT nf.id, c.nome AS cliente_nome, nf.data_emissao, nf.valor_total " +
                     "FROM notas_fiscais nf " +
                     "JOIN clientes c ON nf.cliente_id = c.id";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        // Limpar os dados da tabela antes de adicionar os novos
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        // Preencher a tabela com as notas fiscais atualizadas
        while (rs.next()) {
            // Adaptado para 4 colunas: ID da nota fiscal, nome do cliente, data de emissão, valor total
            Object[] row = new Object[4];  
            row[0] = rs.getInt("id");           // ID da Nota Fiscal
            row[1] = rs.getString("cliente_nome"); // Nome do cliente
            row[2] = rs.getDate("data_emissao");  // Data da nota fiscal
            row[3] = rs.getDouble("valor_total");  // Total da nota fiscal
            model.addRow(row);
        }
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar a tabela: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

   
    
  
    
 

    
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonExcluir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Historico de  Notas Fiscais");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Nota Fiscal", "id Cliente", "Data EMS", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton3.setText("Visualizar Detalhes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Endereço", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Descrição", "Quantidade", "Preço_unitario", "Total", "nome", "Funcionario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(1).setResizable(false);
            jTable4.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setText("Detalhes Cliente");

        jLabel2.setText("Detalhes Serviços");

        jButtonExcluir.setText("Ecluir Nota");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(jButtonExcluir)
                    .addGap(50, 50, 50)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(210, 210, 210)
                    .addComponent(jLabel2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButtonExcluir))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                                    
    // Pega a linha selecionada na tabela de notas fiscais
    int selectedRow = jTable2.getSelectedRow();
    if (selectedRow != -1) {
        // Pega o ID da Nota Fiscal na coluna correspondente
        int idNotaFiscal = (int) jTable2.getValueAt(selectedRow, 0);  // Aqui, 0 é o índice da coluna ID da Nota Fiscal

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "")) {
            // Consulta para buscar os dados do cliente associado à nota fiscal
            String sqlCliente = "SELECT c.nome, c.endereco, c.telefone " +
                                "FROM clientes c " +
                                "JOIN notas_fiscais n ON c.id = n.cliente_id " +
                                "WHERE n.id = ?";
            try (PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, idNotaFiscal);  // Passando o ID da nota fiscal
                ResultSet rsCliente = stmtCliente.executeQuery();
                
                if (rsCliente.next()) {
                    String nomeCliente = rsCliente.getString("nome");
                    String enderecoCliente = rsCliente.getString("endereco");
                    String telefoneCliente = rsCliente.getString("telefone");

                    // Adiciona os dados do cliente na jTable3 (assumindo que jTable3 tem as colunas: Nome, Endereço, Telefone)
                    DefaultTableModel modelCliente = (DefaultTableModel) jTable3.getModel();
                    modelCliente.setRowCount(0);  // Limpar tabela de clientes
                    modelCliente.addRow(new Object[]{nomeCliente, enderecoCliente, telefoneCliente});
                }
            }

           // Consulta para buscar os serviços associados à nota fiscal
String sqlServicos = "SELECT s.descricao, nfs.quantidade, s.preco, f.nome AS nome_funcionario, s.nome AS nome_servico " +
                     "FROM servicos s " +
                     "JOIN notas_fiscais_servicos nfs ON s.id = nfs.servico_id " +
                     "JOIN funcionarios f ON nfs.funcionario_id = f.id " +
                     "WHERE nfs.nota_fiscal_id = ?";
try (PreparedStatement stmtServicos = connection.prepareStatement(sqlServicos)) {
    stmtServicos.setInt(1, idNotaFiscal);  // Passando o ID da nota fiscal
    ResultSet rsServicos = stmtServicos.executeQuery();
    
    DefaultTableModel modelServicos = (DefaultTableModel) jTable4.getModel(); // jTable4 exibe os serviços
    modelServicos.setRowCount(0);  // Limpar tabela de serviços

    while (rsServicos.next()) {
        String descricaoServico = rsServicos.getString("descricao");
        Double quantidade = rsServicos.getDouble("quantidade");
        Double preco = rsServicos.getDouble("preco");
        Double subtotal = quantidade * preco;
        String nomeServico = rsServicos.getString("nome_servico");  // Nome do serviço
        String nomeFuncionario = rsServicos.getString("nome_funcionario");

        // Adiciona as linhas na tabela com dados do serviço, funcionário e nome do serviço
        modelServicos.addRow(new Object[]{descricaoServico, quantidade, preco, subtotal, nomeServico, nomeFuncionario});
    }
}

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os detalhes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione uma nota fiscal.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

    // Verifica se a linha da tabela de notas fiscais está selecionada
    int selectedRow = jTable2.getSelectedRow();
    if (selectedRow != -1) {
        // Pega o ID da Nota Fiscal selecionada
        int idNotaFiscal = (int) jTable2.getValueAt(selectedRow, 0);  // Aqui, 0 é o índice da coluna ID da Nota Fiscal

        // Confirmação de exclusão
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta nota fiscal?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "")) {
                connection.setAutoCommit(false);  // Inicia a transação

                // Exclui os serviços associados à nota fiscal
                String sqlExcluirServicos = "DELETE FROM notas_fiscais_servicos WHERE nota_fiscal_id = ?";
                try (PreparedStatement stmtExcluirServicos = connection.prepareStatement(sqlExcluirServicos)) {
                    stmtExcluirServicos.setInt(1, idNotaFiscal);
                    stmtExcluirServicos.executeUpdate();
                }

                // Exclui a própria nota fiscal
                String sqlExcluirNotaFiscal = "DELETE FROM notas_fiscais WHERE id = ?";
                try (PreparedStatement stmtExcluirNotaFiscal = connection.prepareStatement(sqlExcluirNotaFiscal)) {
                    stmtExcluirNotaFiscal.setInt(1, idNotaFiscal);
                    stmtExcluirNotaFiscal.executeUpdate();
                }

                // Realiza o commit da transação
                connection.commit();
                JOptionPane.showMessageDialog(this, "Nota fiscal excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Atualiza a tabela de notas fiscais para refletir a exclusão
                DefaultTableModel modelNotasFiscais = (DefaultTableModel) jTable2.getModel();
                modelNotasFiscais.removeRow(selectedRow);  // Remove a linha selecionada da tabela

            } catch (SQLException e) {
                // Em caso de erro, realiza o rollback
                connection.rollback();
                JOptionPane.showMessageDialog(this, "Erro ao excluir a nota fiscal: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione uma nota fiscal para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    }//GEN-LAST:event_jButtonExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
