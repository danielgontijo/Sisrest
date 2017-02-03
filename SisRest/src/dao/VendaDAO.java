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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Usuario;
import model.Venda;

/**
 *
 * @author tatuapu
 */
public class VendaDAO  implements DAO{
    private Connection conn;
    
    public VendaDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
   
    public void salvar(Venda vend,int id_cliente) throws Exception{
        
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (vend == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO vendas (data_vendas,meiopag_vendas,status_vendas,id_cliente) VALUES (?,?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setDate(1,vend.getDataVendas());
            ps.setString(2, vend.getMeioPagamentoVendas());
            ps.setString(3, vend.getStatusVendas());
            ps.setInt(4, id_cliente);
          
            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados da Venda :\n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    @Override
    public void excluir(Object ob) throws Exception{
        Venda vend = (Venda) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(vend==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
       
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM vendas WHERE id_vendas=?");
            
            ps.setInt(1, vend.getIdVendas());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
        Venda vend = (Venda) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(vend==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE vendas SET data_vendas=?, meiopag_vendas=? , status_vendas = ? WHERE id_vendas=?");
                ps.setDate(1,vend.getDataVendas());
                ps.setString(2, vend.getMeioPagamentoVendas());
                ps.setString(3, vend.getStatusVendas());
                ps.setInt(4, vend.getIdVendas());
                
                ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    public void entregarVend(Object ob) throws Exception{
        Venda vend = (Venda) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(vend==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE vendas SET status_vendas = 'Pronto' WHERE id_vendas=?");
               
                ps.setInt(1, vend.getIdVendas());
                
                ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
   
    public List listaVendaPeriodo(Date dataIni,Date dataFim) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Venda vend = new Venda();
        
           try{
            conn = this.conn;
            String sql = "SELECT * FROM vendas WHERE vendas.status_vendas <> 'Andamento' AND vendas.data_vendas between  ? and  ?";
            java.sql.PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDate(1,dataIni);
            stmt.setDate(2,dataFim);
            rs = stmt.executeQuery();
            
            List<Venda> list = new ArrayList<Venda>();
            
            while(rs.next()){
                   
                   vend.setIdVendas(rs.getInt(1));
                   vend.setDataVendas(rs.getDate(2));
                   vend.setMeioPagamentoVendas(rs.getString(3));
                   vend.setStatusVendas(rs.getString(4));
                   vend.setIdCliente(rs.getInt(5));
                   
                   list.add(new Venda(vend.getIdVendas(),vend.getDataVendas(),vend.getMeioPagamentoVendas(),vend.getStatusVendas(),vend.getIdCliente()));
            }
            
            stmt.close();
            return list;
            
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps,rs);
        }
    }
    
    public List listaTodasVendas(Venda vend) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM vendas WHERE vendas.status_vendas AND vendas.status_vendas <> 'Pronto' ");
            rs = ps.executeQuery();
            List<Venda> list = new ArrayList<Venda>();
            while(rs.next()){
                  
                   vend.setIdVendas(rs.getInt(1));
                   vend.setDataVendas(rs.getDate(2));
                   vend.setMeioPagamentoVendas(rs.getString(3));
                   vend.setStatusVendas(rs.getString(4));
                   vend.setIdCliente(rs.getInt(5));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Venda(vend.getIdVendas(),vend.getDataVendas(),vend.getMeioPagamentoVendas(),vend.getStatusVendas(),vend.getIdCliente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
   
    
    public void pesquisar(Venda vend){
       
         String sql;
         
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT * FROM vendas WHERE id_vendas = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, vend.getIdVendas());
            
            ResultSet rs;
            rs = ps.executeQuery();
            
            if (rs.next()) {
                vend.setDataVendas(rs.getDate("data_vendas"));
                vend.setMeioPagamentoVendas(rs.getString("meiopag_vendas"));
                vend.setStatusVendas(rs.getString("status_vendas"));
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

    @Override
    public List procura(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}



