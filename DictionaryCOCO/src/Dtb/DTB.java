
package Dtb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DTB {
    Connection conn = null;
    int id=139244;
    private Connection connect() {
      try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:dictionaries.db";
                conn = DriverManager.getConnection(url);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return conn;
    }
     public void insert(String eng, String viet) {
        String sql = "INSERT INTO tbl_edict(idx,word,detail) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id++);
            pstmt.setString(2, eng);
            pstmt.setString(3, viet);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public void update(int id, String word, String detail) {
        String sql = "UPDATE tbl_edict SET word = ? , "
                + "detail = ? "
                + "WHERE idx = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, word);
            pstmt.setString(2, detail);
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       public void delete(String word) {
        String sql = "DELETE FROM tbl_edict WHERE word = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, word);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ResultSet getword(String word) {
        String sql = "SELECT * "+ "FROM tbl_edict WHERE word = '"+ word + "'";
        ResultSet rs=null;
        try (Connection conn = this.connect()){
//             PreparedStatement pstmt  = conn.prepareStatement(sql)){
//            
//            // set the value
//            pstmt.setString(1,word);
//            //
             rs  = this.excuteQuery(sql);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
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
    
}
