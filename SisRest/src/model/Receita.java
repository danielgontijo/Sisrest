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
public class Receita {
        
      private int idReceita;
      private String nomeReceita;
      
      public Receita(){
      }
      public Receita(int idReceita){
          this.idReceita = idReceita;
      }
        public Receita(String nomeReceita){
            this.nomeReceita = nomeReceita;
      }
      
      public Receita(int idReceita,String nomeReceita){
          this.idReceita = idReceita;
          this.nomeReceita = nomeReceita;
      }

    /**
     * @return the idReceita
     */
    public int getIdReceita() {
        return this.idReceita;
    }

    /**
     * @param idReceita the idReceita to set
     */
    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    /**
     * @return the nomeReceita
     */
    public String getNomeReceita() {
        return this.nomeReceita;
    }

    /**
     * @param nomeReceita the nomeReceita to set
     */
    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }


}
