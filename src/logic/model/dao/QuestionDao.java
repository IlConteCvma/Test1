package logic.model.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import execption.QuestionException;
import logic.model.Question;
import logic.model.QuestionExercise;

import logic.model.QuestionProblem;
import logic.model.QuestionType;
import logic.model.SingletonConnectionDB;
import logic.model.queries.QuestionQueries;


public class QuestionDao {
	protected static Statement stmt;    
    private QuestionDao() {
        throw new IllegalStateException("Utility class");
      }
   
    
	public static int getNewId() throws SQLException {
		int count;
		Connection conn = null;
		try {
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if(conn==null) {
        		throw new SQLException();
        	}
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
		ResultSet rs = QuestionQueries.getId(stmt);
		
		  if (!rs.first()){
			  count = 0;
          }else {
          	//returned one value
          	rs.first();
          	count = rs.getInt("count");
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
      	return count+1;
		

	}
	
	public static void saveOnDB(Question question,QuestionType type) throws QuestionException  {
		
		

		String text;
		Connection conn = null;
		
		
		try {
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
			if(conn==null) {
        		throw new SQLException();
        	}
            stmt = conn.createStatement();
    
           
		}
		catch(SQLException e) {
			throw new QuestionException(e.getMessage());
		}
			
		
		try {
			text = (String) question.getClass().getMethod("getText").invoke(question);
			
		}
		catch(ReflectiveOperationException e) {
			throw new QuestionException("Error on invoke for text");
		}
		
		switch(type) {
			
			case PROBLEM:
				
				try {
				QuestionQueries.saveQuestion(stmt,question ,text, null,type.toString());
				}
				catch(SQLException e) {
					throw new QuestionException(e.getMessage());
					
				}
				break;
				
			case EXERCISE:
				
				String image;
				try {
					image = (String) question.getClass().getMethod("getImage").invoke(question);
					
				}
				catch(ReflectiveOperationException e) {
					throw new QuestionException("Error on invoke for image");
				}
				try {
				QuestionQueries.saveQuestion(stmt,question, text, image,type.toString());
				}
				catch(SQLException e) {
					throw new QuestionException(e.getMessage());
				}
				break;
				
				default:
					throw new QuestionException("Error in question type");
				
				
		}
		
		
		
		
	}
	
	public static List<Question> getQuestionsOfStudent(String student) throws SQLException, ReflectiveOperationException{
		
		Statement stmt = null;
        Connection conn = null;
        List<Question> quest;
		
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = QuestionQueries.getQuestions(stmt,student);
            
            if (!rs.first()){
            	quest = null;
            }else {
            	//returned one value
            	rs.first();
            	quest = new ArrayList<>();
            	
            	do{
            		//Make question 
            		Question q;
            		
            		if(rs.getString("Tipo").equals("EXERCISE") ) {
            			String body = rs.getString("Testo");
            			String image = rs.getString("Immagine");
            			q = new QuestionExercise();
            			q.getClass().getMethod("setText", String.class).invoke(q, body);
            			q.getClass().getMethod("setImage", String.class).invoke(q, image);
            		}
            		else {
            			String body = rs.getString("Testo");
            			q = new QuestionProblem();
            			q.getClass().getMethod("setText", String.class).invoke(q, body);
            			
            		}

            		q.setId(rs.getInt("ID"));
            		q.setTitle(rs.getString("Titolo"));
            		q.setSolved(rs.getBoolean("Risolto"));
            		q.setStudent(StudentDao.findStudent(rs.getString("Studente")));
            		q.setQuestionSub(SubjectDao.getSubjectByName(rs.getString("Materia")));

                    quest.add(q);
                    
                    
                }while(/*Volendo ci metti una variabili i &&*/ rs.next());
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
        
		return quest;
		
	}

}
