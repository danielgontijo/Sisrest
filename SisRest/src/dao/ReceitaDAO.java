/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Daniel Lima
 */


import util.ConnectionDAO;
import util.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Ingrediente;
import model.Receita;
import model.Usuario;

/**
 *
 * @author tatuapu
 */
public class ReceitaDAO  implements DAO{
    private Connection conn;
    
    public ReceitaDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
   
    public void Cadastrar(Receita rece) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (rece == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO receita (nome_receita) VALUES (?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setString(1, rece.getNomeReceita());
          
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do autor: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    @Override
    public void excluir(Object ob) throws Exception{
        Receita rece = (Receita) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(rece==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM receita WHERE id_receita = ?");
            ps.setInt(1, rece.getIdReceita());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
        Receita rec = (Receita) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(rec==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE receita SET nome_receita=? WHERE id_receita=?");
            ps.setString(1, rec.getNomeReceita());
            ps.setInt(2, rec.getIdReceita());
            System.out.println("" + rec.getIdReceita());
            System.out.println("" + rec.getNomeReceita());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    public List listaTodasReceitas(Receita rec) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM receita");
            rs = ps.executeQuery();
            List<Receita> list = new ArrayList<Receita>();
            while(rs.next()){
                   rec.setIdReceita(rs.getInt(1));
                   rec.setNomeReceita(rs.getString(2));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Receita(rec.getIdReceita(),rec.getNomeReceita()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    public List listaTodosIngredientesReceita(Receita rec,Ingrediente ing) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = this.conn;
            ps = conn.prepareStatement(
                           "SELECT ingrediente.id_ingrediente,ingrediente.nome_ingrediente FROM receita_has_ingrediente INNER JOIN ingrediente\n" +
                           "ON receita_has_ingrediente.id_ingrediente = ingrediente.id_ingrediente \n" +
                           "INNER JOIN receita\n" +
                           "ON receita_has_ingrediente.id_receita = receita.id_receita \n" +
                           "WHERE receita.id_receita = ? ");
            //System.out.println("" + rec.getIdReceita());
            ps.setInt(1, rec.getIdReceita());
            rs = ps.executeQuery();
            
            //ps.setString(1, rec.getNomeReceita());
           // ps.executeUpdate();
            //ps.executeUpdate();
            //rs = ps.executeQuery();
            
            List<Ingrediente> list = new ArrayList<Ingrediente>();
            while(rs.next()){
                   ing.setIdIngrediente(rs.getInt(1));
                   ing.setNomeIngrediente(rs.getString(2));   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Ingrediente(ing.getIdIngrediente(),ing.getNomeIngrediente()));
                  // System.out.println("" + ing.getNomeIngrediente());
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }    
     public int retornaIDReceita(Receita rec,String nomeRec){
       
        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
       //  System.out.println("" + rec.getNomeReceita());
        
        try{
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT id_receita FROM receita WHERE nome_receita = ?";
            
                ps = conn.prepareStatement(sql);
                ps.setString(1, nomeRec);
                System.out.println("" + nomeRec);
                rs =  ps.executeQuery();
                if (rs.next()) {
               
                    
              //  System.out.println("" + usu.getNivelUsuario());
            } else {
                //JOptionPane.showMessageDialog(this, "Acesso Negado \nInforme o setor de Inventário");
                ps.close();
               
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }  
        return rec.getIdReceita();
     }
       
    
    
    
   public boolean consultar(Usuario usu) {
       
//        System.out.println("" + login + senha);
        boolean autenticado = false;
        String sql;
        
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT nome_usu, senha_usu,nivel_usu FROM usuario WHERE nome_usu= ? and senha_usu= ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, usu.getNomeUsuario());
            ps.setString(2, usu.getSenhaUsuario());
            
            ResultSet rs;
            rs = ps.executeQuery();
            
           
            if (rs.next()) {
                String user = rs.getString("nome_usu");
                String pass = rs.getString("senha_usu");
                usu.setNivelUsuario(rs.getString("nivel_usu"));
                
              //  System.out.println("" + usu.getNivelUsuario());
                
                autenticado = true;
            } else {
                //JOptionPane.showMessageDialog(this, "Acesso Negado \nInforme o setor de Inventário");
                ps.close();
                return autenticado;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
       
        return autenticado;
    }


    @Override
    public void salvar(Object ob) throws Exception {
        
    }

    @Override
    public List listaTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List procura(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}



