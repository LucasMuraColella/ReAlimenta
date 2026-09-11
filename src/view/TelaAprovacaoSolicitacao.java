package view;

import dao.AdministradorDAO;
import dao.AlimentoDAO;
import dao.InstituicaoDAO;
import dao.ItemSolicitacaoDAO;
import dao.SolicitacaoDAO;

import model.Administrador;
import model.Alimento;
import model.Instituicao;
import model.ItemSolicitacao;
import model.Solicitacao;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.SenhaUtil;

public class TelaAprovacaoSolicitacao extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAprovacaoSolicitacao.class.getName());
    private SolicitacaoDAO solicitacaoDAO
            = new SolicitacaoDAO();

    private ItemSolicitacaoDAO itemSolicitacaoDAO
            = new ItemSolicitacaoDAO();

    private AlimentoDAO alimentoDAO
            = new AlimentoDAO();

    private InstituicaoDAO instituicaoDAO
            = new InstituicaoDAO();

    private AdministradorDAO administradorDAO
            = new AdministradorDAO();

    private int id_solicitacao_selecionada = 0;

    public TelaAprovacaoSolicitacao() {
        initComponents();
        setLocationRelativeTo(null);

        carregarAdministradores();
        carregarSolicitacoes();
    }

    private void carregarAdministradores() {

        cbAdministrador.removeAllItems();

        List<Administrador> lista
                = administradorDAO.listar();

        for (Administrador administrador
                : lista) {

            if (administrador.isStatus()) {

                cbAdministrador.addItem(
                        administrador.getNome()
                );
            }
        }
    }

    private Administrador
            buscarAdministradorPorNome(
                    String nome) {

        List<Administrador> lista
                = administradorDAO.listar();

        for (Administrador administrador
                : lista) {

            if (administrador.getNome()
                    .equals(nome)) {

                return administrador;
            }
        }

        return null;
    }

    private void carregarSolicitacoes() {

        DefaultTableModel modelo
                = (DefaultTableModel) tableSolicitacoes.getModel();

        modelo.setRowCount(0);

        List<Solicitacao> lista
                = solicitacaoDAO.listarPendentes();

        List<Instituicao> instituicoes
                = instituicaoDAO.listar();

        for (Solicitacao solicitacao
                : lista) {

            String nome_instituicao = "";

            for (Instituicao instituicao
                    : instituicoes) {

                if (instituicao.getId_instituicao()
                        == solicitacao
                                .getFk_instituicao()) {

                    nome_instituicao
                            = instituicao.getResponsavel();

                    break;
                }
            }

            modelo.addRow(new Object[]{
                solicitacao.getId_solicitacao(),
                nome_instituicao,
                solicitacao.getData_solicitacao(),
                solicitacao.getStatus()
            });
        }
    }

    private void carregarItens() {

        DefaultTableModel modelo
                = (DefaultTableModel) tableItens.getModel();

        modelo.setRowCount(0);

        List<ItemSolicitacao> itens
                = itemSolicitacaoDAO
                        .listarPorSolicitacao(
                                id_solicitacao_selecionada
                        );

        List<Alimento> alimentos
                = alimentoDAO.listar();

        for (ItemSolicitacao item
                : itens) {

            String nome_alimento = "";
            String unidade_medida = "";
            java.math.BigDecimal quantidade_disponivel = null;

            for (Alimento alimento
                    : alimentos) {

                if (alimento.getId_alimento()
                        == item.getFk_alimento()) {

                    nome_alimento
                            = alimento.getNome();

                    unidade_medida
                            = alimento.getUnidade_medida();

                    quantidade_disponivel
                            = alimento.getQuantidade();

                    break;
                }
            }

            modelo.addRow(new Object[]{
                nome_alimento,
                item.getQuantidade_solicitada(),
                unidade_medida,
                quantidade_disponivel
            });
        }
    }

    private void limparCampos() {

        id_solicitacao_selecionada = 0;

        tableSolicitacoes.clearSelection();

        DefaultTableModel modelo
                = (DefaultTableModel) tableItens.getModel();

        modelo.setRowCount(0);

        if (cbAdministrador.getItemCount() > 0) {

            cbAdministrador.setSelectedIndex(0);
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

        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblSubtitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSolicitacoes = new javax.swing.JTable();
        lblSubtitulo2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItens = new javax.swing.JTable();
        lblAdministrador = new javax.swing.JLabel();
        cbAdministrador = new javax.swing.JComboBox<>();
        btnAprovar = new javax.swing.JButton();
        btnRejeitar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(245, 247, 245));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo.setText("ANÁLISE DE SOLICITAÇÕES");

        lblSubtitulo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(96, 125, 139));
        lblSubtitulo.setText("SOLICITAÇÕES PENDENTES");

        tableSolicitacoes.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableSolicitacoes.setForeground(new java.awt.Color(38, 50, 56));
        tableSolicitacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Instituição", "Data", "Status"
            }
        ));
        tableSolicitacoes.setGridColor(new java.awt.Color(224, 224, 224));
        tableSolicitacoes.setInheritsPopupMenu(true);
        tableSolicitacoes.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableSolicitacoes.setRowHeight(28);
        tableSolicitacoes.setSelectionBackground(new java.awt.Color(232, 245, 233));
        tableSolicitacoes.setSelectionForeground(new java.awt.Color(38, 50, 56));
        tableSolicitacoes.setShowGrid(true);
        tableSolicitacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSolicitacoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSolicitacoes);
        if (tableSolicitacoes.getColumnModel().getColumnCount() > 0) {
            tableSolicitacoes.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        lblSubtitulo2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSubtitulo2.setForeground(new java.awt.Color(96, 125, 139));
        lblSubtitulo2.setText("ITENS DA SOLICITAÇÃO SELECIONADA");

        tableItens.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableItens.setForeground(new java.awt.Color(38, 50, 56));
        tableItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alimento", "Solicitado", "Unidade", "Disponível"
            }
        ));
        tableItens.setGridColor(new java.awt.Color(224, 224, 224));
        tableItens.setInheritsPopupMenu(true);
        tableItens.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableItens.setRowHeight(28);
        tableItens.setSelectionBackground(new java.awt.Color(232, 245, 233));
        tableItens.setSelectionForeground(new java.awt.Color(38, 50, 56));
        tableItens.setShowGrid(true);
        jScrollPane2.setViewportView(tableItens);

        lblAdministrador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAdministrador.setForeground(new java.awt.Color(38, 50, 56));
        lblAdministrador.setText("Administrador:");

        cbAdministrador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbAdministrador.setForeground(new java.awt.Color(38, 50, 56));
        cbAdministrador.setToolTipText("Administradores");

        btnAprovar.setBackground(new java.awt.Color(249, 168, 37));
        btnAprovar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAprovar.setForeground(new java.awt.Color(38, 50, 56));
        btnAprovar.setText("Aprovar");
        btnAprovar.setToolTipText("Aprovar");
        btnAprovar.setBorderPainted(false);
        btnAprovar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAprovar.setFocusPainted(false);
        btnAprovar.setOpaque(true);
        btnAprovar.addActionListener(this::btnAprovarActionPerformed);

        btnRejeitar.setBackground(new java.awt.Color(229, 57, 53));
        btnRejeitar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnRejeitar.setForeground(new java.awt.Color(255, 255, 255));
        btnRejeitar.setText("Rejeitar");
        btnRejeitar.setToolTipText("Rejeitar");
        btnRejeitar.setBorderPainted(false);
        btnRejeitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRejeitar.setFocusPainted(false);
        btnRejeitar.setOpaque(true);
        btnRejeitar.addActionListener(this::btnRejeitarActionPerformed);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSubtitulo)
                        .addComponent(lblSubtitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(cbAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAprovar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRejeitar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)
                        .addGap(147, 147, 147))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitulo)
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSubtitulo)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSubtitulo2)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblAdministrador))
                    .addComponent(cbAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAprovar)
                    .addComponent(btnRejeitar)
                    .addComponent(btnLimpar))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableSolicitacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSolicitacoesMouseClicked
        int linha
                = tableSolicitacoes.getSelectedRow();

        if (linha < 0) {
            return;
        }

        id_solicitacao_selecionada
                = Integer.parseInt(
                        tableSolicitacoes
                                .getValueAt(linha, 0)
                                .toString()
                );

        carregarItens();
    }//GEN-LAST:event_tableSolicitacoesMouseClicked

    private void btnAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprovarActionPerformed

        // 1. Verifica se uma solicitação foi selecionada
        if (id_solicitacao_selecionada == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma solicitação para aprovar.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // 2. Verifica se um administrador foi selecionado
        if (cbAdministrador.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione o administrador responsável.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // 3. Busca o administrador selecionado
        String nomeAdministrador
                = cbAdministrador
                        .getSelectedItem()
                        .toString();

        Administrador administrador
                = buscarAdministradorPorNome(
                        nomeAdministrador
                );

        if (administrador == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Administrador não encontrado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // 4. Solicita a senha
        String senha
                = JOptionPane.showInputDialog(
                        this,
                        "Digite sua senha para confirmar a aprovação:",
                        "Confirmação de segurança",
                        JOptionPane.QUESTION_MESSAGE
                );

        // Se clicar em Cancelar
        if (senha == null) {
            return;
        }

        // 5. Verifica se a senha foi preenchida
        if (senha.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite a senha.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // 6. Verifica a senha usando o hash armazenado no banco
        boolean senhaCorreta
                = SenhaUtil.verificarSenha(
                        senha,
                        administrador.getSenha()
                );

        if (!senhaCorreta) {

            JOptionPane.showMessageDialog(
                    this,
                    "Senha incorreta. A aprovação foi cancelada.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // 7. Confirma a aprovação
        int resposta
                = JOptionPane.showConfirmDialog(
                        this,
                        "Administrador: "
                        + administrador.getNome()
                        + "\n\n"
                        + "Deseja realmente aprovar esta solicitação?",
                        "Confirmar aprovação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        // 8. Se escolher NÃO, não faz nada
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        // 9. Aprova a solicitação
        boolean aprovou
                = solicitacaoDAO.aprovar(
                        id_solicitacao_selecionada,
                        administrador.getId_adm()
                );

        // 10. Resultado da aprovação
        if (aprovou) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitação aprovada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();
            carregarSolicitacoes();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível aprovar a solicitação.\n"
                    + "Verifique se existe quantidade suficiente "
                    + "dos alimentos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnAprovarActionPerformed

    private void btnRejeitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejeitarActionPerformed
        // 1. Verifica se uma solicitação foi selecionada
        if (id_solicitacao_selecionada == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma solicitação para rejeitar.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

// 2. Verifica se um administrador foi selecionado
        if (cbAdministrador.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione o administrador responsável.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

// 3. Busca o administrador selecionado
        String nomeAdministrador
                = cbAdministrador
                        .getSelectedItem()
                        .toString();

        Administrador administrador
                = buscarAdministradorPorNome(
                        nomeAdministrador
                );

        if (administrador == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Administrador não encontrado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

// 4. Solicita a senha
        String senha
                = JOptionPane.showInputDialog(
                        this,
                        "Digite sua senha para confirmar a rejeição:",
                        "Confirmação de segurança",
                        JOptionPane.QUESTION_MESSAGE
                );

// Se clicar em Cancelar
        if (senha == null) {
            return;
        }

// 5. Verifica se a senha foi preenchida
        if (senha.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite a senha.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

// 6. Verifica a senha usando o hash armazenado no banco
        boolean senhaCorreta
                = SenhaUtil.verificarSenha(
                        senha,
                        administrador.getSenha()
                );

        if (!senhaCorreta) {

            JOptionPane.showMessageDialog(
                    this,
                    "Senha incorreta. A rejeição foi cancelada.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

// 7. Confirma a rejeição
        int resposta
                = JOptionPane.showConfirmDialog(
                        this,
                        "Administrador: "
                        + administrador.getNome()
                        + "\n\n"
                        + "Deseja realmente rejeitar esta solicitação?",
                        "Confirmar rejeição",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

// 8. Se escolher NÃO, não faz nada
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

// 9. Rejeita a solicitação
        boolean rejeitou
                = solicitacaoDAO.rejeitar(
                        id_solicitacao_selecionada,
                        administrador.getId_adm()
                );

// 10. Resultado da rejeição
        if (rejeitou) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitação rejeitada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();
            carregarSolicitacoes();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível rejeitar a solicitação.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnRejeitarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

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
            java.awt.EventQueue.invokeLater(() -> new TelaAprovacaoSolicitacao().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprovar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRejeitar;
    private javax.swing.JComboBox<String> cbAdministrador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdministrador;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblSubtitulo2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableItens;
    private javax.swing.JTable tableSolicitacoes;
    // End of variables declaration//GEN-END:variables
}
