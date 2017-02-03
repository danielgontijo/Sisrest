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
public class Venda {
    
    private int idVendas;
    private Date dataVendas;
    private String meioPagamentoVendas;
    private String statusVendas;
    private int idCliente;
    
    public Venda(){
    }
    public Venda(int idVendas, Date dataVendas,int idCliente){
        this.idVendas = idVendas;
        this.dataVendas = dataVendas;
        this.idCliente = idCliente;
    }
    public Venda(int idVendas, Date dataVendas, String meioPagamentoVendas,String statusVendas,int idCliente){
        this.idVendas = idVendas;
        this.dataVendas = dataVendas;
        this.meioPagamentoVendas = meioPagamentoVendas;  
        this.statusVendas = statusVendas;
        this.idCliente = idCliente;
    }

     public Venda(int idVendas, Date dataVendas){
        this.idVendas = idVendas;
        this.dataVendas = dataVendas;
    }

    /**
     * @return the idVendas
     */
    public int getIdVendas() {
        return idVendas;
    }

    /**
     * @param idVendas the idVendas to set
     */
    public void setIdVendas(int idVendas) {
        this.idVendas = idVendas;
    }

    /**
     * @return the dataVendas
     */
    public Date getDataVendas() {
        return this.dataVendas;
    }

    /**
     * @param dataVendas the dataVendas to set
     */
    public void setDataVendas(Date dataVendas) {
        this.dataVendas = dataVendas;
    }

    /**
     * @return the meioPagamentoVendas
     */
    public String getMeioPagamentoVendas() {
        return this.meioPagamentoVendas;
    }

    /**
     * @param meioPagamentoVendas the meioPagamentoVendas to set
     */
    public void setMeioPagamentoVendas(String meioPagamentoVendas) {
        this.meioPagamentoVendas = meioPagamentoVendas;
    }

    /**
     * @return the statusVendas
     */
    public String getStatusVendas() {
        return this.statusVendas;
    }

    /**
     * @param statusVendas the statusVendas to set
     */
    public void setStatusVendas(String statusVendas) {
        this.statusVendas = statusVendas;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return this.idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the qntdVendas
     */

}
