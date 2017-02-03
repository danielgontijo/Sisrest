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
import model.Cliente;
import model.Usuario;

/**
 *
 * @author tatuapu
 */
public class ClienteDAO  implements DAO{
    private Connection conn;
    
    public ClienteDAO() throws Exception{
        try{
            this.conn = ConnectionDAO.getConnection();
        }catch(Exception e){
            throw new Exception("Erro: \n"+e.getMessage());                    
        }
    }
    @Override
    public void salvar(Object ob) throws Exception{
        Cliente cli = (Cliente) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (cli == null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            String SQL = "INSERT INTO cliente (nome_cliente,sobrenome_cliente,cpf_cliente) VALUES (?,?,?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            //o 1 e para cada posiçao no values //
            ps.setString(1, cli.getNomeCliente());
            ps.setString(2, cli.getSobreNomeCliente());
            ps.setString(3, cli.getCpfCliente());

            ps.executeUpdate();
        }catch (SQLException sqle){
            throw new Exception("Erro ao inserir dados do Usuario: \n"+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn,ps);
        }
    }
    
    public List consultaClienteID(int id_cliente) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cliente cli = new Cliente();
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM cliente WHERE cliente.id_cliente =  ? ");
            ps.setInt(1, id_cliente);
            rs = ps.executeQuery();
            List<Cliente> list = new ArrayList<Cliente>();
            while(rs.next()){
                  
                  cli.setIdCliente(rs.getInt(1));
                  cli.setNomeCliente(rs.getString(2));
                  cli.setSobreNomeCliente(rs.getString(3));
                  cli.setCpfCliente(rs.getString(4));
                  // System.out.println("" + usu.getNomeUsuario());
                  list.add(new Cliente(cli.getIdCliente(),cli.getNomeCliente(),cli.getSobreNomeCliente(),cli.getCpfCliente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
   }
    
    public List clientesDeVenda() throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cliente cli = new Cliente();
        try{
            conn = this.conn;
            
            ps = conn.prepareStatement("SELECT * FROM cliente INNER JOIN vendas ON vendas.id_cliente = cliente.id_cliente WHERE vendas.status_vendas <> 'Pronto' ");
            rs = ps.executeQuery();
            List<Cliente> list = new ArrayList<Cliente>();
            
            while(rs.next()){
                   cli.setIdCliente(rs.getInt(1));
                   cli.setNomeCliente(rs.getString(2));
                   cli.setSobreNomeCliente(rs.getString(3));
                   cli.setCpfCliente(rs.getString(4));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Cliente(cli.getIdCliente(),cli.getNomeCliente(),cli.getSobreNomeCliente(),cli.getCpfCliente()));
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
        Cliente cli = (Cliente) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(cli==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
       
        try{
            conn = this.conn;
            ps = conn.prepareStatement("DELETE FROM cliente WHERE id_cliente=?");
            
            ps.setInt(1, cli.getIdCliente());
            ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao excluir dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    @Override
    public void atualizar(Object ob) throws Exception{
        Cliente cli = (Cliente) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        if(cli==null){
            throw new Exception("O valor passado não pode ser nulo");
        }
        try{
            conn = this.conn;
            ps = conn.prepareStatement("UPDATE cliente SET nome_cliente=?, sobrenome_cliente=? , cpf_cliente = ? WHERE id_cliente=?");
                ps.setString(1, cli.getNomeCliente());
                ps.setString(2, cli.getSobreNomeCliente());
                ps.setString(3, cli.getCpfCliente());
                
                ps.setInt(4, cli.getIdCliente());
                //System.out.println("" + usu.getIdUsuario());
                ps.executeUpdate();
        }catch(SQLException sqle){
            throw new Exception("Erro ao atualizar dados: "+sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    public List listaTodosClientes(Cliente cli) throws Exception{
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = this.conn;
            ps = conn.prepareStatement("SELECT * FROM cliente");
            rs = ps.executeQuery();
            List<Cliente> list = new ArrayList<Cliente>();
            while(rs.next()){
                   cli.setIdCliente(rs.getInt(1));
                   cli.setNomeCliente(rs.getString(2));
                   cli.setSobreNomeCliente(rs.getString(3));
                   cli.setCpfCliente(rs.getString(4));
                  // System.out.println("" + usu.getNomeUsuario());
                   list.add(new Cliente(cli.getIdCliente(),cli.getNomeCliente(),cli.getSobreNomeCliente(),cli.getCpfCliente()));
            }
            //System.out.println(""+list.size());
            return list;
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
    }
    
    public void pesquisar(Cliente cli){
       
         String sql;
         
        try {
            Connection conn = null;
            // Nessa linha a conexao recebe o objeto que foi instanciado no construtor//
            conn = this.conn;
            //interrogaçao sao os dados que vao chegar para o select
            sql = "SELECT * FROM cliente WHERE id_cliente= ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cli.getIdCliente());
            
            ResultSet rs;
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cli.setNomeCliente(rs.getString("nome_cliente"));
                cli.setSobreNomeCliente(rs.getString("sobrenome_cliente"));
                cli.setCpfCliente(rs.getString("cpf_cliente"));
              
              //  System.out.println("" + usu.getNivelUsuario());
            } else {
                //JOptionPane.showMessageDialog(this, "Acesso Negado \nInforme o setor de Inventário");
                ps.close();
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
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
   
    
}



