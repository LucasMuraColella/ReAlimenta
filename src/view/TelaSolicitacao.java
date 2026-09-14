package view;

import dao.AlimentoDAO;
import dao.InstituicaoDAO;
import dao.ItemSolicitacaoDAO;
import dao.SolicitacaoDAO;

import model.Alimento;
import model.Instituicao;
import model.ItemSolicitacao;
import model.Solicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaSolicitacao extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaSolicitacao.class.getName());
    private SolicitacaoDAO solicitacaoDAO
            = new SolicitacaoDAO();

    private ItemSolicitacaoDAO itemSolicitacaoDAO
            = new ItemSolicitacaoDAO();

    private InstituicaoDAO instituicaoDAO
            = new InstituicaoDAO();

    private AlimentoDAO alimentoDAO
            = new AlimentoDAO();

    private List<ItemSolicitacao> itens_temporarios
            = new ArrayList<>();

    private int id_solicitacao_selecionada = 0;

    public TelaSolicitacao() {
        initComponents();
        setLocationRelativeTo(null);

        carregarInstituicoes();
        carregarAlimentos();
        carregarSolicitacoes();
    }

    private void carregarInstituicoes() {

        cbInstituicao.removeAllItems();

        List<Instituicao> lista
                = instituicaoDAO.listar();

        for (Instituicao instituicao : lista) {

            cbInstituicao.addItem(
                    instituicao.getNome()
            );
        }
    }

    private void carregarAlimentos() {

        cbAlimento.removeAllItems();

        List<Alimento> lista
                = alimentoDAO.listar();

        for (Alimento alimento : lista) {

            if ("DISPONIVEL".equals(
                    alimento.getStatus())) {

                cbAlimento.addItem(
                        alimento.getNome()
                );
            }
        }
    }

    private Instituicao buscarInstituicaoPorNome(
            String nome) {

        List<Instituicao> lista
                = instituicaoDAO.listar();

        for (Instituicao instituicao : lista) {

            if (instituicao.getNome()
                    .equals(nome)) {

                return instituicao;
            }
        }

        return null;
    }

    private Alimento buscarAlimentoPorNome(
            String nome) {

        List<Alimento> lista
                = alimentoDAO.listar();

        for (Alimento alimento : lista) {

            if (alimento.getNome()
                    .equals(nome)) {

                return alimento;
            }
        }

        return null;
    }

    private void carregarSolicitacoes() {

        DefaultTableModel modelo
                = (DefaultTableModel) tableSolicitacoes.getModel();

        modelo.setRowCount(0);

        List<Solicitacao> lista
                = solicitacaoDAO.listar();

        List<Instituicao> instituicoes
                = instituicaoDAO.listar();

        for (Solicitacao solicitacao : lista) {

            String nome_instituicao = "";

            for (Instituicao instituicao
                    : instituicoes) {

                if (instituicao.getId_instituicao()
                        == solicitacao
                                .getFk_instituicao()) {

                    nome_instituicao
                            = instituicao.getNome();

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

    private void carregarItensDaSolicitacao(
            int fk_solicitacao) {

        itens_temporarios.clear();

        DefaultTableModel modelo
                = (DefaultTableModel) tableItens.getModel();

        modelo.setRowCount(0);

        List<ItemSolicitacao> itens
                = itemSolicitacaoDAO
                        .listarPorSolicitacao(
                                fk_solicitacao
                        );

        List<Alimento> alimentos
                = alimentoDAO.listar();

        for (ItemSolicitacao item
                : itens) {

            itens_temporarios.add(item);

            String nome_alimento = "";
            String unidade_medida = "";

            for (Alimento alimento
                    : alimentos) {

                if (alimento.getId_alimento()
                        == item.getFk_alimento()) {

                    nome_alimento
                            = alimento.getNome();

                    unidade_medida
                            = alimento.getUnidade_medida();

                    break;
                }
            }

            modelo.addRow(new Object[]{
                nome_alimento,
                item.getQuantidade_solicitada(),
                unidade_medida
            });
        }
    }

    private void limparCampos() {

        txtObservacao.setText("");
        txtQuantidade.setText("");

        id_solicitacao_selecionada = 0;

        itens_temporarios.clear();

        DefaultTableModel modelo
                = (DefaultTableModel) tableItens.getModel();

        modelo.setRowCount(0);

        tableSolicitacoes.clearSelection();

        if (cbInstituicao.getItemCount() > 0) {
            cbInstituicao.setSelectedIndex(0);
        }

        if (cbAlimento.getItemCount() > 0) {
            cbAlimento.setSelectedIndex(0);
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
        lblInstituicao = new javax.swing.JLabel();
        lblObservacao = new javax.swing.JLabel();
        cbInstituicao = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        lblAlimento = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        cbAlimento = new javax.swing.JComboBox<>();
        btnAdicionarItem = new javax.swing.JButton();
        btnRemoverItem = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lblTitulo2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItens = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        lblTitulo3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSolicitacoes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(245, 247, 245));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo.setText("GERENCIAR SOLICITAÇÕES");

        lblInstituicao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblInstituicao.setForeground(new java.awt.Color(38, 50, 56));
        lblInstituicao.setText("Instituição:");

        lblObservacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblObservacao.setForeground(new java.awt.Color(38, 50, 56));
        lblObservacao.setText("Observação:");

        cbInstituicao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbInstituicao.setForeground(new java.awt.Color(38, 50, 56));
        cbInstituicao.setToolTipText("Instituições");

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtObservacao.setForeground(new java.awt.Color(38, 50, 56));
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(5);
        txtObservacao.setToolTipText("Observação");
        txtObservacao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservacao);

        lblAlimento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAlimento.setForeground(new java.awt.Color(38, 50, 56));
        lblAlimento.setText("Alimento:");

        lblQuantidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblQuantidade.setForeground(new java.awt.Color(38, 50, 56));
        lblQuantidade.setText("Quantidade:");

        txtQuantidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuantidade.setForeground(new java.awt.Color(38, 50, 56));
        txtQuantidade.setToolTipText("Quantidade");

        cbAlimento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbAlimento.setForeground(new java.awt.Color(38, 50, 56));
        cbAlimento.setToolTipText("Alimentos");

        btnAdicionarItem.setBackground(new java.awt.Color(76, 175, 80));
        btnAdicionarItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAdicionarItem.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarItem.setText("Adicionar Item");
        btnAdicionarItem.setToolTipText("Adicionar Item");
        btnAdicionarItem.setBorderPainted(false);
        btnAdicionarItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarItem.setFocusPainted(false);
        btnAdicionarItem.setOpaque(true);
        btnAdicionarItem.addActionListener(this::btnAdicionarItemActionPerformed);

        btnRemoverItem.setBackground(new java.awt.Color(33, 150, 243));
        btnRemoverItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnRemoverItem.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverItem.setText("Remover Item");
        btnRemoverItem.setToolTipText("Remover Item");
        btnRemoverItem.setBorderPainted(false);
        btnRemoverItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoverItem.setFocusPainted(false);
        btnRemoverItem.setOpaque(true);
        btnRemoverItem.addActionListener(this::btnRemoverItemActionPerformed);

        lblTitulo2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo2.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo2.setText("ITENS DA SOLICITAÇÃO");

        tableItens.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableItens.setForeground(new java.awt.Color(38, 50, 56));
        tableItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alimento", "Quantidade", "Unidade"
            }
        ));
        tableItens.setGridColor(new java.awt.Color(224, 224, 224));
        tableItens.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableItens.setRowHeight(28);
        tableItens.setSelectionBackground(new java.awt.Color(232, 245, 233));
        tableItens.setSelectionForeground(new java.awt.Color(38, 50, 56));
        tableItens.setShowGrid(true);
        jScrollPane2.setViewportView(tableItens);

        btnCadastrar.setBackground(new java.awt.Color(76, 175, 80));
        btnCadastrar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setToolTipText("Cadastrar");
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

        lblTitulo3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo3.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo3.setText("SOLICITAÇÕES CADASTRADAS");

        tableSolicitacoes.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableSolicitacoes.setForeground(new java.awt.Color(38, 50, 56));
        tableSolicitacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Instituição", "Data", "Status"
            }
        ));
        tableSolicitacoes.setGridColor(new java.awt.Color(224, 224, 224));
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
        jScrollPane3.setViewportView(tableSolicitacoes);
        if (tableSolicitacoes.getColumnModel().getColumnCount() > 0) {
            tableSolicitacoes.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAdicionarItem)
                                    .addGap(52, 52, 52)
                                    .addComponent(btnRemoverItem))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnCadastrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterar)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(lblTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblObservacao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidade)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlimento)
                    .addComponent(cbAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarItem)
                    .addComponent(btnRemoverItem))
                .addGap(22, 22, 22)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        if (cbAlimento.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um alimento."
            );

            return;
        }

        String quantidade_texto
                = txtQuantidade.getText().trim();

        if (quantidade_texto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe a quantidade."
            );

            return;
        }

        try {

            BigDecimal quantidade
                    = new BigDecimal(
                            quantidade_texto
                                    .replace(",", ".")
                    );

            if (quantidade.compareTo(
                    BigDecimal.ZERO) <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "A quantidade deve ser maior que zero."
                );

                return;
            }

            Alimento alimento
                    = buscarAlimentoPorNome(
                            cbAlimento
                                    .getSelectedItem()
                                    .toString()
                    );

            if (alimento == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Alimento não encontrado."
                );

                return;
            }

            if (quantidade.compareTo(
                    alimento.getQuantidade()
            ) > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "A quantidade solicitada é maior "
                        + "que a quantidade disponível."
                );

                return;
            }

            ItemSolicitacao item
                    = new ItemSolicitacao();

            item.setQuantidade_solicitada(
                    quantidade
            );

            item.setFk_alimento(
                    alimento.getId_alimento()
            );

            itens_temporarios.add(item);

            DefaultTableModel modelo
                    = (DefaultTableModel) tableItens.getModel();

            modelo.addRow(new Object[]{
                alimento.getNome(),
                quantidade,
                alimento.getUnidade_medida()
            });

            txtQuantidade.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma quantidade válida."
            );
        }
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        int linha
                = tableItens.getSelectedRow();

        if (linha < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um item da solicitação."
            );

            return;
        }

        itens_temporarios.remove(linha);

        DefaultTableModel modelo
                = (DefaultTableModel) tableItens.getModel();

        modelo.removeRow(linha);
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if (cbInstituicao.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma instituição."
            );

            return;
        }

        if (itens_temporarios.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Adicione pelo menos um alimento."
            );

            return;
        }

        String nome_instituicao
                = cbInstituicao
                        .getSelectedItem()
                        .toString();

        Instituicao instituicao
                = buscarInstituicaoPorNome(
                        nome_instituicao
                );

        if (instituicao == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Instituição não encontrada."
            );

            return;
        }

        Solicitacao solicitacao
                = new Solicitacao();

        solicitacao.setData_solicitacao(
                LocalDate.now()
        );

        solicitacao.setObservacao(
                txtObservacao.getText().trim()
        );

        solicitacao.setStatus("PENDENTE");

        solicitacao.setFk_instituicao(
                instituicao.getId_instituicao()
        );

        solicitacao.setFk_administrador(null);

        int id_solicitacao
                = solicitacaoDAO.cadastrar(
                        solicitacao
                );

        if (id_solicitacao == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao cadastrar a solicitação."
            );

            return;
        }

        for (ItemSolicitacao item
                : itens_temporarios) {

            item.setFk_solicitacao(
                    id_solicitacao
            );

            itemSolicitacaoDAO.cadastrar(
                    item
            );
        }

        JOptionPane.showMessageDialog(
                this,
                "Solicitação cadastrada com sucesso!"
        );

        limparCampos();
        carregarSolicitacoes();
    }//GEN-LAST:event_btnCadastrarActionPerformed

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

        Solicitacao solicitacao
                = solicitacaoDAO.buscarPorId(
                        id_solicitacao_selecionada
                );

        if (solicitacao == null) {
            return;
        }

        List<Instituicao> instituicoes
                = instituicaoDAO.listar();

        for (Instituicao instituicao
                : instituicoes) {

            if (instituicao.getId_instituicao()
                    == solicitacao
                            .getFk_instituicao()) {

                cbInstituicao.setSelectedItem(
                        instituicao.getNome()
                );

                break;
            }
        }

        txtObservacao.setText(
                solicitacao.getObservacao()
        );

        carregarItensDaSolicitacao(
                id_solicitacao_selecionada
        );
    }//GEN-LAST:event_tableSolicitacoesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (id_solicitacao_selecionada == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma solicitação."
            );

            return;
        }

        Solicitacao solicitacao
                = solicitacaoDAO.buscarPorId(
                        id_solicitacao_selecionada
                );

        if (solicitacao == null) {
            return;
        }

        if (!"PENDENTE".equals(
                solicitacao.getStatus())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Somente solicitações pendentes "
                    + "podem ser alteradas."
            );

            return;
        }

        if (itens_temporarios.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "A solicitação precisa possuir "
                    + "pelo menos um alimento."
            );

            return;
        }

        Instituicao instituicao
                = buscarInstituicaoPorNome(
                        cbInstituicao
                                .getSelectedItem()
                                .toString()
                );

        if (instituicao == null) {
            return;
        }

        solicitacao.setFk_instituicao(
                instituicao.getId_instituicao()
        );

        solicitacao.setObservacao(
                txtObservacao.getText().trim()
        );

        boolean atualizou
                = solicitacaoDAO.atualizar(
                        solicitacao
                );

        if (!atualizou) {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível alterar "
                    + "a solicitação."
            );

            return;
        }

        itemSolicitacaoDAO.excluirPorSolicitacao(
                id_solicitacao_selecionada
        );

        for (ItemSolicitacao item
                : itens_temporarios) {

            item.setFk_solicitacao(
                    id_solicitacao_selecionada
            );

            itemSolicitacaoDAO.cadastrar(
                    item
            );
        }

        JOptionPane.showMessageDialog(
                this,
                "Solicitação alterada com sucesso!"
        );

        limparCampos();
        carregarSolicitacoes();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (id_solicitacao_selecionada == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma solicitação."
            );

            return;
        }

        Solicitacao solicitacao
                = solicitacaoDAO.buscarPorId(
                        id_solicitacao_selecionada
                );

        if (solicitacao == null) {
            return;
        }

        if (!"PENDENTE".equals(
                solicitacao.getStatus())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Somente solicitações pendentes "
                    + "podem ser canceladas."
            );

            return;
        }

        int resposta
                = JOptionPane.showConfirmDialog(
                        this,
                        "Deseja cancelar esta solicitação?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

        if (resposta == JOptionPane.YES_OPTION) {

            boolean cancelou
                    = solicitacaoDAO.cancelar(
                            id_solicitacao_selecionada
                    );

            if (cancelou) {

                JOptionPane.showMessageDialog(
                        this,
                        "Solicitação cancelada com sucesso!"
                );

                limparCampos();
                carregarSolicitacoes();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível cancelar "
                        + "a solicitação."
                );
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaSolicitacao().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JComboBox<String> cbAlimento;
    private javax.swing.JComboBox<String> cbInstituicao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAlimento;
    private javax.swing.JLabel lblInstituicao;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JTable tableItens;
    private javax.swing.JTable tableSolicitacoes;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
