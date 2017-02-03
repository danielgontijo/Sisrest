/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Daniel Lima
 */
public class Estoque {
    
    private int idEstoque;
    private int qtdEstoque;
    private Date dataVencimentoEstoque;
    private String numeroLoteEstoque;
    private int idIngrediente; 
    
    public Estoque(){
    }
    public Estoque(int qtdEstoque,int idIngrediente){
      
        this.qtdEstoque = qtdEstoque;
        this.idIngrediente = idIngrediente;
    }
    public Estoque(int qtdEstoque){
      
        this.qtdEstoque = qtdEstoque;
    }
    public Estoque(int idEstoque,int qtdEstoque,Date dataVencimentoEstoque,String numeroLoteEstoque,int idIngrediente){
        this.idEstoque = idEstoque;
        this.qtdEstoque = qtdEstoque;
        this.dataVencimentoEstoque = dataVencimentoEstoque;
        this.numeroLoteEstoque = numeroLoteEstoque;
        this.idIngrediente = idIngrediente;
    }
        
    /**
     * @return the idEstoque
     */
    public int getIdEstoque() {
        return this.idEstoque;
    }

    /**
     * @param idEstoque the idEstoque to set
     */
    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    /**
     * @return the qtdEstoque
     */
    public int getQtdEstoque() {
        return this.qtdEstoque;
    }

    /**
     * @param qtdEstoque the qtdEstoque to set
     */
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    /**
     * @return the dataVencimentoEstoque
     */
    public Date getDataVencimentoEstoque() {
        return this.dataVencimentoEstoque;
    }

    /**
     * @param dataVencimentoEstoque the dataVencimentoEstoque to set
     */
    public void setDataVencimentoEstoque(Date dataVencimentoEstoque) {
        this.dataVencimentoEstoque = dataVencimentoEstoque;
    }

    /**
     * @return the numeroLoteEstoque
     */
    public String getNumeroLoteEstoque() {
        return this.numeroLoteEstoque;
    }

    /**
     * @param numeroLoteEstoque the numeroLoteEstoque to set
     */
    public void setNumeroLoteEstoque(String numeroLoteEstoque) {
        this.numeroLoteEstoque = numeroLoteEstoque;
    }

    /**
     * @return the ingredienteId
     */
    

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
