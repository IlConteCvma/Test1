package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.SingletonConnectionDB;
import logic.model.Subject;
import logic.model.queries.SubjectQueries;

public class SubjectDao {
	
	private static final String INDEX = "Indice";
	private static final String ABBREVATION = "Sigla";
	
	public Subject getSubjectLesson(int idLesson)  throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        Subject subjectOfLesson;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = SubjectQueries.findSubjectOfLesson(stmt, idLesson);
            //check if a returned zero value
            if (!rs.first()){
            	subjectOfLesson = null;
            }else {
            	//returned one value
            	rs.first();
            	
            	String name = rsGetString(rs,"Materia");
            	String abbreviation = rsGetString(rs,ABBREVATION);
            	double index = rsGetDouble(rs,INDEX);
            	
            	subjectOfLesson = new Subject(name, abbreviation, index);
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	if (conn != null) {
    				SingletonConnectionDB.close();
    			}
            }
        	return subjectOfLesson;
    }
	
	public static List<Subject> getSubjectOfStudent(String username) throws SQLException{
		
		Statement stmt = null;
        Connection conn = null;
        List<Subject> subject;
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = SubjectQueries.findSubjectOfStudent(stmt, username);
            
            if (!rs.first()){
            	subject = null;
            }else {
            	//returned one value
            	rs.first();
            	subject = new ArrayList<>();
            	do{
            		
            		String name = rsGetString(rs,"Materia");
                	String abbreviation = rsGetString(rs,ABBREVATION);
                	double index = rsGetDouble(rs,INDEX);
                	
                    Subject sub = new Subject(name,abbreviation,index);
                   
                    subject.add(sub);

                }while(rs.next());
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	if (conn != null) {
    				SingletonConnectionDB.close();
    			}
            	
            }
		
		return subject;
		
	}
	
	public static Subject getSubjectByName(String name) throws SQLException {
		Statement stmt = null;
        Connection conn = null;
        Subject subject;
		
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = SubjectQueries.findSubjectByName(stmt, name);
            //check if a returned zero value
            if (!rs.first()){
            	subject = null;
            }else {
            	//returned one value
            	rs.first();
            	String nm = rsGetString(rs,"Nome");
            	String abbreviation = rsGetString(rs,ABBREVATION);
            	double index = rsGetDouble(rs,INDEX);
            	
            	subject = new Subject(nm, abbreviation, index);
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	if (conn != null) {
    				SingletonConnectionDB.close();
    			}
            }
        	return subject;
		
	}
	
	private static String rsGetString(ResultSet rs,String columnLabel) throws SQLException {
		return rs.getString(columnLabel);
	}
	private static double rsGetDouble(ResultSet rs,String columnLabel) throws SQLException {
		return rs.getDouble(columnLabel);
	}
}
