/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VendasPeriodoTableModel extends AbstractTableModel{
    
    private ArrayList<Venda> dados;
    private ArrayList<Cliente> dadosCliente;
    private ArrayList<ProdutoHasVenda> dadosProdutoVenda;
    private String[] colunas = {"IdVenda","Nome Cliente","Data Venda"};
    
    public VendasPeriodoTableModel(){
        this.dados = new ArrayList<>();
        this.dadosCliente = new ArrayList<>();
    }
    
    public void addRow(Venda v,Cliente c){
            this.dados.add(v);
            this.dadosCliente.add(c);
            this.fireTableDataChanged();
    }
    public void removeRow(int linha){
            this.dados.remove(linha);
            this.dadosCliente.remove(linha);
            this.fireTableRowsDeleted(linha,linha);
    }
    @Override
    public String getColumnName(int num){
            return this.colunas[num];
    }
    @Override
    public int getRowCount(){
            return this.dados.size();
    }
    @Override
    public int getColumnCount(){
            return this.colunas.length;
    }
    @Override
    public Object getValueAt(int linha, int coluna){
            Venda vendas = dados.get(linha);
            Cliente cli = dadosCliente.get(linha);
            
            switch(coluna){
//                    case 0: return dados.get(linha).getIdUsuario();
//                    case 1: return dados.get(linha).getNomeUsuario();
                      case 0: return vendas.getIdVendas();
                      case 1: return cli.getNomeCliente();
                      case 2: return vendas.getDataVendas();
            }
            return null;
    }
    public Venda get(int linha){
            return this.dados.get(linha);
    }
    public ArrayList<Venda> getVendas(){
            return this.dados;
    }
    
}
