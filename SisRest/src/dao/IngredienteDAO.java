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
import model.Estoque;
import model.Ingrediente;
import model.IngredienteHasReceita;
import model.Produto;
import model.Usuario;

/**
 *
 * @author tatuapu
 */
public class IngredienteDAO  implements DAO{
    private Connection conn;
    
    public IngredienteDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
    
   
    public void Cadastrar(Ingrediente ingrediente) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (ingrediente == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO ingrediente (nome_ingrediente,preco_ingrediente) VALUES (?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setString(1, ingrediente.getNomeIngrediente());
            ps.setDouble(2, ingrediente.getPrecoIngrediente());
            
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do Ingrediente: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    @Override
    public void excluir(Object ob) throws Exception{
        Ingrediente ing = (Ingrediente) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(ing==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM ingrediente WHERE nome_ingrediente=?");
            ps.setString(1, ing.getNomeIngrediente());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
        Ingrediente ing = (Ingrediente) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        
        if(ing==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE ingrediente SET nome_ingrediente=?, preco_ingrediente=? WHERE id_ingrediente=?");
            ps.setString(1, ing.getNomeIngrediente());
            ps.setDouble(2, ing.getPrecoIngrediente());
            ps.setInt(3,ing.getIdIngrediente());
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    public List consultaQntdEstoquePeloID(int id_ingrediente) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Estoque est = new Estoque();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT estoque.qtd_estoque,estoque.id_ingrediente FROM estoque,ingrediente WHERE estoque.id_ingrediente = ingrediente.id_ingrediente AND estoque.id_ingrediente = ?");
            ps.setInt(1, id_ingrediente);
            rs = ps.executeQuery();
           
            List<Estoque> list = new ArrayList<Estoque>();
            while(rs.next()){
                   
                   est.setQtdEstoque(rs.getInt(1));
                   est.setIdIngrediente(rs.getInt(2));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Estoque(est.getQtdEstoque(),est.getIdEstoque()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    
    public int retornaQtdIngredienteReceita(int id_ingrediente,int id_receita) throws Exception{
      PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        IngredienteHasReceita ihr = new IngredienteHasReceita();
      
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT receita_has_ingrediente.receita_ingrediente_qtd FROM receita_has_ingrediente WHERE receita_has_ingrediente.id_receita = ? AND receita_has_ingrediente.id_ingrediente = ?");
          
            ps.setInt(1,id_receita);
            ps.setInt(2,id_ingrediente);
            
            rs = ps.executeQuery();
            
             if (rs.next()) {
                 
                ihr.setReceitaIngredienteQtd(rs.getInt(1));
                //System.out.println("" + pV.getQtdPrdVenda());
              //  System.out.println("" + usu.getNivelUsuario());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
       
        return ihr.getReceitaIngredienteQtd();
    }
    
    public List listaTodosIngredienteEstoque(Ingrediente ing) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT ingrediente.id_ingrediente,ingrediente.nome_ingrediente,ingrediente.preco_ingrediente FROM estoque INNER JOIN ingrediente ON ingrediente.id_ingrediente = estoque.id_ingrediente");
            rs = ps.executeQuery();
           
            List<Ingrediente> list = new ArrayList<Ingrediente>();
            while(rs.next()){
                   
                   ing.setIdIngrediente(rs.getInt(1));
                   ing.setNomeIngrediente(rs.getString(2));
                   ing.setPrecoIngrediente(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Ingrediente(ing.getIdIngrediente(),ing.getNomeIngrediente(),ing.getPrecoIngrediente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    public List listaTodosIngredientes(Ingrediente ingrediente) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM ingrediente");
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
    
    public List consultaTodosIngredientes(int id_ingrediente) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Ingrediente ingrediente = new Ingrediente();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM ingrediente WHERE id_ingrediente = ?");
            ps.setInt(1, id_ingrediente);
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
    public List listaComChaveEstrangeira() throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Ingrediente ing = new Ingrediente();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM ingrediente INNER JOIN estoque ON ingrediente.id_ingrediente = estoque.id_ingrediente");
            //ps.setInt(1,id_ingrediente);
            
            rs = ps.executeQuery();
            List<Ingrediente> list = new ArrayList<Ingrediente>();
            while(rs.next()){
                   //usu.setIdUsuario(rs.getInt(1));
                   ing.setIdIngrediente(rs.getInt(1));
                   ing.setNomeIngrediente(rs.getString(2));
                   //usu.setNomeUsuario(rs.getString(2));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Ingrediente(ing.getIdIngrediente(),ing.getNomeIngrediente()));
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
    public List procura(Object ob) throws Exception{
        Usuario usu = (Usuario) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        if(usu == null)
            throw new Exception("O valor passado não pode ser nulo");
        try{
            conn = this.conn;
            String SQL = "SELECT * FROM usuario  ";
            String where = "";
            boolean checa = false;
            if(usu.getIdUsuario()!=0 || usu.getNomeUsuario()!= null || usu.getSobreNomeUsuario()!=null  || usu.getSexoUsuario() != null ||  usu.getNivelUsuario() != null){
                where = "where ";
                if(usu.getIdUsuario()!=0){
                    where += "id_usu ? ";
                    checa = true;
                }
                if(usu.getNomeUsuario()!=null){
                    if(checa) where+="and";
                    where += " nome_usu= ? ";
                    checa = true;
                }
//                if(aut.getNmCitacao()!=null){
//                    if(checa) where+="and";
//                    where += " nmCitacao=? ";
//                }
            }
            ps = conn.prepareStatement(SQL+where);
              //variavel usada pra inserir no where
            int contaCampos=1;
            if(usu.getIdUsuario()!=0 || usu.getNomeUsuario()!= null || usu.getSobreNomeUsuario()!=null  || usu.getSexoUsuario() != null ||  usu.getNivelUsuario() != null){
                if (usu.getIdUsuario()!=0){
                    ps.setInt(contaCampos,usu.getIdUsuario());
                    contaCampos++;
                }
                if (usu.getNomeUsuario()!=null){
                    ps.setString(contaCampos,usu.getNomeUsuario());
                    contaCampos++;
                }
            }
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();
            while(rs.next()){
                Integer idUsuario = rs.getInt(1);
                String loginUsuario = rs.getString(2);
                String nomeUsuario = rs.getString(3);
                String sobreNomeUsuario = rs.getString(4);
                String sexoUsuario = rs.getString(5);
                String nivelUsuario = rs.getString(6);
                String senhaUsuario = rs.getString(7);
                
                list.add(new Usuario(idUsuario,loginUsuario,nomeUsuario, sobreNomeUsuario, sexoUsuario,nivelUsuario,senhaUsuario));
            }
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps,rs);
        }
    }
    //public List 
    public void pesquisar(Ingrediente ing){
       
         String sql;
         
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT * FROM ingrediente WHERE id_ingrediente= ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ing.getIdIngrediente());
            
            ResultSet rs;
            rs = ps.executeQuery();
            
            if (rs.next()) {
                ing.setNomeIngrediente(rs.getString("nome_ingrediente"));
                ing.setPrecoIngrediente(rs.getDouble("preco_ingrediente"));
             

              //  System.out.println("" + usu.getNivelUsuario());
            } else {
                //JOptionPane.showMessageDialog(this, "Acesso Negado \nInforme o setor de Inventário");
                ps.close();
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    
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
   
    
}



