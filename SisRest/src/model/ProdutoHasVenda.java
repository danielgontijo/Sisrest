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
public class ProdutoHasVenda {
 
    private int qtdPrdVenda;
    private int idProduto;
    private int idVenda;
            
    public ProdutoHasVenda(){
    
    }
    public ProdutoHasVenda(int qtdPrdVenda, int idProduto,int idVenda){
        this.qtdPrdVenda = qtdPrdVenda;
        this.idProduto = idProduto;
        this.idVenda = idVenda;
    }
    public ProdutoHasVenda(int qtdPrdVenda){
        this.qtdPrdVenda = qtdPrdVenda;
    }

    /**
     * @return the qtdPrdPed
     */
    public int getQtdPrdVenda() {
        return this.qtdPrdVenda;
    }

    /**
     * @param qtdPrdPed the qtdPrdPed to set
     */
    public void setQtdPrdVenda(int qtdPrdVenda) {
        this.qtdPrdVenda = qtdPrdVenda;
    }

    /**
     * @return the id_produto
     */
    public int getIdProduto() {
        return this.idProduto;
    }

    /**
     * @param id_produto the id_produto to set
     */
    public void setIdProduto(int id_produto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the id_venda
     */
    public int getIdVenda() {
        return this.idVenda;
    }

    /**
     * @param id_venda the id_venda to set
     */
    public void setIdVenda(int id_venda) {
        this.idVenda = idVenda;
    }
    
}
