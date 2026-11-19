/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import DAO.DespesaDAO;
import enums.Categoria;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Despesa;
import model.ItemDespesa;
import model.Politico;
import model.ServicoRecurso;
import DAO.PoliticoDAO;
import DAO.ServicoRecursoDAO;

/**
 *
 * @author aluno
 */
public class EditarExcluirDespesa extends javax.swing.JFrame {

    private Despesa despesaAtual = null;
    private final DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    
   public EditarExcluirDespesa() {
        initComponents();
        this.setLocationRelativeTo(null);
        tblItens.setRowHeight(25);
        
        carregarCombos();
        cmbServico.addActionListener(e -> preencherValorReferencia());
        preencherValorReferencia();
    }
    
    private void limparFormulario() {
        despesaAtual = null;
        txbIdConsulta.setText("");
        if (cmbPolitico.getItemCount() > 0) cmbPolitico.setSelectedIndex(0);
        if (cmbServico.getItemCount() > 0) cmbServico.setSelectedIndex(0);
        txtData.setText("");
        txbDescricaoEdit.setText("");
        txbQtd.setText("");
        preencherValorReferencia();
        atualizarTabelaItens();
    }
    
    private void preencherValorReferencia() {
        ServicoRecurso selecionado = (ServicoRecurso) cmbServico.getSelectedItem();
        if (selecionado != null && selecionado.getValorReferencia() != null) {
            txbValor.setText(selecionado.getValorReferencia().toString().replace(".", ","));
        }
    }
    
    private void carregarCombos() {
        PoliticoDAO pDAO = new PoliticoDAO();
        cmbPolitico.removeAllItems();
        for (Politico p : pDAO.getAllPoliticos()) {
            cmbPolitico.addItem(p);
        }

        ServicoRecursoDAO sDAO = new ServicoRecursoDAO();
        cmbServico.removeAllItems();
        for (ServicoRecurso s : sDAO.getAllServicos()) {
            cmbServico.addItem(s);
        }
    }

    private void selecionarPoliticoNoCombo(Politico politicoAlvo) {
        if (politicoAlvo == null) return;
        for (int i = 0; i < cmbPolitico.getItemCount(); i++) {
            Politico item = cmbPolitico.getItemAt(i);
            if (item.getId() == politicoAlvo.getId()) {
                cmbPolitico.setSelectedIndex(i);
                break;
            }
        }
    }

    private void atualizarTabelaItens() {
        DefaultTableModel model = (DefaultTableModel) tblItens.getModel();
        model.setNumRows(0);

        if (despesaAtual != null && despesaAtual.getItens() != null) {
            for (ItemDespesa item : despesaAtual.getItens()) {
                model.addRow(new Object[]{
                    item.getServicoRecurso().getDescricao(),
                    item.getServicoRecurso().getCategoria().name(),
                    item.getQuantidade(),
                    fmtMoeda.format(item.getSubtotal())
                });
            }
            lblValorTotal.setText("Valor Total: " + fmtMoeda.format(despesaAtual.getValor()));
        } else {
            lblValorTotal.setText("Valor Total: R$ 0,00");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txbIdConsulta = new javax.swing.JTextField();
        btnConsultarId = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txbDescricaoEdit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnExcluirDespesa = new javax.swing.JButton();
        btnAtualizarDespesa = new javax.swing.JButton();
        cmbPolitico = new javax.swing.JComboBox<>();
        txtData = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txbQtd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txbValor = new javax.swing.JTextField();
        cmbServico = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();
        lblValorTotal = new javax.swing.JLabel();
        btnRemoverItem1 = new javax.swing.JButton();
        btnAddItem1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jLabel1.setText("Editar e Excluir Despesa");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setText("ID da Despesa");

        btnConsultarId.setText("Consultar");
        btnConsultarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Político");

        jLabel5.setText("Descrição Geral");

        btnExcluirDespesa.setText("Excluir");
        btnExcluirDespesa.putClientProperty("FlatLaf.style", "foreground: #D32F2F; borderColor: #D32F2F;");
        btnExcluirDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDespesaActionPerformed(evt);
            }
        });

        btnAtualizarDespesa.setText("Atualizar");
        btnAtualizarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarDespesaActionPerformed(evt);
            }
        });

        jLabel7.setText("Data");

        jLabel8.setText("Qtd");

        jLabel9.setText("Serviço");

        jLabel10.setText("Valor Unit (R$)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Itens da Despesa");

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serviço", "Categoria", "Qtd", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblItens);

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorTotal.setText("ValorTotal (R$)");

        btnRemoverItem1.setText("Remover Item");
        btnRemoverItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItem1ActionPerformed(evt);
            }
        });

        btnAddItem1.setText("Adicionar Item");
        btnAddItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txbDescricaoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnConsultarId)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cmbPolitico, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cmbServico, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAddItem1)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnRemoverItem1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txbQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txbValor, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))))
                                .addGap(273, 273, 273))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblValorTotal)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExcluirDespesa)
                                    .addGap(45, 45, 45)
                                    .addComponent(btnAtualizarDespesa))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel9))
                                    .addGap(533, 533, 533))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultarId))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPolitico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txbDescricaoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txbQtd)
                    .addComponent(txbValor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarIdActionPerformed
       if (txbIdConsulta.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID da despesa para consultar.");
            return;
        }

        try {
            int idDespesa = Integer.parseInt(txbIdConsulta.getText().trim());
            DespesaDAO dDAO = new DespesaDAO();
            Despesa d = dDAO.getDespesa(idDespesa);

            if (d == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma despesa encontrada com o ID " + idDespesa);
                limparFormulario();
            } else {
                this.despesaAtual = d;
                selecionarPoliticoNoCombo(d.getPolitico());
                txbDescricaoEdit.setText(d.getDescricao());
                txtData.setText(d.getDataRegistro() != null ? d.getDataRegistro().format(fmtData) : "");
                atualizarTabelaItens();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número inteiro.");
        }
        

    }//GEN-LAST:event_btnConsultarIdActionPerformed

    private void btnAtualizarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarDespesaActionPerformed
     if (despesaAtual == null) {
            JOptionPane.showMessageDialog(this, "Consulte uma despesa primeiro.");
            return;
        }

        if (despesaAtual.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A despesa não pode ficar sem itens!");
            return;
        }

        LocalDate dataRegistro;
        try {
            dataRegistro = LocalDate.parse(txtData.getText().trim(), fmtData);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/aaaa.");
            return;
        }

        despesaAtual.setPolitico((Politico) cmbPolitico.getSelectedItem());
        despesaAtual.setDescricao(txbDescricaoEdit.getText().trim());
        despesaAtual.setDataRegistro(dataRegistro);

        DespesaDAO dDAO = new DespesaDAO();
        if (dDAO.editar(despesaAtual)) {
            JOptionPane.showMessageDialog(this, "Despesa atualizada com sucesso!");
            limparFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar despesa.");
        }
      
      
    }//GEN-LAST:event_btnAtualizarDespesaActionPerformed

    private void btnExcluirDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDespesaActionPerformed
    if (despesaAtual == null) {
            JOptionPane.showMessageDialog(this, "Consulte uma despesa primeiro.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, 
                "Deseja realmente excluir a despesa #" + despesaAtual.getId() + " e todos os seus itens?", 
                "Exclusão", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            DespesaDAO dDAO = new DespesaDAO();
            dDAO.excluir(despesaAtual.getId());

            JOptionPane.showMessageDialog(this, "Despesa excluída com sucesso!");
            limparFormulario();
        }
        
        
    }//GEN-LAST:event_btnExcluirDespesaActionPerformed

    
    private void btnRemoverItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItem1ActionPerformed
       if (despesaAtual == null) return;

        int linha = tblItens.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um item na tabela para remover.");
            return;
        }

        despesaAtual.removerItem(linha);
        atualizarTabelaItens();
    }//GEN-LAST:event_btnRemoverItem1ActionPerformed

    private void btnAddItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItem1ActionPerformed
      if (despesaAtual == null) {
            JOptionPane.showMessageDialog(this, "Consulte uma despesa primeiro antes de adicionar itens.");
            return;
        }

        ServicoRecurso servico = (ServicoRecurso) cmbServico.getSelectedItem();
        if (servico == null || txbQtd.getText().trim().isEmpty() || txbValor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione o serviço e informe quantidade e valor unitário.");
            return;
        }

        try {
            int qtd = Integer.parseInt(txbQtd.getText().trim());
            if (qtd <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.");
                return;
            }

            String valorStr = txbValor.getText().replace("R$", "").replace(".", "").replace(",", ".").trim();
            BigDecimal valorUnit = new BigDecimal(valorStr);

            despesaAtual.adicionarItem(new ItemDespesa(servico, qtd, valorUnit));
            atualizarTabelaItens();
            txbQtd.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade ou valor unitário inválido.");
        }

    }//GEN-LAST:event_btnAddItem1ActionPerformed

    /**
     * @param args the command line argumentsas
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
            java.util.logging.Logger.getLogger(EditarExcluirDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarExcluirDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarExcluirDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarExcluirDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarExcluirDespesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem1;
    private javax.swing.JButton btnAtualizarDespesa;
    private javax.swing.JButton btnConsultarId;
    private javax.swing.JButton btnExcluirDespesa;
    private javax.swing.JButton btnRemoverItem1;
    private javax.swing.JComboBox<Politico> cmbPolitico;
    private javax.swing.JComboBox<ServicoRecurso> cmbServico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tblItens;
    private javax.swing.JTextField txbDescricaoEdit;
    private javax.swing.JTextField txbIdConsulta;
    private javax.swing.JTextField txbQtd;
    private javax.swing.JTextField txbValor;
    private javax.swing.JTextField txtData;
    // End of variables declaration//GEN-END:variables
}
