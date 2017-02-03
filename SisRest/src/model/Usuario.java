/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Lima
 */
public class Usuario {
    
    private int idUsuario;
    private String loginUsuario;
    private String nomeUsuario;
    private String sobreNomeUsuario;
    private String sexoUsuario;
    private String nivelUsuario;
    private String senhaUsuario;
 
    /**
     * @return the idUsuario
     */
    public Usuario(){
    }
    public Usuario(int idUsuario, String nomeUsuario){
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
    }
    public Usuario(int idUsuario,String loginUsuario,String nomeUsuario,String sobreNomeUsuario,String sexoUsuario,String nivelUsuario,String senhaUsuario){
        this.idUsuario = idUsuario;
        this.loginUsuario = loginUsuario;
        this.nomeUsuario = nomeUsuario;
        this.sobreNomeUsuario = sobreNomeUsuario;
        this.sexoUsuario = sexoUsuario;
        this.nivelUsuario = nivelUsuario;
        this.senhaUsuario = senhaUsuario;
   
    
    }
    public int getIdUsuario() {
        return this.idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the sobreNomeUsuario
     */
    public String getSobreNomeUsuario() {
        return this.sobreNomeUsuario;
    }

    /**
     * @param sobreNomeUsuario the sobreNomeUsuario to set
     */
    public void setSobreNomeUsuario(String sobreNomeUsuario) {
        this.sobreNomeUsuario = sobreNomeUsuario;
    }

    /**
     * @return the sexoUsuario
     */
    public String getSexoUsuario() {
        return this.sexoUsuario;
    }

    /**
     * @param sexoUsuario the sexoUsuario to set
     */
    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    /**
     * @return the nivelUsuario
     */
    public String getNivelUsuario() {
        return this.nivelUsuario;
    }

    /**
     * @param nivelUsuario the nivelUsuario to set
     */
    public void setNivelUsuario(String nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    /**
     * @return the senhaUsuario
     */
    public String getSenhaUsuario() {
        return this.senhaUsuario;
    }

    /**
     * @param senhaUsuario the senhaUsuario to set
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    /**
     * @return the loginUsuario
     */
    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    /**
     * @param loginUsuario the loginUsuario to set
     */
    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    /**
     * @return the listUsuario
     */
   

    /**
     * @return the id
     */
    
    
}
