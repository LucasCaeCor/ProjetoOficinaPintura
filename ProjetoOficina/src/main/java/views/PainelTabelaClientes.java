/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;



/**
 *
 * @author Aluno
 */
public class PainelTabelaClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaFornecedor
     */
    public PainelTabelaClientes() {
        initComponents();
        preencherTabela();

    }
    
     public void preencherTabela() {
        DefaultTableModel dtm = (DefaultTableModel) clienteTable.getModel();
        dtm.setRowCount(0);
        ClienteDAO dao = new ClienteDAO();
        
            for(Cliente f : dao.read()) {
            dtm.addRow(new Object[] {
                f.getId(),
                f.getNome(),
                f.getTelefone(),
                f.getEmail(),
                f.getEndereco()
            });
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
        excluirButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clienteTable = new javax.swing.JTable();

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
        setTitle("Gerenciamento Clientes");

        excluirButton.setText("Excluir");
        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        editarButton.setText("Editar");
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        clienteTable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        clienteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone", "Email", "Endereço"
            }
        ));
        jScrollPane2.setViewportView(clienteTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(excluirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarButton)
                    .addComponent(excluirButton))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow != -1) {
            int clienteId = (int) clienteTable.getValueAt(selectedRow, 0);
            ClienteDAO dao = new ClienteDAO();
            boolean sucesso = dao.excluir(clienteId); // Chama o método modificado
            if (sucesso) {
                preencherTabela(); // Atualiza a tabela após a exclusão
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
        preencherTabela();
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow != -1) {
            int clienteId = (int) clienteTable.getValueAt(selectedRow, 0);
            String nomeAtual = (String) clienteTable.getValueAt(selectedRow, 1);
            String telefoneAtual = (String) clienteTable.getValueAt(selectedRow, 2);
            String emailAtual = (String) clienteTable.getValueAt(selectedRow, 3);
            String enderecoAtual = (String) clienteTable.getValueAt(selectedRow, 4);

            String novoNome = JOptionPane.showInputDialog(this, "Editar nome:", nomeAtual);
            String novoTelefone = JOptionPane.showInputDialog(this, "Editar telefone:", telefoneAtual);
            String novoEmail = JOptionPane.showInputDialog(this, "Editar email:", emailAtual);
            String novoEndereco = JOptionPane.showInputDialog(this, "Editar endereço:", enderecoAtual);

            if (novoNome != null && novoTelefone != null && novoEmail != null && novoEndereco != null) {
                Cliente clienteEditado = new Cliente(clienteId, novoNome, novoTelefone, novoEmail, novoEndereco);
                ClienteDAO dao = new ClienteDAO();
                boolean sucesso = dao.editar(clienteEditado); // Método para atualizar no banco
                if (sucesso) {
                    preencherTabela();
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        }
        preencherTabela();
    }//GEN-LAST:event_editarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable clienteTable;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton excluirButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
