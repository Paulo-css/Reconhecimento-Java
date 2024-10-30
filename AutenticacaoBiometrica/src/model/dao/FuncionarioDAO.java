/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Conection.ConnectionFactory;
import model.bean.Funcionario;

/**
 *
 * @author paulo
 */
public class FuncionarioDAO {
        public void create(Funcionario f){
            Connection con = ConnectionFactory.conectadb();
            PreparedStatement stmt = null;
            
            try {
                stmt = con.prepareStatement("INSERT INTO funcionario(name,birthday,cpf,foto,cargo)VALUES(?,?,?,?,?)");
                stmt.setString(1,f.getName());
                stmt.setString(2,f.getBirthday());
                stmt.setString(3,f.getCpf());
                stmt.setString(4,f.getFoto());
                stmt.setString(5,f.getCargo());
                
                stmt.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.println("Erro na conexão ao banco de dados");
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        
        public Funcionario read(String num){
            Connection con = ConnectionFactory.conectadb();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Funcionario fun = null;
            try {
                stmt=con.prepareStatement("SELECT name,birthday,cpf,foto,cargo FROM funcionario WHERE cpf = ?");
                stmt.setString(1,num);
                rs = stmt.executeQuery();
                if(rs.next()){
                    String name = (rs.getString("name"));
                    String birthday = (rs.getString("birthday"));
                    String cpf = (rs.getString("cpf"));
                    String fotto = (rs.getString("foto"));
                    String cargo = (rs.getString("cargo"));
                    fun = new Funcionario(name,birthday,cpf,fotto,cargo);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            return fun;
        }
        
        public Funcionario excluir(String cpf){
            Connection con = ConnectionFactory.conectadb();
            PreparedStatement stmt = null;
            
            try {
                stmt = con.prepareStatement("DELETE FROM funcionario WHERE cpf = ?");
                stmt.setString(1,cpf);
                JOptionPane.showConfirmDialog(null,"Deseja Continuar ?","Atenção !!!",2);
                
                stmt.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.println("Erro na conexão ao banco de dados");
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
            return null;
        }
}

