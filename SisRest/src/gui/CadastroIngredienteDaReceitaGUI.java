/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.EstoqueDAO;
import dao.IngredienteDAO;
import dao.IngredienteDaReceitaDAO;
import dao.ReceitaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Estoque;
import model.Ingrediente;
import model.IngredienteDaReceitaTableModel;
import model.IngredienteHasReceita;
import model.Receita;
import util.ValidaNumeros;

/**
 *
 * @author Daniel Lima
 */
public class CadastroIngredienteDaReceitaGUI extends javax.swing.JFrame {

    Receita receita;
    ReceitaDAO receitaDAO;
    Ingrediente ingrediente;
    IngredienteHasReceita iR;
    IngredienteDAO ingredienteDAO;
    IngredienteDaReceitaDAO ingDaReceitaDAO;
    Estoque estoque;
    EstoqueDAO estoqueDAO;
    private List<Receita> receitas = new ArrayList<>();
    private List<Receita> receitasConsulta = new ArrayList<>();
    private List<Ingrediente> ingredientes = new ArrayList<>();
    private List<Ingrediente> ingredientesDaReceita = new ArrayList<>();
    private List<IngredienteHasReceita> qtdIngredientes = new ArrayList<>();

    public CadastroIngredienteDaReceitaGUI() throws Exception {
        initComponents();
        inicializaComboReceita();
        inicializaComboIngrediente();                   
        inicializaComboReceitaConsulta();  
        txtQntd.setDocument(new ValidaNumeros());
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
        lblReceita = new javax.swing.JLabel();
        cbReceita = new javax.swing.JComboBox<>();
        lblIngrediente = new javax.swing.JLabel();
        cbIngrediente = new javax.swing.JComboBox<>();
        btnAdicionar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblQntd = new javax.swing.JLabel();
        txtQntd = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblReceitaConsulta = new javax.swing.JLabel();
        cbReceitaConsulta = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        lblIngredientes = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tabelaIngredientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Ingredientes das Receitas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));

        lblReceita.setText("Receita");

        cbReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReceitaActionPerformed(evt);
            }
        });

        lblIngrediente.setText("Ingrediente");

        cbIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIngredienteActionPerformed(evt);
            }
        });

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblQntd.setText("Quantidade");

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblQntd)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblReceita)
                                .addComponent(lblIngrediente)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtQntd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbReceita, javax.swing.GroupLayout.Alignment.LEADING, 0, 126, Short.MAX_VALUE)
                            .addComponent(cbIngrediente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReceita)
                    .addComponent(cbReceita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIngrediente)
                    .addComponent(cbIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQntd)
                    .addComponent(txtQntd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnExcluir)
                    .addComponent(btnAtualizar)
                    .addComponent(btnLimpar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta"));

        lblReceitaConsulta.setText("Receita");

        cbReceitaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReceitaConsultaActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblIngredientes.setText("Ingredientes da Receita");

        tabelaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Título 3"
            }
        ));
        IngredienteDaReceitaTableModel modelo = new IngredienteDaReceitaTableModel();
        tabelaIngredientes.setModel(modelo);
        tabelaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaIngredientesMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tabelaIngredientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblReceitaConsulta)
                        .addGap(46, 46, 46)
                        .addComponent(cbReceitaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIngredientes))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReceitaConsulta)
                    .addComponent(cbReceitaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIngredientes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReceitaActionPerformed
        defineIdsReceita();
    }//GEN-LAST:event_cbReceitaActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (cbReceita.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma receita");
        } else {

            receita = new Receita();
            ingrediente = new Ingrediente();
            
            
//        System.out.println("" + cbIngrediente.getSelectedItem().toString());
//        System.out.println("" + cbIngrediente.getSelectedIndex());

            try {
                
                defineIdsIngrediente();
                defineIdsReceita();
                ingredienteDAO = new IngredienteDAO();
                ingDaReceitaDAO = new IngredienteDaReceitaDAO();
                
                int qtdExistenteTemp = 0;
                if (cbIngrediente.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um ingrediente");
                }else{
                    
                qtdExistenteTemp = ingredienteDAO.retornaQtdIngredienteReceita(ingrediente.getIdIngrediente(),receita.getIdReceita());
                //System.out.println(qtdExistenteTemp);
                if(txtQntd.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Campo qntd invalido !!");
                }else{
                if(qtdExistenteTemp == 0){
                    ingDaReceitaDAO.manterIngredienteDaReceita(receita,ingrediente,Integer.parseInt(txtQntd.getText()));
                    JOptionPane.showMessageDialog(null, "Novo ingrediente cadastrado com sucesso !");
                    populaIngTableAdicionar();
                    cbReceitaConsulta.setSelectedItem(cbReceita.getSelectedItem());
                    }else{
                    
                ingDaReceitaDAO.atualizarQtdIngDaReceita(receita,ingrediente,Integer.parseInt(txtQntd.getText()));
                    JOptionPane.showMessageDialog(null,"Ingrediente existente qtd adicionada!");
                    
                    populaIngTableAdicionar();
                    cbReceitaConsulta.setSelectedItem(cbReceita.getSelectedItem());
                 }
                }
                
                
               }
            } catch (Exception ex) {
                Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void cbIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIngredienteActionPerformed
        defineIdsIngrediente();
    }//GEN-LAST:event_cbIngredienteActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha = tabelaIngredientes.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um ingrediente na tabela !");
        }else{

        IngredienteDaReceitaTableModel modelo = (IngredienteDaReceitaTableModel) tabelaIngredientes.getModel();
        ingrediente = new Ingrediente();
        receita = new Receita();
        defineIdsReceitaConsulta();
        
        receita.setIdReceita(receita.getIdReceita());
        
        //defineIdsIngrediente();
       // System.out.println("" + Integer.parseInt(modelo.getValueAt(linha, 0).toString()));
        ingrediente.setIdIngrediente(Integer.parseInt(modelo.getValueAt(linha, 0).toString()));
        //System.out.println("" + receita.getIdReceita());
        //receita.setIdReceita(receita.getIdReceita());
        
        try {
            ingDaReceitaDAO = new IngredienteDaReceitaDAO();
            ingDaReceitaDAO.excluirIng(ingrediente,receita);

        } catch (Exception ex) {
            Logger.getLogger(CadastroUsuarioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelo.removeRow(linha);
        JOptionPane.showMessageDialog(null, "Ingrediente excluido com sucesso");
        //limpaCampos();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         if (cbReceitaConsulta.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione alguma Receita!!");
            limparIngDaReceitaTable();
        }else{
        populaIngTable();
         }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbReceitaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReceitaConsultaActionPerformed
        defineIdsReceitaConsulta();
        //System.out.println("" + cbReceita.getSelectedItem().toString());
    }//GEN-LAST:event_cbReceitaConsultaActionPerformed

    private void tabelaIngredientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaIngredientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaIngredientesMouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            inicializaComboIngrediente();
            inicializaComboReceita();
            inicializaComboReceitaConsulta();
        } catch (Exception ex) {
            Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparIngDaReceitaTable();
        txtQntd.setText("");
    }//GEN-LAST:event_btnLimparActionPerformed
    public final void inicializaComboReceita() throws Exception {
        DefaultComboBoxModel comboReceita = new DefaultComboBoxModel();
        comboReceita.addElement("Selecione Receita");
        receitaDAO = new ReceitaDAO();
        receita = new Receita();
        receitas = receitaDAO.listaTodasReceitas(receita);
        for (Receita r : receitas) {
            comboReceita.addElement(r.getNomeReceita());
        }
        cbReceita.setModel(comboReceita);
        // cbReceita.setSelectedIndex(0);
    }

    public final void inicializaComboReceitaConsulta() throws Exception {
        DefaultComboBoxModel comboReceitaConsulta = new DefaultComboBoxModel();
        comboReceitaConsulta.addElement("Selecione Receita");
        receitaDAO = new ReceitaDAO();
        receita = new Receita();
        receitasConsulta = receitaDAO.listaTodasReceitas(receita);
        for (Receita r : receitasConsulta) {
            comboReceitaConsulta.addElement(r.getNomeReceita());
        }
        cbReceitaConsulta.setModel(comboReceitaConsulta);
        //cbReceita.setSelectedIndex(0);
    }

    public final void inicializaComboIngrediente() throws Exception {
        DefaultComboBoxModel comboIngrediente = new DefaultComboBoxModel();
        comboIngrediente.addElement("Selecione Ingrediente");
        ingredienteDAO = new IngredienteDAO();
        ingrediente = new Ingrediente();
        ingredientes = ingredienteDAO.listaTodosIngredientes(ingrediente);
        for (Ingrediente i : ingredientes) {
            comboIngrediente.addElement(i.getNomeIngrediente());
        }
        cbIngrediente.setModel(comboIngrediente);
        //cbIngrediente.setSelectedIndex(0);
    }

    private void defineIdsReceita() {
        //arruma o problema de selecionar receita
        int ind = cbReceita.getSelectedIndex();
        if (ind < 1) {
            receita = null;
            return;
        }
        ind--;
        receita = receitas.get(ind);
        //System.out.println(receita.getIdReceita());
    }

    private void defineIdsReceitaConsulta() {
        //arruma o problema de selecionar receita
        int ind = cbReceitaConsulta.getSelectedIndex();
        if (ind < 1) {
            receita = null;
            return;
        }
        ind--;
        receita = receitasConsulta.get(ind);
        //System.out.println(receita.getIdReceita());
    }

    private void defineIdsIngrediente() {

        int ind = cbIngrediente.getSelectedIndex();
        if (ind < 1) {
            ingrediente = null;
            return;
        }
        ind--;
        ingrediente = ingredientes.get(ind);
        //System.out.println(ingrediente.getIdIngrediente());
    }
    public void limparIngDaReceitaTable() {
        IngredienteDaReceitaTableModel modelo = (IngredienteDaReceitaTableModel) tabelaIngredientes.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    public void populaIngTableAdicionar(){
        
            receita = new Receita();
            ingrediente = new Ingrediente();
            iR = new IngredienteHasReceita();
            defineIdsReceita();

            limparIngDaReceitaTable();

            IngredienteDaReceitaTableModel modelo = (IngredienteDaReceitaTableModel) tabelaIngredientes.getModel();
            try {
                receitaDAO = new ReceitaDAO();
                ingDaReceitaDAO = new IngredienteDaReceitaDAO();
                //estoqueDAO = new EstoqueDAO();
                
                ingredientesDaReceita = receitaDAO.listaTodosIngredientesReceita(receita,ingrediente);
                
                qtdIngredientes = ingDaReceitaDAO.listaQntdEstoque(receita.getIdReceita());//estoqueDAO.listaQntdEstoque(receita.getIdReceita());

                if (ingredientesDaReceita.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum ingrediente cadastrado nessa receita!!");
                }
                for(int i = 0 ; i < ingredientesDaReceita.size(); i++){
                    ingrediente.setIdIngrediente(ingredientesDaReceita.get(i).getIdIngrediente());
                    ingrediente.setNomeIngrediente(ingredientesDaReceita.get(i).getNomeIngrediente());
                    iR.setReceitaIngredienteQtd(qtdIngredientes.get(i).getReceitaIngredienteQtd());
                  
                    //estoque.setQtdEstoque(estoqueIngredientes.get(i).getQtdEstoque());
                    
                    //System.out.println("" + estoque.getQtdEstoque());
                    modelo.addRow(new Ingrediente(ingrediente.getIdIngrediente(),ingrediente.getNomeIngrediente()),new IngredienteHasReceita(iR.getReceitaIngredienteQtd()));
                }
            } catch (Exception ex) {
                Logger.getLogger(CadastroReceitaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }
    public void limpaCampos(){
        txtQntd.setText("");
    }
    public void populaIngTable(){
        
            receita = new Receita();
            ingrediente = new Ingrediente();
            iR = new IngredienteHasReceita();
            defineIdsReceitaConsulta();

            limparIngDaReceitaTable();

            IngredienteDaReceitaTableModel modelo = (IngredienteDaReceitaTableModel) tabelaIngredientes.getModel();
            try {
                receitaDAO = new ReceitaDAO();
                ingDaReceitaDAO = new IngredienteDaReceitaDAO();
                //estoqueDAO = new EstoqueDAO();
                
                ingredientesDaReceita = receitaDAO.listaTodosIngredientesReceita(receita,ingrediente);
                
                qtdIngredientes = ingDaReceitaDAO.listaQntdEstoque(receita.getIdReceita());//estoqueDAO.listaQntdEstoque(receita.getIdReceita());

                if (ingredientesDaReceita.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum ingrediente cadastrado nessa receita!!");
                }
                for(int i = 0 ; i < ingredientesDaReceita.size(); i++){
                    ingrediente.setIdIngrediente(ingredientesDaReceita.get(i).getIdIngrediente());
                    ingrediente.setNomeIngrediente(ingredientesDaReceita.get(i).getNomeIngrediente());
                    iR.setReceitaIngredienteQtd(qtdIngredientes.get(i).getReceitaIngredienteQtd());
                  
                    //estoque.setQtdEstoque(estoqueIngredientes.get(i).getQtdEstoque());
                    
                    //System.out.println("" + estoque.getQtdEstoque());
                    modelo.addRow(new Ingrediente(ingrediente.getIdIngrediente(),ingrediente.getNomeIngrediente()),new IngredienteHasReceita(iR.getReceitaIngredienteQtd()));
                }
            } catch (Exception ex) {
                Logger.getLogger(CadastroReceitaGUI.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                //new CadastroIngredienteDaReceita().setVisible(true);
//                CadastroIngredienteDaReceitaGUI cadastroIngredienteDaReceita = new CadastroIngredienteDaReceitaGUI();
//                try {
//                    cadastroIngredienteDaReceita.inicializaComboReceita();
//                    cadastroIngredienteDaReceita.defineIdsReceita();
//                    cadastroIngredienteDaReceita.inicializaComboIngrediente();
//                    cadastroIngredienteDaReceita.defineIdsIngrediente();
//                    cadastroIngredienteDaReceita.inicializaComboReceitaConsulta();
//                    cadastroIngredienteDaReceita.defineIdsReceitaConsulta();
//                } catch (Exception ex) {
//                    Logger.getLogger(CadastroIngredienteDaReceitaGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                cadastroIngredienteDaReceita.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cbIngrediente;
    private javax.swing.JComboBox<String> cbReceita;
    private javax.swing.JComboBox<String> cbReceitaConsulta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblIngrediente;
    private javax.swing.JLabel lblIngredientes;
    private javax.swing.JLabel lblQntd;
    private javax.swing.JLabel lblReceita;
    private javax.swing.JLabel lblReceitaConsulta;
    private javax.swing.JTable tabelaIngredientes;
    private javax.swing.JTextField txtQntd;
    // End of variables declaration//GEN-END:variables
}