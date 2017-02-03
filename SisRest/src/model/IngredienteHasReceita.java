/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Daniel Lima
 */
public class IngredienteHasReceita {
    
    private int receitaIngredienteQtd;
    private int idIngrediente;
    
    public IngredienteHasReceita(){
    }
    

    public IngredienteHasReceita(int receitaIngredienteQtd, int idIngrediente){
        this.receitaIngredienteQtd = receitaIngredienteQtd;
        this.idIngrediente = idIngrediente;
    }
    public IngredienteHasReceita(int receitaIngredienteQtd){
        this.receitaIngredienteQtd = receitaIngredienteQtd;
       
    }

    /**
     * @return the receitaIngredienteQtd
     */
    public int getReceitaIngredienteQtd() {
        return this.receitaIngredienteQtd;
    }

    /**
     * @param receitaIngredienteQtd the receitaIngredienteQtd to set
     */
    public void setReceitaIngredienteQtd(int receitaIngredienteQtd) {
        this.receitaIngredienteQtd = receitaIngredienteQtd;
    }

    /**
     * @return the idIngrediente
     */
    public int getIdIngrediente() {
        return this.idIngrediente;
    }

    /**
     * @param idIngrediente the idIngrediente to set
     */
    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    
}
