package ChatServer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZISHAN
 */
public class JdbcOdbc {
    private Connection con;
    OraclePreparedStatement pst=null;
    OracleResultSet rs=null;
    public JdbcOdbc(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "sanu786");  
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }
    public boolean authentiction(String userName , String password) throws SQLException{
            String sql="select * from Login where username='"+userName+"'and password='"+password+"' ";
            System.out.println("Authentiction of DB userName"+userName+"8");
            pst = (OraclePreparedStatement) con.prepareStatement(sql);
            //pst.setString(1, userName);
            rs=(OracleResultSet) pst.executeQuery();
          //  if(rs.next())
           //     System.out.println("Data Base Responce "+rs.getString(1)+" ");
        return rs.next();
    }
    public boolean isUserExist(String user){
        String sql="select * from Login where username='"+user+"'";
        try {
            pst = (OraclePreparedStatement) con.prepareStatement(sql);
            rs=(OracleResultSet) pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcOdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createUser(String msg){
        String details[]=msg.split(" ")[1].split(",");
        
        try {
                String sql="insert into Registration values(?,?,?,?,?,?)";
                String loginSql="insert into Login values(?,?)";
                pst = (OraclePreparedStatement) con.prepareStatement(sql);
                pst.setString(1, details[0]);
                pst.setString(2, details[1]);
                pst.setString(3, details[2]);
                pst.setString(4, details[3]);
                pst.setString(5, details[4]);
                pst.setString(6, details[5]);
                pst.executeUpdate();
                pst = (OraclePreparedStatement) con.prepareStatement(loginSql);
                pst.setString(1, details[3]);
                pst.setString(2, details[4]);
                pst.executeUpdate();
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(JdbcOdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public String getPassword(String user){
         String sql="select * from Login where username='"+user+"'";
        try {
            pst = (OraclePreparedStatement) con.prepareStatement(sql);
            rs=(OracleResultSet) pst.executeQuery();
            if( rs.next());
               return rs.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcOdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
