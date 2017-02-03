/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import util.DAO;
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
import model.Ingrediente;
import model.IngredienteHasReceita;
import model.Produto;
import model.ProdutoHasVenda;
import model.Receita;
import model.Venda;

/**
 *
 * @author Daniel Lima
 */
public class ProdutoDaVendaDAO implements DAO{
     
    public ProdutoDaVendaDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
    private Connection conn;
    
    public void salvarProdutoDaVenda(Produto prod,Venda vend,ProdutoHasVenda pV) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        //System.out.println("" + idReceita);
        if (prod == null && vend == null && pV == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO produto_has_venda(qtd_prd_vend,id_produto,id_vendas) VALUES (?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setInt(1, pV.getQtdPrdVenda());
            ps.setInt(2, prod.getIdProduto());
            ps.setInt(3, vend.getIdVendas());
            
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do produto: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    
    }
    
    public int retornaQtdProdutoExistente(Venda vend,Produto prod) throws Exception{
      PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        ProdutoHasVenda pV = new ProdutoHasVenda();
      
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT qtd_prd_vend FROM produto_has_venda WHERE produto_has_venda.id_produto = ? AND produto_has_venda.id_vendas = ?");
          
            ps.setInt(1,prod.getIdProduto());
            ps.setInt(2,vend.getIdVendas());
            
            rs = ps.executeQuery();
            
             if (rs.next()) {
                 
                pV.setQtdPrdVenda(rs.getInt(1));
                //System.out.println("" + pV.getQtdPrdVenda());
              //  System.out.println("" + usu.getNivelUsuario());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
       
        return pV.getQtdPrdVenda();
    }
    
    //SELECT * FROM produto,produto_has_venda WHERE produto.id_produto = produto_has_venda.id_produto AND produto_has_venda.id_vendas = ?
    
    
    public List listaQntdProduto(int id_venda) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        ProdutoHasVenda pV = new ProdutoHasVenda();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT produto_has_venda.qtd_prd_vend FROM produto_has_venda WHERE produto_has_venda.id_vendas = ?");
            ps.setInt(1, id_venda);
            
            rs = ps.executeQuery();
            
            List<ProdutoHasVenda> list = new ArrayList<ProdutoHasVenda>();
            while(rs.next()){
                   //iR.setQtdEstoque(rs.getInt(1));
                   pV.setQtdPrdVenda(rs.getInt(1));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new ProdutoHasVenda(pV.getQtdPrdVenda()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    public List listaProdutosVenda(int id_venda) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        ProdutoHasVenda pV = new ProdutoHasVenda();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM produto_has_venda WHERE produto_has_venda.id_vendas = ? ");
            ps.setInt(1,id_venda);
            
            rs = ps.executeQuery();
            
            List<ProdutoHasVenda> list = new ArrayList<ProdutoHasVenda>();
            while(rs.next()){
                   //iR.setQtdEstoque(rs.getInt(1));
                   
                   int qtdPrdVenda = rs.getInt(1);
                   int idProduto = rs.getInt(2);
                   int idVenda = rs.getInt(3);
                  
                   
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new ProdutoHasVenda(qtdPrdVenda,idProduto,idVenda));
            }
            System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    public List listaQntdProdutoProducao(Date dataProd) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        ProdutoHasVenda pV = new ProdutoHasVenda();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT produto_has_venda.qtd_prd_vend FROM produto,produto_has_venda,vendas WHERE produto.id_produto = produto_has_venda.id_produto AND vendas.status_vendas <> 'Pronto' AND produto_has_venda.id_vendas = vendas.id_vendas AND vendas.data_vendas = ? ");
            ps.setDate(1, dataProd);
            
            rs = ps.executeQuery();
            
            List<ProdutoHasVenda> list = new ArrayList<ProdutoHasVenda>();
            while(rs.next()){
                   //iR.setQtdEstoque(rs.getInt(1));
                   pV.setQtdPrdVenda(rs.getInt(1));
                   
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new ProdutoHasVenda(pV.getQtdPrdVenda()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    public void atualizarQtd(Venda vend,Produto prod,int qtdProdAdd) throws Exception{
       
        ProdutoHasVenda pV = new ProdutoHasVenda();
        PreparedStatement ps = null;
        Connection conn = null;
        if(pV==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE produto_has_venda SET qtd_prd_vend = qtd_prd_vend + ? WHERE id_vendas = ? AND id_produto = ?");
                
                ps.setInt(1,qtdProdAdd);
                ps.setInt(2, vend.getIdVendas());
                ps.setInt(3, prod.getIdProduto());
               
                ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }

    public void excluirProduto(Object ob,Produto prod) throws Exception {
        Venda vend = (Venda) ob;
        
        PreparedStatement ps = null;
        Connection conn = null;
        if(prod==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
       
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM produto_has_venda WHERE id_vendas = ? AND id_produto = ?");
            
            ps.setInt(1, vend.getIdVendas());
            ps.setInt(2, prod.getIdProduto());
            //System.out.println("" + rece.getIdReceita());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
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

    @Override
    public void atualizar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
