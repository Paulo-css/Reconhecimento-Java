/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Conection.ConnectionFactory;
import model.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author work-
 */
public class ClienteDAO {

    Connection con = ConnectionFactory.conectadb();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Cliente> lista = new ArrayList<>();

    public void create(Cliente c) {
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (name,id,terreno,status) VALUES (?,?,?,?)");
            stmt.setString(1, c.getName());
            stmt.setInt(2, c.getId());
            stmt.setString(3, c.getTerreno());
            stmt.setString(4, c.getStatus());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar cliente", "Erro", 0);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Cliente> read() {

        try {
            stmt = con.prepareStatement("SELECT id,name,terreno,status FROM cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setName(rs.getString("name"));
                cliente.setId(rs.getInt("id"));
                cliente.setTerreno(rs.getString("terreno"));
                cliente.setStatus(rs.getString("status"));
                
                lista.add(cliente);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Cliente DAO, Erro Pesquisar :"+erro);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
        public ArrayList<Cliente> readgerente() {

        try {
            stmt = con.prepareStatement("SELECT id,name,terreno,status,birthday FROM cliente");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setName(rs.getString("name"));
                cliente.setId(rs.getInt("id"));
                cliente.setTerreno(rs.getString("terreno"));
                cliente.setStatus(rs.getString("status"));
                cliente.setBirthday(rs.getString("birthday"));
                
                lista.add(cliente);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Cliente DAO, Erro Pesquisar :"+erro);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
   
    
    public void alterar(Cliente c){
        try{
            stmt=con.prepareStatement("UPDATE cliente SET birthday = ?,cpf = ?, foto = ? WHERE id = ?");
            stmt.setString(1, c.getBirthday());
            stmt.setString(2, c.getCpf());
            stmt.setString(3,c.getFoto());
            stmt.setInt(4, c.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso", "alteração",0);
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar cliente", "Erro", 0);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
