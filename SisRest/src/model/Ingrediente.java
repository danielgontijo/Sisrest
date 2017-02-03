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
public class Ingrediente {
    
    private int idIngrediente;
    private String nomeIngrediente;
    private Double precoIngrediente;
    
    public Ingrediente(){
    }
    public Ingrediente(String nomeIngrediente){
        this.nomeIngrediente = nomeIngrediente;
    }
     public Ingrediente(int idIngrediente,String nomeIngrediente){
           this.idIngrediente = idIngrediente;
           this.nomeIngrediente = nomeIngrediente;
    }
    public Ingrediente(int idIngrediente,String nomeIngrediente,Double precoIngrediente){
        this.idIngrediente = idIngrediente;
        this.nomeIngrediente = nomeIngrediente;
        this.precoIngrediente = precoIngrediente;
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

    /**
     * @return the nomeIngrediente
     */
    public String getNomeIngrediente() {
        return  this.nomeIngrediente;
    }

    /**
     * @param nomeIngrediente the nomeIngrediente to set
     */
    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    /**
     * @return the precoIngrediente
     */
    public Double getPrecoIngrediente() {
        return this.precoIngrediente;
    }

    /**
     * @param precoIngrediente the precoIngrediente to set
     */
    public void setPrecoIngrediente(Double precoIngrediente) {
        this.precoIngrediente = precoIngrediente;
    }

   

}