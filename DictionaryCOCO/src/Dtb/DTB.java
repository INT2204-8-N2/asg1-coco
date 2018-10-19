/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtb;

import dictionarycoco.Word;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DTB {
    Connection conn = null;
    int id;
    public Connection connect() {
      try {     
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:dictionaryvv.db";
                conn = DriverManager.getConnection(url);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return conn;
    }
     public  void  setId(){
        String sql="SELECT MAX(id) FROM Dictionary";
        int idmax=0;
        ResultSet rs=null;
        try (Connection conn = this.connect()){    
             Statement stmt=conn.createStatement();
            rs  = stmt.executeQuery(sql);
            idmax=rs.getInt("MAX(id)");
            conn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        this.id=idmax+1;
    }
     public void insert(String eng, String viet){
       String sql = "INSERT INTO Dictionary(id,word,info) VALUES("+(id++) +",'"+eng+"','"+viet+"')";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DTB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void update(int id, String word, String detail) {
        String sql = "UPDATE Dictionary SET word = ? , "
                + "info = ? "
                + "WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.setString(2, detail);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       public void delete(String word) {
        String sql = "DELETE FROM Dictionary WHERE word = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Word getword(String word) {
        String sql = "SELECT id,word,info "+ "FROM Dictionary WHERE word = \""+word+"\"";
        ResultSet rs=null;
        Word ev=new Word();
        
        ev.setExplain("Không tìm thấy");
        try (Connection conn = this.connect()){
             Statement stmt=conn.createStatement();
             rs  = stmt.executeQuery(sql);
             while(rs.next()){
                 ev.setId(rs.getInt(1));
                 ev.setSpelling(rs.getString(2));
                 ev.setExplain(rs.getString(3));
             }
  
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ev;
        
    }   
    public Word getData(int idd){
       String sql = "SELECT * "+ "FROM Dictionary WHERE id = '"+ idd+ "'";
        ResultSet rs=null;
        Word ev=new Word();
        try (Connection conn = this.connect()){

            Statement stmt=conn.createStatement();
             rs  = stmt.executeQuery(sql);
            
             while(rs.next()){
                 ev.setSpelling(rs.getString(2));
                 ev.setExplain(rs.getString(3));
             }
  
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ev;
    }
    
}
