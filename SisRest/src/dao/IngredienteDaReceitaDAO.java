/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.ConnectionDAO;
import util.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Ingrediente;
import model.IngredienteHasReceita;
import model.Receita;

/**
 *
 * @author Daniel Lima
 */
public class IngredienteDaReceitaDAO implements DAO{
     
    public IngredienteDaReceitaDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
    private Connection conn;
    
    public void manterIngredienteDaReceita(Receita rece,Ingrediente ingre,int qntd) throws Exception{
        //System.out.println("" + rece.getIdReceita() + ingre.getIdIngrediente());
        PreparedStatement ps = null;
        Connection conn = null;
        //System.out.println("" + idReceita);
        if (rece == null && ingre == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO receita_has_ingrediente(id_receita,id_ingrediente,receita_ingrediente_qtd) VALUES (?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setInt(1, rece.getIdReceita());
            ps.setInt(2, ingre.getIdIngrediente());
            ps.setInt(3, qntd);
            
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    
    }
    public void atualizarQtdIngDaReceita(Receita rec,Ingrediente ing,int qtdIngAdd) throws Exception{
       
        IngredienteHasReceita iR = new IngredienteHasReceita();
        PreparedStatement ps = null;
        Connection conn = null;
        
        if(rec==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE receita_has_ingrediente SET receita_ingrediente_qtd = receita_ingrediente_qtd + ? WHERE id_receita = ? AND id_ingrediente = ?");
                
                ps.setInt(1,qtdIngAdd);
                ps.setInt(2,rec.getIdReceita());
                ps.setInt(3,ing.getIdIngrediente());
                
                
                ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
     public List consultaIngPeloID(int id_produto) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Ingrediente ingrediente = new Ingrediente();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT ingrediente.id_ingrediente,ingrediente.nome_ingrediente,ingrediente.preco_ingrediente FROM ingrediente,receita_has_ingrediente,produto \n" +
"WHERE ingrediente.id_ingrediente = receita_has_ingrediente.id_ingrediente AND receita_has_ingrediente.id_receita = produto.id_receita AND produto.id_produto = ?");
            ps.setInt(1, id_produto);
            rs = ps.executeQuery();
            List<Ingrediente> list = new ArrayList<Ingrediente>();
            while(rs.next()){
                   ingrediente.setIdIngrediente(rs.getInt(1));
                   ingrediente.setNomeIngrediente(rs.getString(2));
                   ingrediente.setPrecoIngrediente(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Ingrediente(ingrediente.getIdIngrediente(),ingrediente.getNomeIngrediente(),ingrediente.getPrecoIngrediente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    @Override
    public void atualizar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluirIng(Object ob,Receita rece) throws Exception {
        Ingrediente ing = (Ingrediente) ob;
        
        PreparedStatement ps = null;
        Connection conn = null;
        if(ing==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
       
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM receita_has_ingrediente WHERE id_ingrediente = ? AND id_receita = ?");
            
            ps.setInt(1, ing.getIdIngrediente());
            ps.setInt(2, rece.getIdReceita());
            //System.out.println("" + rece.getIdReceita());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
    public List listaQntdEstoque(int id_receita) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        IngredienteHasReceita iR = new IngredienteHasReceita();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT receita_has_ingrediente.receita_ingrediente_qtd FROM ingrediente INNER JOIN receita_has_ingrediente ON ingrediente.id_ingrediente = receita_has_ingrediente.id_ingrediente WHERE id_receita = ?");
            ps.setInt(1,id_receita);
            rs = ps.executeQuery();
            
            List<IngredienteHasReceita> list = new ArrayList<IngredienteHasReceita>();
            while(rs.next()){
                   //iR.setQtdEstoque(rs.getInt(1));
                   iR.setReceitaIngredienteQtd(rs.getInt(1));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new IngredienteHasReceita(iR.getReceitaIngredienteQtd()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    public List listaIdEquantidadeIngredientesDoProduto(int id_produto) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        IngredienteHasReceita iR = new IngredienteHasReceita();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT receita_has_ingrediente.id_ingrediente,receita_has_ingrediente.receita_ingrediente_qtd FROM ingrediente,receita_has_ingrediente,produto \n" +
            "WHERE ingrediente.id_ingrediente = receita_has_ingrediente.id_ingrediente AND receita_has_ingrediente.id_receita = produto.id_receita AND produto.id_produto = ?");
            ps.setInt(1,id_produto);
            rs = ps.executeQuery();
            
            List<IngredienteHasReceita> list = new ArrayList<IngredienteHasReceita>();
            while(rs.next()){
                   //iR.setQtdEstoque(rs.getInt(1));
                   iR.setIdIngrediente(rs.getInt(1));
                  
                   iR.setReceitaIngredienteQtd(rs.getInt(2));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new IngredienteHasReceita(iR.getReceitaIngredienteQtd(),iR.getIdIngrediente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    

    @Override
    public List listaTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List procura(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
