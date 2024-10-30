/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author work-
 */
public class ConnectionFactory {
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final String URL = "jdbc:sqlserver://MAQUINAMONSTRO\\SQLEXPRESS:1433;databaseName=dbbiometria;encrypt=false;trustServerCertificate=true;integratedSecurity=true";
        
        public static Connection conectadb(){
            try {
                return DriverManager.getConnection(URL);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro de conexão");
                return null;
            }
        }
        
        public static void closeConnection(Connection con){
            try {
               if(con!=null){
                    con.close();
                    }
            }catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        public static void closeConnection(Connection con, PreparedStatement stmt){
           closeConnection(con);
            try {
                   if(stmt!=null){
                       stmt.close();
                   }
            }catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
           closeConnection(con,stmt);
            try {
                if(rs!=null){
                    rs.close();
                }
            }catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
}
        