/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author work-
 */
public class Frame_funcionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Frame_funcionario
     */
    public Frame_funcionario() {
        initComponents();
        listarValores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jText_nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jText_cargo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jText_cliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jText_terreno = new javax.swing.JTextField();
        jComboBox_status = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jText_id = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 0, -1, 673));

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 98, -1, -1));
        getContentPane().add(jText_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 95, 216, -1));

        jLabel2.setText("Cargo:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 98, -1, -1));
        getContentPane().add(jText_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 95, 252, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Funcionario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 22, 169, 37));

        jLabel4.setText("Cliente:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 146, 47, -1));
        getContentPane().add(jText_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 143, 244, -1));

        jLabel5.setText("Terreno:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 186, -1, -1));

        jLabel6.setText("Status:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 214, -1, -1));
        getContentPane().add(jText_terreno, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 183, 244, -1));

        jComboBox_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR", "ORÇAMENTO", "AGUARDANDO PAGAMENTO", "PAGO", "EM ANDAMENTO", "FINALIZADO" }));
        getContentPane().add(jComboBox_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 211, 177, -1));

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 211, -1, -1));

        jLabel7.setText("ID: ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 146, -1, -1));
        getContentPane().add(jText_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 143, 103, -1));

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Terreno", "Status"
            }
        ));
        jScrollPane2.setViewportView(tabelaCliente);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 240, 573, 315));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       ClienteDAO dao = new ClienteDAO();
       Cliente c = new Cliente();
       
       c.setName(jText_cliente.getText());
       c.setTerreno(jText_terreno.getText());
       c.setId(Integer.parseInt(jText_id.getText()));
       c.setStatus(jComboBox_status.getSelectedItem().toString());
       dao.create(c);
       listarValores();
       
       jText_cliente.setText("");
       jComboBox_status.setSelectedItem("");
       jText_id.setText("");
       jText_terreno.setText("");
       
       
 
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jText_cargo;
    private javax.swing.JTextField jText_cliente;
    private javax.swing.JTextField jText_id;
    private javax.swing.JTextField jText_nome;
    private javax.swing.JTextField jText_terreno;
    private javax.swing.JTable tabelaCliente;
    // End of variables declaration//GEN-END:variables
    
    private void listarValores(){
        try{
            ClienteDAO dao = new ClienteDAO();
            
            DefaultTableModel model = (DefaultTableModel) tabelaCliente.getModel();
            model.setNumRows(0);
            
            ArrayList<Cliente> lista = dao.read();
            
            for(int num = 0; num < lista.size(); num ++){
                model.addRow(new Object[]{
                   lista.get(num).getId(),
                   lista.get(num).getName(),
                   lista.get(num).getTerreno(),
                   lista.get(num).getStatus()
                });
            }
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Erro ao listar Valores, Frame Fun erro:"+ erro);
        }
    }
}
