package view;

import dao.GraficoDAO;

import dao.GraficoDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class TelaGraficos extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGraficos.class.getName());
    private GraficoDAO graficoDAO
            = new GraficoDAO();

    private final DateTimeFormatter formato_data
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaGraficos() {
        initComponents();
        setLocationRelativeTo(null);

        txtDataInicial.setToolTipText("Formato: dd/MM/yyyy");
        txtDataFinal.setToolTipText("Formato: dd/MM/yyyy");
    }

    private void gerarGraficoAlimentosPorMes(LocalDate data_inicial, LocalDate data_final) {

        List<Object[]> dados
                = graficoDAO.doacoesPorMes(data_inicial, data_final);

        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (Object[] linha : dados) {

            String mes = linha[0].toString();

            int total = Integer.parseInt(
                    linha[1].toString()
            );

            dataset.addValue(
                    total,
                    "Alimentos",
                    mes
            );
        }

        JFreeChart chart
                = ChartFactory.createBarChart(
                        "Alimentos cadastrados por mês",
                        "Mês",
                        "Quantidade",
                        dataset
                );

        ChartPanel chartPanel
                = new ChartPanel(chart);

        chartPanel.setPreferredSize(
                new Dimension(800, 480)
        );

        panelGrafico.removeAll();

        panelGrafico.setLayout(
                new BorderLayout()
        );

        panelGrafico.add(
                chartPanel,
                BorderLayout.CENTER
        );
    }

    private void gerarGraficoCategorias(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> dados
                = graficoDAO.alimentosPorCategoria(
                        data_inicial,
                        data_final
                );

        DefaultPieDataset dataset
                = new DefaultPieDataset();

        for (Object[] linha : dados) {

            String categoria
                    = linha[0].toString();

            int total
                    = Integer.parseInt(
                            linha[1].toString()
                    );

            dataset.setValue(
                    categoria,
                    total
            );
        }

        JFreeChart chart
                = ChartFactory.createPieChart(
                        "Alimentos por categoria",
                        dataset,
                        true,
                        true,
                        false
                );

        org.jfree.chart.plot.PiePlot plot
                = (org.jfree.chart.plot.PiePlot) chart.getPlot();

        plot.setLabelGenerator(
                new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                        "{0} = {1}"
                )
        );

        ChartPanel chartPanel
                = new ChartPanel(chart);

        chartPanel.setPreferredSize(
                new Dimension(800, 480)
        );

        panelGrafico.removeAll();

        panelGrafico.setLayout(
                new BorderLayout()
        );

        panelGrafico.add(
                chartPanel,
                BorderLayout.CENTER
        );
    }

    private void gerarGraficoSolicitacoes(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> dados
                = graficoDAO.solicitacoesPorStatus(
                        data_inicial,
                        data_final
                );

        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (Object[] linha : dados) {

            String status
                    = linha[0].toString();

            int total
                    = Integer.parseInt(
                            linha[1].toString()
                    );

            dataset.addValue(
                    total,
                    "Solicitações",
                    status
            );
        }

        JFreeChart chart
                = ChartFactory.createBarChart(
                        "Solicitações por status",
                        "Status",
                        "Quantidade",
                        dataset
                );

        ChartPanel chartPanel
                = new ChartPanel(chart);

        chartPanel.setPreferredSize(
                new Dimension(800, 480)
        );

        panelGrafico.removeAll();

        panelGrafico.setLayout(
                new BorderLayout()
        );

        panelGrafico.add(
                chartPanel,
                BorderLayout.CENTER
        );
    }

    private void gerarGraficoStatus(
            LocalDate data_inicial,
            LocalDate data_final) {

        List<Object[]> dados
                = graficoDAO.alimentosPorStatus(
                        data_inicial,
                        data_final
                );

        DefaultPieDataset dataset
                = new DefaultPieDataset();

        for (Object[] linha : dados) {

            String status
                    = linha[0].toString();

            int total
                    = Integer.parseInt(
                            linha[1].toString()
                    );

            dataset.setValue(
                    status,
                    total
            );
        }

        JFreeChart chart
                = ChartFactory.createPieChart(
                        "Alimentos por status",
                        dataset,
                        true,
                        true,
                        false
                );

        org.jfree.chart.plot.PiePlot plot
                = (org.jfree.chart.plot.PiePlot) chart.getPlot();

        plot.setLabelGenerator(
                new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                        "{0} = {1}"
                )
        );

        ChartPanel chartPanel
                = new ChartPanel(chart);

        chartPanel.setPreferredSize(
                new Dimension(800, 480)
        );

        panelGrafico.removeAll();

        panelGrafico.setLayout(
                new BorderLayout()
        );

        panelGrafico.add(
                chartPanel,
                BorderLayout.CENTER
        );
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
        lblTipoGrafico = new javax.swing.JLabel();
        cbTipoGrafico = new javax.swing.JComboBox<>();
        btnGerar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        panelGrafico = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDataInicial = new javax.swing.JTextField();
        txtDataFinal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(245, 247, 245));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(46, 125, 50));
        lblTitulo.setText("GRÁFICOS");

        lblTipoGrafico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipoGrafico.setForeground(new java.awt.Color(38, 50, 56));
        lblTipoGrafico.setText("Tipo de Gráfico:");

        cbTipoGrafico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTipoGrafico.setForeground(new java.awt.Color(38, 50, 56));
        cbTipoGrafico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alimentos cadastrados por mês", "Alimentos por categoria", "Solicitações por status", "Alimentos por status" }));

        btnGerar.setBackground(new java.awt.Color(76, 175, 80));
        btnGerar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnGerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGerar.setText("Gerar");
        btnGerar.setToolTipText("Gerar");
        btnGerar.setBorderPainted(false);
        btnGerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGerar.setFocusPainted(false);
        btnGerar.setOpaque(true);
        btnGerar.addActionListener(this::btnGerarActionPerformed);

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

        panelGrafico.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelGraficoLayout = new javax.swing.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(96, 125, 139));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(38, 50, 56));
        jLabel2.setText("Período:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(38, 50, 56));
        jLabel3.setText("Data Inicial:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(38, 50, 56));
        jLabel4.setText("Data Final:");

        txtDataInicial.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDataInicial.setForeground(new java.awt.Color(38, 50, 56));
        txtDataInicial.setToolTipText("Data Inicial");

        txtDataFinal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDataFinal.setForeground(new java.awt.Color(38, 50, 56));
        txtDataFinal.setToolTipText("Data Final");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipoGrafico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 524, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(btnGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(191, 191, 191)
                                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(381, 381, 381))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoGrafico)
                    .addComponent(cbTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerar)
                    .addComponent(btnLimpar))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        String dataInicialTexto
                = txtDataInicial.getText().trim();

        String dataFinalTexto
                = txtDataFinal.getText().trim();

        // Verifica se as datas foram preenchidas
        if (dataInicialTexto.isEmpty()
                || dataFinalTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe a data inicial e a data final.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        LocalDate data_inicial;
        LocalDate data_final;

        try {

            data_inicial
                    = LocalDate.parse(
                            dataInicialTexto,
                            formato_data
                    );

            data_final
                    = LocalDate.parse(
                            dataFinalTexto,
                            formato_data
                    );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe as datas no formato dd/MM/yyyy.\n"
                    + "Exemplo: 01/09/2026",
                    "Data inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Verifica se a data final é menor que a inicial
        if (data_final.isBefore(data_inicial)) {

            JOptionPane.showMessageDialog(
                    this,
                    "A data final não pode ser anterior à data inicial.",
                    "Período inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String tipo
                = cbTipoGrafico
                        .getSelectedItem()
                        .toString();

        panelGrafico.removeAll();

        if (tipo.equals("Alimentos cadastrados por mês")) {

            gerarGraficoAlimentosPorMes(
                    data_inicial,
                    data_final
            );

        } else if (tipo.equals("Alimentos por categoria")) {

            gerarGraficoCategorias(
                    data_inicial,
                    data_final
            );

        } else if (tipo.equals("Solicitações por status")) {

            gerarGraficoSolicitacoes(
                    data_inicial,
                    data_final
            );

        } else if (tipo.equals("Alimentos por status")) {

            gerarGraficoStatus(
                    data_inicial,
                    data_final
            );
        }

        panelGrafico.revalidate();
        panelGrafico.repaint();

        jLabel1.setText(
                "Gráfico: " + tipo
                + " | Período: "
                + dataInicialTexto
                + " até "
                + dataFinalTexto
        );
    }//GEN-LAST:event_btnGerarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        txtDataInicial.setText("");
        txtDataFinal.setText("");

        cbTipoGrafico.setSelectedIndex(0);

        panelGrafico.removeAll();

        panelGrafico.revalidate();
        panelGrafico.repaint();

        jLabel1.setText("Nenhum gráfico gerado");
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
        java.awt.EventQueue.invokeLater(() -> new TelaGraficos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cbTipoGrafico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblTipoGrafico;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelGrafico;
    private javax.swing.JTextField txtDataFinal;
    private javax.swing.JTextField txtDataInicial;
    // End of variables declaration//GEN-END:variables
}
