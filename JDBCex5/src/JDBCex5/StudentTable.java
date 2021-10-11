package JDBCex5;

import java.sql.ResultSet;
import java.sql.SQLException;
import utilities.DatabaseHandler;

public class StudentTable {
    static int rowInserted, rowUpdated, rowDeleted;
    
    public static int InsertStudent(DatabaseHandler dbHandler, Student stu) {
         String sql = "insert into Student (id, name, gpa)" + 
               " values (?,?,?)";
         
         try {
             rowInserted = dbHandler.update(sql, stu.getId(), stu.getName(), stu.getGpa());
         }
         catch(SQLException ex ) {
             rowInserted = 0;
         }
         return rowInserted;
    }
    
    public static int updateStudent(DatabaseHandler dbHandler, Student stu) {
        String sql = "update Student set name=?, gpa=? where id=?";

        try {
            rowUpdated = dbHandler.update(sql, stu.getName(), stu.getGpa(), stu.getId());
        }
        catch (SQLException ex ) {
            rowUpdated = 0;
        }
        return rowUpdated;
    }
    
    public static int removeStudent(DatabaseHandler dbHandler, Student stu) {
         String sql = "delete from Student where id = ?";

         try {
            rowDeleted = dbHandler.update(sql, stu.getId());
         }
         catch (SQLException ex ) {
             rowDeleted = 0;
         }
        return rowDeleted;
    }
    
    public static int removeAllData(DatabaseHandler dbHandler){
        String sql = "delete from Student";
        
        try {
            rowDeleted = dbHandler.update(sql);
        }
        catch (SQLException ex ) {
             rowDeleted = 0;
         }
        return rowDeleted;
    }
    
    public static Student findStudentById(DatabaseHandler dbHandler, int id) throws SQLException {
        String sql = "select * from Student where id = ?";
        ResultSet rs;
        Student stu = null;
        rs = dbHandler.query(sql, id);
        if (rs.next()) {
           stu = new Student();
           stu.setId(rs.getInt("id"));
           stu.setName(rs.getString("name"));
           stu.setGpa(rs.getDouble("gpa"));
       }
        return stu;
        
    }
    
}
