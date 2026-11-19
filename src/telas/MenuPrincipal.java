/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import DAO.DespesaDAO;
import com.formdev.flatlaf.FlatLightLaf;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import model.Despesa;

/**
 *
 * @author User
 */
public class MenuPrincipal extends javax.swing.JFrame {

    public MenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);

        atualizarDashboard();
        tblUltimasDespesas.setRowHeight(28);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalDespesasValor = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPoliticosCadastrados = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblMaiorGastoMes = new javax.swing.JLabel();
        btnNovaDespesaMenu = new javax.swing.JButton();
        btnNovaDespesaMenu.putClientProperty("JButton.buttonType", "accent");
        btnEditarMenu = new javax.swing.JButton();
        bntVerRelatorioMenu = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUltimasDespesas = new javax.swing.JTable();
        btnServicosMenu = new javax.swing.JButton();
        btnPoliticosCRUD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setText("Ações Rápidas");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Painel de Despesas Públicas");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel2.setText("Bem vindo ao sistema de gestão e controle de gastos");

        jPanel2.setPreferredSize(new java.awt.Dimension(600, 150));
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 15, 0));

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel3.setForeground(new java.awt.Color(0, 255, 0));
        jLabel3.setText("Total de Despesas");

        lblTotalDespesasValor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalDespesasValor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDespesasValor.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblTotalDespesasValor))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblTotalDespesasValor)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);

        jPanel5.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Políticos Cadastrados");

        lblPoliticosCadastrados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPoliticosCadastrados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPoliticosCadastrados.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lblPoliticosCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addComponent(lblPoliticosCadastrados)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);

        jPanel6.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));

        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Maior Gasto do Mês");

        lblMaiorGastoMes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMaiorGastoMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaiorGastoMes.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblMaiorGastoMes))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(lblMaiorGastoMes)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);

        btnNovaDespesaMenu.setText("Nova Despesa");
        btnNovaDespesaMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovaDespesaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaDespesaMenuActionPerformed(evt);
            }
        });

        btnEditarMenu.setText("Editar / Excluir");
        btnEditarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMenuActionPerformed(evt);
            }
        });

        bntVerRelatorioMenu.setText("Relatório");
        bntVerRelatorioMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVerRelatorioMenuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel10.setText("Últimas Despesas Lançadas");

        tblUltimasDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Político/Partido", "Valor da Despesa", "Qtd. Itens", "Descrição", "Data do Registro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUltimasDespesas);

        btnServicosMenu.setText("Serviços");
        btnServicosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicosMenuActionPerformed(evt);
            }
        });

        btnPoliticosCRUD.setText("Políticos");
        btnPoliticosCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliticosCRUDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNovaDespesaMenu)
                                .addGap(39, 39, 39)
                                .addComponent(btnEditarMenu)
                                .addGap(39, 39, 39)
                                .addComponent(bntVerRelatorioMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnPoliticosCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnServicosMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovaDespesaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntVerRelatorioMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnServicosMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPoliticosCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovaDespesaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaDespesaMenuActionPerformed
        RegistrarDespesaForm cadDespesa = new RegistrarDespesaForm();
        cadDespesa.setLocationRelativeTo(this);
        cadDespesa.setVisible(true);

        cadDespesa.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                atualizarDashboard();
            }
        });
    }//GEN-LAST:event_btnNovaDespesaMenuActionPerformed

    private void btnEditarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMenuActionPerformed
        EditarExcluirDespesa editarForm = new EditarExcluirDespesa();
        editarForm.setLocationRelativeTo(this);
        editarForm.setVisible(true);

        editarForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                atualizarDashboard();
            }
        });


    }//GEN-LAST:event_btnEditarMenuActionPerformed

    private void bntVerRelatorioMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVerRelatorioMenuActionPerformed
        RelatoriaDespesas relatorio = new RelatoriaDespesas();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }//GEN-LAST:event_bntVerRelatorioMenuActionPerformed

    private void btnServicosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicosMenuActionPerformed
        CrudServicoForm cadServico = new CrudServicoForm();
        cadServico.setLocationRelativeTo(this);
        cadServico.setVisible(true);
    }//GEN-LAST:event_btnServicosMenuActionPerformed

    private void btnPoliticosCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliticosCRUDActionPerformed
        CrudPoliticoForm cadPolitico = new CrudPoliticoForm(); // Ajuste para o nome exato do seu JFrame de Políticos
        cadPolitico.setLocationRelativeTo(this);
        cadPolitico.setVisible(true);

        cadPolitico.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                atualizarDashboard(); // Atualiza o card "Políticos Cadastrados" ao fechar
            }
        });
    }//GEN-LAST:event_btnPoliticosCRUDActionPerformed

    public void atualizarDashboard() {
        DespesaDAO dDAO = new DespesaDAO();

        NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        BigDecimal total = dDAO.getTotalDespesas();
        lblTotalDespesasValor.setText(fmtMoeda.format(total));

        int qtdPoliticos = dDAO.getQuantidadePoliticos();
        lblPoliticosCadastrados.setText(String.valueOf(qtdPoliticos));

        BigDecimal maiorGasto = dDAO.getMaiorGastoDoMes();
        lblMaiorGastoMes.setText(fmtMoeda.format(maiorGasto));

        carregarTabelaUltimasDespesas();
    }

   public void carregarTabelaUltimasDespesas() {
        DespesaDAO dDAO = new DespesaDAO();
        List<Despesa> ultimas = dDAO.getUltimasDespesas(5);

        DefaultTableModel model = (DefaultTableModel) tblUltimasDespesas.getModel();
        model.setNumRows(0);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        for (Despesa d : ultimas) {
            String dataFormatada = (d.getDataRegistro() != null) ? d.getDataRegistro().format(formatador) : "";
            String politicoTexto = (d.getPolitico() != null) ? d.getPolitico().toString() : "";
            int qtdItens = (d.getItens() != null) ? d.getItens().size() : 0;

            model.addRow(new Object[]{
                d.getId(),
                politicoTexto,
                fmtMoeda.format(d.getValor()),
                qtdItens,
                d.getDescricao(),
                dataFormatada
            });
        }
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntVerRelatorioMenu;
    private javax.swing.JButton btnEditarMenu;
    private javax.swing.JButton btnNovaDespesaMenu;
    private javax.swing.JButton btnPoliticosCRUD;
    private javax.swing.JButton btnServicosMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaiorGastoMes;
    private javax.swing.JLabel lblPoliticosCadastrados;
    private javax.swing.JLabel lblTotalDespesasValor;
    private javax.swing.JTable tblUltimasDespesas;
    // End of variables declaration//GEN-END:variables
}
