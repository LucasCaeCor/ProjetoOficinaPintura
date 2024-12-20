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
public class TelaGerarNotaFiscal extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaFornecedor
     */
    public TelaGerarNotaFiscal() {
        initComponents();
        carregarServicosDisponiveis(); 
        carregarServicosSelecionados();
        

    }
    
    
    
  private void carregarServicosDisponiveis() {
    DefaultTableModel model = (DefaultTableModel) tabelaServicosDisponiveis.getModel();
    model.setRowCount(0);

    // Conexão com o banco de dados
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "")) {
        String query = "SELECT id, nome, descricao, preco, funcionarios_id FROM servicos";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getInt("funcionarios_id")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar serviços: " + e.getMessage());
    }
}
private void carregarServicosSelecionados() {
    // Obtém o modelo da tabela de serviços selecionados
    DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();

    // Limpa a tabela antes de adicionar os novos dados
    model.setRowCount(0);

    // Captura o nome do funcionário selecionado da ComboBox

   
    // Aqui, você pode substituir os dados simulados por dados reais carregados do banco
    Object[][] dadosSelecionados = {

    };

    // Adiciona cada linha de dados à tabela
    for (Object[] dados : dadosSelecionados) {
        model.addRow(dados);
    }

    // Chama a função para atualizar o valor total após a inserção dos serviços na tabela
    atualizarValorTotal();
}


    
    
 private void atualizarValorTotal() {
    double valorTotal = 0.0;

    DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();
    
    // Percorre as linhas da tabela de serviços selecionados para calcular o total
    for (int i = 0; i < model.getRowCount(); i++) {
        int quantidade = (int) model.getValueAt(i, 2); // Quantidade
        double preco = (double) model.getValueAt(i, 3); // Preço
        valorTotal += preco * quantidade;
    }

    // Atualiza o campo de valor total
    jTextField1.setText(String.format("%.2f", valorTotal));  // Formata para 2 casas decimais
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnGerar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnRemover = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaServicosSelecionados = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaServicosDisponiveis = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();

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
        setTitle("Gerenciamento NotaFiscal");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jLabel1.setText("Cliente:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "vai de bet bb" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        btnGerar.setText("Gerar Nota Fiscal");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        jLabel2.setText("Valor Total");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover Serviço");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemover)
                            .addComponent(btnGerar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnGerar)
                .addGap(18, 18, 18)
                .addComponent(btnRemover)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tabelaServicosSelecionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Quantidade", "SubTotal", "Funcionario"
            }
        ));
        jScrollPane2.setViewportView(tabelaServicosSelecionados);

        tabelaServicosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Descrição", "Preço", "Funcionario"
            }
        ));
        jScrollPane3.setViewportView(tabelaServicosDisponiveis);

        btnAdicionar.setText("Adicionar Serviço");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdicionar)
                .addGap(163, 163, 163))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
     try {
        // Conectar ao banco de dados
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "");

        // Consulta SQL para pegar os nomes dos clientes
        String sql = "SELECT id, nome FROM clientes";  // Ajuste para sua tabela e coluna
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        // Salvar o índice do cliente selecionado antes de limpar o ComboBox
        int selectedIndex = jComboBox1.getSelectedIndex(); 

        // Limpar o combobox antes de adicionar os novos itens
        jComboBox1.removeAllItems();

        // Adicionar clientes ao combobox
        while (rs.next()) {
            String nomeCliente = rs.getString("nome");  // Pegando o nome do cliente
            jComboBox1.addItem(nomeCliente);
        }

        // Restaurar a seleção do cliente (se possível)
        if (selectedIndex != -1 && selectedIndex < jComboBox1.getItemCount()) {
            jComboBox1.setSelectedIndex(selectedIndex);
        }

        // Fechar a conexão
        connection.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
  int selectedRow = tabelaServicosDisponiveis.getSelectedRow();
    if (selectedRow >= 0) {
        DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();
        Object id = tabelaServicosDisponiveis.getValueAt(selectedRow, 0);
        Object nome = tabelaServicosDisponiveis.getValueAt(selectedRow, 1);
        Object descricao = tabelaServicosDisponiveis.getValueAt(selectedRow, 2);
        Object preco = tabelaServicosDisponiveis.getValueAt(selectedRow, 3);
        Object funcionario_id = tabelaServicosDisponiveis.getValueAt(selectedRow, 4);
        model.addRow(new Object[]{id, nome, 1, preco,funcionario_id});  // Default quantity is 1
        atualizarValorTotal();
    }

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
int selectedRow = tabelaServicosSelecionados.getSelectedRow();
    if (selectedRow >= 0) {
        DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();
        model.removeRow(selectedRow);
        atualizarValorTotal();
    }

    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
    // Obtém o modelo da tabela de serviços selecionados
    DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();
    double total = 0;

    // Verifica se há serviços selecionados
    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Por favor, adicione ao menos um serviço.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return; // Impede a continuação se não houver serviços
    }

    // Captura o valor total somado
    for (int i = 0; i < model.getRowCount(); i++) {
        total += (double) model.getValueAt(i, 3);  // Soma os subtotais
    }

    // Captura o cliente selecionado
    String nomeCliente = (String) jComboBox1.getSelectedItem();
    if (nomeCliente == null || nomeCliente.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return; // Impede a continuação se não houver cliente
    }

    int clienteId = -1; // ID do cliente a ser encontrado

    // Tenta realizar a conexão e inserção no banco de dados
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina", "root", "")) {
        // Desabilita o autocommit para controlar a transação manualmente
        connection.setAutoCommit(false);

        // Consulta para obter o ID do cliente
        String sqlCliente = "SELECT id FROM clientes WHERE nome = ?";
        try (PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)) {
            stmtCliente.setString(1, nomeCliente);
            ResultSet rsCliente = stmtCliente.executeQuery();

            if (rsCliente.next()) {
                clienteId = rsCliente.getInt("id");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Insere a nota fiscal
        String sqlNotaFiscal = "INSERT INTO notas_fiscais (cliente_id, valor_total, data_emissao) VALUES (?, ?, ?)";
        try (PreparedStatement stmtNotaFiscal = connection.prepareStatement(sqlNotaFiscal, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtNotaFiscal.setInt(1, clienteId);  
            stmtNotaFiscal.setDouble(2, total);  
            stmtNotaFiscal.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            stmtNotaFiscal.executeUpdate();

            ResultSet rs = stmtNotaFiscal.getGeneratedKeys();
            if (rs.next()) {
                int notaFiscalId = rs.getInt(1);

                // Insere os serviços associados à nota fiscal
                String sqlServico = "INSERT INTO notas_fiscais_servicos (nota_fiscal_id, servico_id, funcionario_id, quantidade, subtotal) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmtServico = connection.prepareStatement(sqlServico)) {
                    // Itera sobre os serviços selecionados na tabela
                    for (int i = 0; i < model.getRowCount(); i++) {
                        int idServico = (int) model.getValueAt(i, 0);  

                        // Verifica se o valor do funcionário não é null
                        Integer funcionarioId = (Integer) model.getValueAt(i, 4);  
                        if (funcionarioId == null) {
                            JOptionPane.showMessageDialog(this, "Funcionário não selecionado para o serviço na linha " + (i + 1), "Erro", JOptionPane.ERROR_MESSAGE);
                            return; // Impede a continuação caso o funcionário seja null
                        }
                        
                        int quantidade = (int) model.getValueAt(i, 2);  
                        double subtotal = (double) model.getValueAt(i, 3);  

                        stmtServico.setInt(1, notaFiscalId);
                        stmtServico.setInt(2, idServico);  
                        stmtServico.setInt(3, funcionarioId);  // Agora incluímos o ID do funcionário
                        stmtServico.setInt(4, quantidade);  
                        stmtServico.setDouble(5, subtotal);  
                        stmtServico.executeUpdate();
                    }
                }

                // Commit da transação
                connection.commit();
                JOptionPane.showMessageDialog(this, "Nota Fiscal gerada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                // Limpa a tabela de serviços selecionados e valor total
                model.setRowCount(0);  
                jTextField1.setText("R$ 0,00");  
            }
        } catch (SQLException e) {
            // Em caso de erro, realiza o rollback
            connection.rollback();
            JOptionPane.showMessageDialog(this, "Erro ao gerar a nota fiscal: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro de conexão com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }



    }//GEN-LAST:event_btnGerarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    // Atualiza o valor total ao calcular os subtotais
    DefaultTableModel model = (DefaultTableModel) tabelaServicosSelecionados.getModel();
    double total = 0;

    // Somando os subtotais
    for (int i = 0; i < model.getRowCount(); i++) {
        total += (double) model.getValueAt(i, 3);  // Somando os subtotais
    }

    // Atualiza o campo de valor total com o formato "R$"
    jTextField1.setText("R$ " + String.format("%.2f", total));  // Formata o valor para duas casas decimais

    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabelaServicosDisponiveis;
    private javax.swing.JTable tabelaServicosSelecionados;
    // End of variables declaration//GEN-END:variables
}
