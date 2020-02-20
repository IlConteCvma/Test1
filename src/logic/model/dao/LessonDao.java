package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import execption.EntityNotFoundException;
import logic.Session;
import logic.model.Lesson;
import logic.model.SingletonConnectionDB;
import logic.model.queries.LessonQueries;

public class LessonDao {
	
	private LessonDao() {
		throw new IllegalStateException("Utility class");
	}
	

	 public static Lesson getNextLesson() throws SQLException, EntityNotFoundException {
		 Lesson nextLesson = null;
		 int id = findIdNextLesson();
		 if(id != 0) {
			 nextLesson = getLessonById(id);
			 return nextLesson;
		 }
		 else {
			 throw new EntityNotFoundException("Lesson");
		 }

	 }
	 
	 
	 private static Lesson getLessonById(int idLesson) throws SQLException {
	        
		 	Statement stmt = null;
	        Connection conn = null;
	        Lesson nextLesson = null;
	        
	        try {
	        	//create connection
	        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
	        	if (conn== null) {
					throw new SQLException();
				}
	        	//create statement
	        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	//execute query
	            ResultSet rs = LessonQueries.selectInfoLessonById(stmt,idLesson);
	            //check if a returned zero value
	            if (rs.first()){
	            	//returned one value
	            	rs.first();
	            	Time startHour = rs.getTime("OraInizio");
	            	Time endHour = rs.getTime("OraFine");
	            	int dayOfWeek = rs.getInt("Giorno");
	            	nextLesson = new Lesson(startHour,endHour,dayOfWeek);
	            	
	            	nextLesson.setRoomLesson(RoomDao.getRoomOfLesson(idLesson));
	            	
	            	nextLesson.setSubjectLesson(SubjectDao.getSubjectByLesson(idLesson));
	            }
	            rs.close();
	            } finally {     
	            	if(stmt != null){
	            		stmt.close();
	            	}
	            }
	        	return nextLesson;
	    }
	 
	 private static int findIdNextLesson() throws SQLException {
	        
		 	Statement stmt = null;
	        Connection conn = null;
	        int id;
	        
	        try {
	        	//create connection
	        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
	        	if (conn== null) {
					throw new SQLException();
				}
	        	//create statement
	        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	//execute query
	            ResultSet rs = LessonQueries.selectNextLesson(stmt,Session.getSession().getStudent().getUsername() );
	            //check if a returned zero value
	            if (!rs.first()){
	            	id = 0; //lesson not found
	            }else {
	            	//returned one value
	            	rs.first();
	            	id = rs.getInt("ID");
	            }
	            rs.close();
	            } finally {     
	            	if(stmt != null){
	            		stmt.close();
	            	}
	            }
	        	return id;
	    }
	 
}
