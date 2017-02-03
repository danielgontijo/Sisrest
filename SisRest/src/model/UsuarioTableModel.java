/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel{
    private ArrayList<Usuario> dados;
    private String[] colunas = {"ID","Nome"};
    public UsuarioTableModel(){
        this.dados = new ArrayList<>();
    }
    
    public void addRow(Usuario u){
            this.dados.add(u);
            this.fireTableDataChanged();
    }
    public void removeRow(int linha){
            this.dados.remove(linha);
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
            Usuario usu = dados.get(linha);
            switch(coluna){
//                    case 0: return dados.get(linha).getIdUsuario();
//                    case 1: return dados.get(linha).getNomeUsuario();
                      case 0: return usu.getIdUsuario();
                      case 1: return usu.getNomeUsuario();
            }
            return null;
    }
    public Usuario get(int linha){
            return this.dados.get(linha);
    }
    public ArrayList<Usuario> getUsuarios(){
            return this.dados;
    }
    
}
