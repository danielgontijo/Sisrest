/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import static com.sun.glass.ui.Cursor.setVisible;
import dao.UsuarioDAO;
import gui.LoginGUI;
import model.Usuario;


/**
 *
 * @author Daniel Lima
 */
//CLASSE Q INSTANCIA TODAS AS TELAS E CONTROLA A ABERTURA DE TELAS DE ACORDO COM A POSIÇAO
//E VAI TER A MAIN
public class Principal {
    private static LoginGUI loginGUI;
    
    public static void main(String []args) {
   
       loginGUI  = new LoginGUI();
       loginGUI.setVisible(true);
    }
}
