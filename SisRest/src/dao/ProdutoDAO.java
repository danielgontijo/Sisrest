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
import model.Produto;
import model.Receita;
import model.Usuario;
import model.Venda;

/**
 *
 * @author tatuapu
 */
public class ProdutoDAO  implements DAO{
    private Connection conn;
    
    public ProdutoDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
   
    public void cadastrarProduto(Produto prod,int idReceita) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        System.out.println("" + idReceita);
        if (prod == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO produto (nome_produto,preco_produto,id_receita) VALUES (?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setString(1, prod.getNomeProduto());
            ps.setDouble(2, prod.getPrecoProduto());
            ps.setInt(3, idReceita);
           
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    @Override
    public void excluir(Object ob) throws Exception{
        Produto prod = (Produto) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(prod==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM produto WHERE nome_produto=?");
            ps.setString(1,prod.getNomeProduto());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
//        Usuario usu = (Usuario) ob;
           Produto prod = (Produto)ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(prod==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE produto SET nome_produto=?, preco_produto=? WHERE id_produto=? ");
            ps.setString(1, prod.getNomeProduto());
            ps.setDouble(2, prod.getPrecoProduto());
            ps.setInt(3, prod.getIdProduto());
            System.out.println("" + prod.getIdProduto());
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
            System.out.println("" + prod.getIdProduto());
            System.out.println("" + rec.getIdReceita());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
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
    //SELECT * FROM produto INNER JOIN produto_has_venda ON produto.id_produto = produto_has_venda.id_produto AND produto_has_venda.id_vendas = ?
            
    public List listaTodosProdutos(Produto prod) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM produto");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while(rs.next()){
                   prod.setIdProduto(rs.getInt(1));
                   prod.setNomeProduto(rs.getString(2));
                   prod.setPrecoProduto(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Produto(prod.getIdProduto(),prod.getNomeProduto(),prod.getPrecoProduto()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    public List listaDadosVendaDataFixa(Date data) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto prod = new Produto();
        //System.out.println(dataIni);
           try{
            conn = this.conn;
            //String where = "'" + "?" + "'";
            String sql = "SELECT produto.id_produto,produto.nome_produto,produto.preco_produto FROM produto,produto_has_venda,vendas WHERE produto.id_produto = produto_has_venda.id_produto AND vendas.status_vendas <> 'Pronto' AND produto_has_venda.id_vendas = vendas.id_vendas AND vendas.data_vendas = ?";
            java.sql.PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDate(1,data);
           
            rs = stmt.executeQuery();
            
            List<Produto> list = new ArrayList<Produto>();
            
            while(rs.next()){
                   //usu.setIdUsuario(rs.getInt(1));
                   prod.setIdProduto(rs.getInt(1));
                   prod.setNomeProduto(rs.getString(2));
                   prod.setPrecoProduto(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Produto(prod.getIdProduto(),prod.getNomeProduto(),prod.getPrecoProduto()));
            }
            //System.out.println(""+list.size());
            stmt.close();
            return list;
            
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps,rs);
        }
    }
    
    public List listaDadosProdPeloID(int id_vendas) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto prod = new Produto();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM produto,produto_has_venda WHERE produto.id_produto = produto_has_venda.id_produto AND produto_has_venda.id_vendas = ?");
            ps.setInt(1, id_vendas);
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while(rs.next()){
                   prod.setIdProduto(rs.getInt(1));
                   prod.setNomeProduto(rs.getString(2));
                   prod.setPrecoProduto(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Produto(prod.getIdProduto(),prod.getNomeProduto(),prod.getPrecoProduto()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    public List listaProdutoAdd(int id_vendas) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Produto prod = new Produto();
        try{
            
            conn = this.conn;
            ps = conn.prepareStatement("SELECT produto.id_produto,produto.nome_produto,produto.preco_produto FROM produto,produto_has_venda WHERE produto.id_produto = produto_has_venda.id_produto AND produto_has_venda.id_vendas = ?");
            ps.setInt(1,id_vendas);
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while(rs.next()){
                   prod.setIdProduto(rs.getInt(1));
                   prod.setNomeProduto(rs.getString(2));
                   prod.setPrecoProduto(rs.getDouble(3));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Produto(prod.getIdProduto(),prod.getNomeProduto(),prod.getPrecoProduto()));
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
                String nomeUsuario = rs.getString(2);
                String sobreNomeUsuario = rs.getString(3);
                String sexoUsuario = rs.getString(4);
                String nivelUsuario = rs.getString(5);
                String senhaUsuario = rs.getString(5);
                
                list.add(new Usuario(idUsuario,loginUsuario,nomeUsuario, sobreNomeUsuario, sexoUsuario,nivelUsuario,senhaUsuario));
            }
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps,rs);
        }
    }
    public void pesquisar(Produto prod){
       
         String sql;
         
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT * FROM produto WHERE id_produto = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, prod.getIdProduto());
            
            ResultSet rs;
            rs = ps.executeQuery();
            
            if (rs.next()) {
               prod.setNomeProduto(rs.getString("nome_produto"));
               prod.setPrecoProduto(rs.getDouble("preco_produto"));
                //System.out.println("" + prod.getNomeProduto());
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



