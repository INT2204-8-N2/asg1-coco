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
                String url = "jdbc:sqlite:dictionaries.db";
                conn = DriverManager.getConnection(url);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return conn;
    }
     public  void  setId(){
        String sql="SELECT MAX(idx) FROM tbl_edict";
        int idmax=0;
        ResultSet rs=null;
        try (Connection conn = this.connect()){    
             rs  = this.excuteQuery(sql);
            idmax=rs.getInt("MAX(idx)");
            conn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        this.id=idmax+1;
    }
     public void insert(String eng, String viet){
       String sql = "INSERT INTO tbl_edict(idx,word,detail) VALUES("+(id++) +",'"+eng+"','"+viet+"')";

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
        String sql = "UPDATE tbl_edict SET word = ? , "
                + "detail = ? "
                + "WHERE idx = ?";
 
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
        String sql = "DELETE FROM tbl_edict WHERE word = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Word getword(String word) {
        String sql = "SELECT * "+ "FROM tbl_edict WHERE word = \""+word+"\"";
        ResultSet rs=null;
        Word ev=new Word();
        try (Connection conn = this.connect()){
             rs  = this.excuteQuery(sql);
             while(rs.next()){
                 ev.setId(rs.getInt("idx"));
                 ev.setSpelling(rs.getString("word"));
                 ev.setExplain(rs.getString("detail"));
             }
  
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ev;
        
    }   
    
      protected Statement getStatement() throws Exception {  //ham thuc thi cau lenh Query
      // Neu staement =null hoac da dong thi can khoi tao lai
      Statement statement = null;
        if (statement == null ? true : statement.isClosed()) {
            statement = this.connect().createStatement();
        }
        return statement;
    }
     public ResultSet excuteQuery(String query) throws Exception {
         ResultSet result;
        try {
            //thực thi câu lệnh
            result = this.getStatement().executeQuery(query);
        } catch (Exception e) {
            throw new Exception("Lỗi:" + e.getMessage());
        }
        return result; 
    }
    //Lay gia tri tra ve cua cac cau lenh: insert, update, delete, cerate
    public int excuteUpdate(String query) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            res = this.getStatement().executeUpdate(query);
        } catch (Exception e) {
            throw new Exception("Lỗi:" + e.getMessage());
        } 
        return res;
    }
    public Word getData(int idd){
       String sql = "SELECT * "+ "FROM tbl_edict WHERE idx = '"+ idd+ "'";
        ResultSet rs=null;
        Word ev=new Word();
        try (Connection conn = this.connect()){
//             PreparedStatement pstmt  = conn.prepareStatement(sql)){
//            
//            // set the value
//            pstmt.setString(1,word);
//            //
             rs  = this.excuteQuery(sql);
            
             while(rs.next()){
                 ev.setSpelling(rs.getString("word"));
                 ev.setExplain(rs.getString("detail"));
             }
  
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ev;
    }
    
    public static void main(String[] args) {
//        DTB dtb = new DTB();
//       dtb.setId();
//        dtb.insert("okk1", "ổn");
//         dtb.insert("okk2", "ổn");
//        
  }
}
