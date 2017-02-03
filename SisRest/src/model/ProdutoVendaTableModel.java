/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoVendaTableModel extends AbstractTableModel{
    
    private ArrayList<Produto> dados;
    private ArrayList<ProdutoHasVenda> dadosVenda;
    private String[] colunas = {"ID","Nome","Preço","Qntd"};
    
    public ProdutoVendaTableModel(){
        this.dados = new ArrayList<>();
        this.dadosVenda = new ArrayList<>();
    }
    
    public void addRow(Produto p,ProdutoHasVenda pV){
            this.dados.add(p);
            this.dadosVenda.add(pV);
            this.fireTableDataChanged();
    }
   
    public void removeRow(int linha){
            this.dados.remove(linha);
            this.dadosVenda.remove(linha);
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
            Produto prod = dados.get(linha);
            ProdutoHasVenda pV = dadosVenda.get(linha);
            switch(coluna){
//                    case 0: return dados.get(linha).getIdUsuario();
//                    case 1: return dados.get(linha).getNomeUsuario();
                      case 0: return prod.getIdProduto();
                      case 1: return prod.getNomeProduto();
                      case 2: return prod.getPrecoProduto();
                      case 3: return pV.getQtdPrdVenda();
            }
            return null;
    }
    public Produto get(int linha){
            return this.dados.get(linha);
    }
    public ArrayList<Produto> getProdutos(){
            return this.dados;
    }
    public ArrayList<ProdutoHasVenda> getProdutosVenda(){
            return this.dadosVenda;
    }
    
}
