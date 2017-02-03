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
public class Cliente {
    
    private int idCliente;
    private String nomeCliente;
    private String sobreNomeCliente;
    private String cpfCliente;
    
    public Cliente(){
    }
     public Cliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }
    public Cliente(int idCliente,String nomeCliente){
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
    }
    
    public Cliente(int idCliente,String nomeCliente, String sobreNomeCliente){
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.sobreNomeCliente = sobreNomeCliente;
    }

    public Cliente(int idCliente,String nomeCliente, String sobreNomeCliente,String cpfCliente){
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.sobreNomeCliente = sobreNomeCliente;
        this.cpfCliente = cpfCliente;
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
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the sobreNomeCliente
     */
    public String getSobreNomeCliente() {
        return this.sobreNomeCliente;
    }

    /**
     * @param sobreNomeCliente the sobreNomeCliente to set
     */
    public void setSobreNomeCliente(String sobreNomeCliente) {
        this.sobreNomeCliente = sobreNomeCliente;
    }

    /**
     * @return the cpfCliente
     */
    public String getCpfCliente() {
        return this.cpfCliente;
    }

    /**
     * @param cpfCliente the cpfCliente to set
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    
}
