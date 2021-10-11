package JDBCex5;

import java.sql.SQLException;
import utilities.DatabaseDriver;
import utilities.DatabaseHandler;

public class JDBCex5Connect {
    static Student stu1 = new Student(1, "Mia", 3.47);
    static Student stu2 = new Student(2, "Mercy", 3.21);
    static Student stu3 = new Student(3, "Mark", 2.89);
    static Student stu4 = new Student(4, "Watson", 2.75);
    static Student stu5 = new Student(5, "Kloby", 3.11);
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Start();
    }
    
    static void Start()throws SQLException, ClassNotFoundException, InterruptedException{
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/Student";
        String user = "Student";
        String passwd = "st";
        
        DatabaseDriver dbDriver = new DatabaseDriver(driver, url, user, passwd);
        DatabaseHandler dbHandler = new DatabaseHandler(dbDriver);
        
        InsertTest(dbHandler);
        Thread.sleep(4000);
        UpdateTest(dbHandler);
        Thread.sleep(4000);
        DeleteRowTest(dbHandler);
        Thread.sleep(4000);
        DeleteAlldataTest(dbHandler);
        dbHandler.closeConnection();
    }
    
    static void InsertTest(DatabaseHandler dbHandler){
        try {
            StudentTable.InsertStudent(dbHandler, stu1);
            StudentTable.InsertStudent(dbHandler, stu2);
            StudentTable.InsertStudent(dbHandler, stu3);
            StudentTable.InsertStudent(dbHandler, stu4);
            StudentTable.InsertStudent(dbHandler, stu5);
            System.out.println("Insert Successful!!");
        } catch(Exception e){
            System.out.println("Something wrong!!");
            e.printStackTrace();
        }
    }
    
    static void UpdateTest(DatabaseHandler dbHandler) throws SQLException{
        Student stu = StudentTable.findStudentById(dbHandler, 1);
        stu.setName("Mark");
        stu.setGpa(2.44);
        StudentTable.updateStudent(dbHandler, stu);
        System.out.println("Update Complete!!");
    }
    
    static void DeleteRowTest(DatabaseHandler dbHandler){
        try {
            StudentTable.removeStudent(dbHandler, stu1);
            StudentTable.removeStudent(dbHandler, stu3);
            StudentTable.removeStudent(dbHandler, stu5);
            System.out.println("Remove Successful!!");
        } catch(Exception e){
            System.out.println("Something wrong!!");
            e.printStackTrace();
        }
    }
    
    static void DeleteAlldataTest(DatabaseHandler dbHandler){
        StudentTable.removeAllData(dbHandler);
        System.out.println("Remove Successful!!");
    }
}
