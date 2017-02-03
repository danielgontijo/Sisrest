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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Estoque;
import model.Ingrediente;
import model.Produto;
import model.Receita;
import model.Usuario;

/**
 *
 * @author tatuapu
 */
public class EstoqueDAO  implements DAO{
    private Connection conn;
    
    public EstoqueDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
   
    public void cadastrar(Estoque est,int id_ingrediente) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        //System.out.println("" + idReceita);
        if (est == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO estoque (qtd_estoque,num_lote_estoque,data_validade_estoque,id_ingrediente) VALUES (?,?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setInt(1, est.getQtdEstoque());
            ps.setString(2,est.getNumeroLoteEstoque());
            ps.setDate(3,est.getDataVencimentoEstoque());
            ps.setInt(4,id_ingrediente);
           
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    public List listaTodoEstoque() throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Estoque est = new Estoque();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM estoque");
            rs = ps.executeQuery();
            List<Estoque> list = new ArrayList<Estoque>();
            while(rs.next()){
                   //usu.setIdUsuario(rs.getInt(1));
                   est.setIdEstoque(rs.getInt(1));
                   est.setQtdEstoque(rs.getInt(2));
                   est.setDataVencimentoEstoque(rs.getDate(3));
                   est.setNumeroLoteEstoque(rs.getString(4));
                   est.setIdIngrediente(rs.getInt(5));
                   //usu.setNomeUsuario(rs.getString(2));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Estoque(est.getIdEstoque(),est.getQtdEstoque(),est.getDataVencimentoEstoque(),est.getNumeroLoteEstoque(),est.getIdIngrediente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    public List listaIngredienteNoEstoque(Ingrediente ing) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM estoque INNER JOIN ingrediente ON estoque.id_ingrediente = ingrediente.id_ingrediente");
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
    public void excluir(Object ob) throws Exception{
        Estoque est = (Estoque) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(est==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM estoque WHERE id_estoque = ?");
            //System.out.println("" + est.getIdEstoque());
            ps.setInt(1,est.getIdEstoque());
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
           Estoque est = (Estoque) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(est==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE estoque SET qtd_estoque=?,data_validade_estoque=? ,num_lote_estoque = ? WHERE id_estoque=? ");
            ps.setInt(1, est.getQtdEstoque());
            ps.setDate(2,est.getDataVencimentoEstoque());
            ps.setString(3, est.getNumeroLoteEstoque());
            ps.setInt(4,est.getIdEstoque());
            
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    public void retiraIngEstoque(int id_ing,int qtd_escolhida,int qntdRetirar) throws Exception{
           
        PreparedStatement ps = null;
        Connection conn = null;
//        if(est==null){
//            throw new Exception("O valor passado não pode ser nulo");
//        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE estoque SET estoque.qtd_estoque = estoque.qtd_estoque - ?*? WHERE id_ingrediente = ?");
            
            ps.setInt(1,qntdRetirar);
            ps.setInt(2,qtd_escolhida);
            ps.setInt(3,id_ing);
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
    
    
    public void repoeIngEstoque(int id_ing,int qtd_escolhida,int qntdRepor) throws Exception{
           
        PreparedStatement ps = null;
        Connection conn = null;
//        if(est==null){
//            throw new Exception("O valor passado não pode ser nulo");
//        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE estoque SET estoque.qtd_estoque = estoque.qtd_estoque + ?*? WHERE id_ingrediente = ?");
            
            ps.setInt(1,qntdRepor);
            ps.setInt(2,qtd_escolhida);
            ps.setInt(3,id_ing);
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
    
     public void alterar(Produto prod,Receita rec) throws Exception{
       
        PreparedStatement ps = null;
        Connection conn = null;
        if(prod==null || rec==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE produto SET nome_produto=?, preco_produto=? WHERE id_produto=? and id_receita = ?");
            ps.setString(1, prod.getNomeProduto());
            ps.setDouble(2, prod.getPrecoProduto());
            ps.setInt(3, prod.getIdProduto());
            ps.setInt(4,rec.getIdReceita());
            
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
     }
       public void retornaDadosDoIngrediente(Estoque est) throws Exception{
          PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        if (est == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
            try{
            String SQL = "SELECT estoque.id_estoque,estoque.qtd_estoque,estoque.data_validade_estoque,estoque.num_lote_estoque FROM estoque,ingrediente WHERE estoque.id_ingrediente = ingrediente.id_ingrediente";
        
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            List<Estoque> list = new ArrayList<Estoque>();
            while(rs.next()){
                
                   est.setIdEstoque(rs.getInt(1));
                   est.setQtdEstoque(rs.getInt(2));
                   est.setDataVencimentoEstoque(rs.getDate(3));
                   est.setNumeroLoteEstoque(rs.getString(4));
                  // rece.setNomeReceita(rs.getString(2));
                 
                   list.add(new Estoque(est.getIdEstoque(),est.getQtdEstoque(),est.getDataVencimentoEstoque(),est.getNumeroLoteEstoque(),est.getIdIngrediente()));
                  // System.out.println("" + rece.getIdReceita());
            }
            //System.out.println(""+list.size());
           
            //return list;
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
       
       
    }
     
       public List consultaIngPeloEstoque(int id_ingrediente) throws Exception{
          PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Estoque est = new Estoque();
        if (est == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
            try{
            String SQL = "SELECT * FROM estoque where id_ingrediente = ?";
        
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_ingrediente);
            rs = ps.executeQuery();
            List<Estoque> list = new ArrayList<Estoque>();
            while(rs.next()){
                
                   est.setIdEstoque(rs.getInt(1));
                   est.setQtdEstoque(rs.getInt(2));
                   est.setDataVencimentoEstoque(rs.getDate(3));
                   est.setNumeroLoteEstoque(rs.getString(4));
                  // rece.setNomeReceita(rs.getString(2));
                 
                   list.add(new Estoque(est.getIdEstoque(),est.getQtdEstoque(),est.getDataVencimentoEstoque(),est.getNumeroLoteEstoque(),est.getIdIngrediente()));
                  // System.out.println("" + rece.getIdReceita());
            }
            //System.out.println(""+list.size());
           
            return list;
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }    
       
    }
       
    public List retornaIdChaveEstrangeira(Receita rece) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        if (rece == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "SELECT produto.id_receita FROM receita INNER JOIN produto ON produto.id_receita = receita.id_receita ";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            List<Receita> list = new ArrayList<Receita>();
            while(rs.next()){
                
                   rece.setIdReceita(rs.getInt(1));
                  // rece.setNomeReceita(rs.getString(2));
                 
                   list.add(new Receita(rece.getIdReceita()));
                  // System.out.println("" + rece.getIdReceita());
            }
            //System.out.println(""+list.size());
           
            return list;
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    public List listaQntdEstoque(int id_receita) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Estoque est = new Estoque();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT estoque.qtd_estoque FROM estoque,receita,ingrediente,receita_has_ingrediente\n" +
            "WHERE estoque.id_ingrediente = ingrediente.id_ingrediente AND\n" +
            "receita_has_ingrediente.id_ingrediente = ingrediente.id_ingrediente AND\n" +
            "receita_has_ingrediente.id_receita = receita.id_receita AND\n" +
            "receita.id_receita = ?");
            ps.setInt(1,id_receita);
            rs = ps.executeQuery();
            
            List<Estoque> list = new ArrayList<Estoque>();
            while(rs.next()){
                   est.setQtdEstoque(rs.getInt(1));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Estoque(est.getQtdEstoque()));
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
    public void pesquisar(Estoque est,Ingrediente ing){
       
         String sql;
         
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT * FROM estoque WHERE id_ingrediente = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ing.getIdIngrediente());
            ResultSet rs;
            rs = ps.executeQuery();
            
            if (rs.next()) {
               est.setQtdEstoque(rs.getInt("qtd_estoque"));
               est.setDataVencimentoEstoque(rs.getDate("data_validade_estoque"));
               est.setNumeroLoteEstoque(rs.getString("num_lote_estoque"));
               
//               prod.setNomeProduto(rs.getString("nome_produto"));
//               prod.setPrecoProduto(rs.getDouble("preco_produto"));
//               
                
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



