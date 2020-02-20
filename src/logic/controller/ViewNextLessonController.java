package logic.controller;

import java.sql.SQLException;

import execption.EntityNotFoundException;
import logic.model.Lesson;
import logic.model.dao.LessonDao;


public class ViewNextLessonController {
	
	public Lesson getNextLesson() throws SQLException, EntityNotFoundException {
		
		return LessonDao.getNextLesson();	
		
	}

}
