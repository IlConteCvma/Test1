package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao {	
	private static String USER = "root";
    private static String PASS = "";
    private static String DB_URL = "jdbc:mysql://localhost/maaldb";
    private static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static Student findStudentLog(String user, String psw) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        Student studLog;
        
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
    
            ResultSet rs = StudentQueries.selectStudent(stmt, user, psw);

            if (!rs.first()){ // rs empty
            	studLog = null;
            	System.out.println("username not found");
            }else {
            
            	// riposizionamento del cursore
            	rs.first();
            
            	// lettura delle colonne "by username"
            	String nome = rs.getString("Nome");
            	String cognome = rs.getString("Cognome");
            	String username = rs.getString("Username");
            	String password = rs.getString("Password");
	        
            	System.out.println("Lo studente e' "+ nome + " "+ cognome );
            	studLog = new Student(nome,cognome,username,password);
            }
            
            rs.close();
            
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            try {
                if (stmt != null) {
                	stmt.close();
                }       
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                	conn.close();
                }    
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
	
        return studLog;
    }
}
