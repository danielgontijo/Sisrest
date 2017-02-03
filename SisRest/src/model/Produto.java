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
public class Produto {
    
    private int idProduto;
    private String nomeProduto;
    private Double precoProduto;
    
    public Produto(){
    }
    public Produto(String nomeProduto,Double precoProduto){
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }
    public Produto(int idProduto,String nomeProduto,Double precoProduto){
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return this.idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /**
     * @param nomeProduto the nomeProduto to set
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return the precoProduto
     */
    public Double getPrecoProduto() {
        return this.precoProduto;
    }

    /**
     * @param precoProduto the precoProduto to set
     */
    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
     
}
