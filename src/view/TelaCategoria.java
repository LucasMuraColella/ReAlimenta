package view;

import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaCategoria extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCategoria.class.getName());

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int idCategoriaSelecionada = 0;

    public TelaCategoria() {
        initComponents();
        setLocationRelativeTo(null);

        carregarTabela();
    }

    private void carregarTabela() {

        DefaultTableModel modelo = (DefaultTableModel) tableCategorias.getModel();

        modelo.setRowCount(0);

        List<Categoria> lista = categoriaDAO.listar();

        for (Categoria categoria : lista) {

            modelo.addRow(new Object[]{
                categoria.getId_categoria(),
                categoria.getNome(),
                categoria.getDescricao()
            });
        }
    }

    private void limparCampos() {

        txtNome.setText("");
        txtDescricao.setText("");

        idCategoriaSelecionada = 0;

        txtNome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCategorias = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(245, 247, 245));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo.setText("GERENCIAR CATEGORIAS");

        lblNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(38, 50, 56));
        lblNome.setText("Nome:");

        lblDescricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(38, 50, 56));
        lblDescricao.setText("Descrição:");

        txtNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNome.setForeground(new java.awt.Color(38, 50, 56));
        txtNome.setToolTipText("Nome");
        txtNome.setOpaque(true);
        txtNome.addActionListener(this::txtNomeActionPerformed);

        btnCadastrar.setBackground(new java.awt.Color(76, 175, 80));
        btnCadastrar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setToolTipText("Cadastrar");
        btnCadastrar.setAutoscrolls(true);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setOpaque(true);
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnAlterar.setBackground(new java.awt.Color(33, 150, 243));
        btnAlterar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar");
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.setBorderPainted(false);
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.setFocusPainted(false);
        btnAlterar.setOpaque(true);
        btnAlterar.addActionListener(this::btnAlterarActionPerformed);

        btnExcluir.setBackground(new java.awt.Color(229, 57, 53));
        btnExcluir.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBorderPainted(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.setFocusPainted(false);
        btnExcluir.setOpaque(true);
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnLimpar.setBackground(new java.awt.Color(96, 125, 139));
        btnLimpar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar.setText("Limpar");
        btnLimpar.setToolTipText("Limpar");
        btnLimpar.setBorderPainted(false);
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.setFocusPainted(false);
        btnLimpar.setOpaque(true);
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        tableCategorias.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableCategorias.setForeground(new java.awt.Color(38, 50, 56));
        tableCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Descrição"
            }
        ));
        tableCategorias.setGridColor(new java.awt.Color(224, 224, 224));
        tableCategorias.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableCategorias.setRowHeight(28);
        tableCategorias.setSelectionBackground(new java.awt.Color(232, 245, 233));
        tableCategorias.setSelectionForeground(new java.awt.Color(38, 50, 56));
        tableCategorias.setShowGrid(true);
        tableCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCategoriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCategorias);
        if (tableCategorias.getColumnModel().getColumnCount() > 0) {
            tableCategorias.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(38, 50, 56));
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setToolTipText("Descrição");
        txtDescricao.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtDescricao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(jScrollPane3)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnCadastrar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnAlterar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnExcluir)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLimpar)
                                    .addGap(83, 83, 83)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblTitulo)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblDescricao)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrar)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos.");
            return;
        }

        if (nome.length() > 150) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome deve possuir no máximo 150 caracteres."
            );
            return;
        }

        if (descricao.length() > 255) {
            JOptionPane.showMessageDialog(
                    this,
                    "A descrição deve possuir no máximo 255 caracteres."
            );
            return;
        }

        Categoria categoria = new Categoria();

        categoria.setNome(nome);
        categoria.setDescricao(descricao);

        categoriaDAO.cadastrar(categoria);

        JOptionPane.showMessageDialog(this,
                "Categoria cadastrada com sucesso!");

        limparCampos();
        carregarTabela();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
        tableCategorias.clearSelection();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tableCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCategoriasMouseClicked

        int linha = tableCategorias.getSelectedRow();

        if (linha >= 0) {

            idCategoriaSelecionada = Integer.parseInt(
                    tableCategorias.getValueAt(linha, 0).toString()
            );

            txtNome.setText(
                    tableCategorias.getValueAt(linha, 1).toString()
            );

            txtDescricao.setText(
                    tableCategorias.getValueAt(linha, 2).toString()
            );
        }
    }//GEN-LAST:event_tableCategoriasMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (idCategoriaSelecionada == 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma categoria na tabela.");
            return;
        }

        String nome = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos.");
            return;
        }

        Categoria categoria = new Categoria();

        categoria.setId_categoria(idCategoriaSelecionada);
        categoria.setNome(nome);
        categoria.setDescricao(descricao);

        categoriaDAO.atualizar(categoria);

        JOptionPane.showMessageDialog(this,
                "Categoria alterada com sucesso!");

        limparCampos();
        idCategoriaSelecionada = 0;
        carregarTabela();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (idCategoriaSelecionada == 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma categoria na tabela.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir esta categoria?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {

            categoriaDAO.excluir(idCategoriaSelecionada);

            JOptionPane.showMessageDialog(this,
                    "Categoria excluída com sucesso!");

            limparCampos();
            idCategoriaSelecionada = 0;
            carregarTabela();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaCategoria().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableCategorias;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
