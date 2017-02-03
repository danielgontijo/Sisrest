package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ReceitaComboBoxModel extends AbstractListModel implements ComboBoxModel {
  
  ArrayList<Receita> receitas;

  public ReceitaComboBoxModel(){
      this.receitas = new ArrayList<Receita>();
  }
  String selection = null;

  public void addRow(Receita r){
      this.receitas.add(r);
      this.fireContentsChanged(r, 0, 0);
  }
  public Object getElementAt(int index) {
    return receitas.get(index);
  }

  public int getSize() {
    return receitas.size();
  }
  /**
   * limpa todos os dados existentes
   */
  public void clearAll(){
      this.receitas = new ArrayList<Receita>();
  }

  /** Método que permite a seleção do item.
  * recebe o item selecionado na view e captura a String associada ao valor selecionado
  * @param Object anItem representa o objeto oriundo da seleção na view
  * 
  */
  public void setSelectedItem(Object anItem) {
      Receita r = (Receita) anItem;
      selection = Integer.toString(r.getIdReceita()); 
  } 

  // Methods implemented from the interface ComboBoxModel
  public Object getSelectedItem() {
    return selection; // to add the selection to the combo box
  }

   
}
