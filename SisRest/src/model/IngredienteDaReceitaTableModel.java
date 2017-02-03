/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class IngredienteDaReceitaTableModel extends AbstractTableModel{
    private ArrayList<Ingrediente> dados;
    private ArrayList<IngredienteHasReceita> dadosIngredienteReceita;
    
    private String[] colunas = {"ID","Nome","Quantidade"};
    
    public IngredienteDaReceitaTableModel(){
        this.dados = new ArrayList<>();
        this.dadosIngredienteReceita = new ArrayList<>();
    }
    
    public void addRow(Ingrediente i,IngredienteHasReceita iR){
            this.dados.add(i);
            this.dadosIngredienteReceita.add(iR);
            this.fireTableDataChanged();
    }
    public void removeRow(int linha){
            this.dados.remove(linha);
            this.dadosIngredienteReceita.remove(linha);
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
            Ingrediente ingrediente = dados.get(linha);
            IngredienteHasReceita ingredienteHasReceita = dadosIngredienteReceita.get(linha);
            switch(coluna){
                      case 0: return ingrediente.getIdIngrediente();
                      case 1: return ingrediente.getNomeIngrediente(); 
                      case 2: return ingredienteHasReceita.getReceitaIngredienteQtd();
            }
            return null;
    }
    public Ingrediente get(int linha){
            return this.dados.get(linha);
    }
    public ArrayList<Ingrediente> getIngredientes(){
            return this.dados;
    }
     public ArrayList<IngredienteHasReceita> getIngredienteReceita(){
            return this.dadosIngredienteReceita;
    }
}
