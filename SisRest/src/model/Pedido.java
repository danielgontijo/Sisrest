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
public class Pedido {
    
    private int idPedido;
    
    public Pedido(int idPedido){
    
        this.idPedido = idPedido;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return this.idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    
}
