	package logic.controller;

import java.sql.SQLException;

import logic.bean.SeatBean;
import logic.model.dao.SeatDao;

public class BookSeatController {
	
	public void occupateSeat(SeatBean seat) throws SQLException {	
		SeatDao.occupySeat(seat.getRoom().getName(), seat.getIndex());
	}
	
	public void freeSeat(SeatBean seat) throws SQLException {
		SeatDao.freeSeat(seat.getRoom().getName(), seat.getIndex());	
	}
	
}
