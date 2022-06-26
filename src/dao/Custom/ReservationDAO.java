package dao.Custom;

import dao.CrudDAO;
import entity.RoomReservation;

import java.io.IOException;
import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<RoomReservation,String> {
    String generateNewId() throws SQLException, ClassNotFoundException, IOException;
    String generateRoomAvailableStatus(String id) throws SQLException, ClassNotFoundException, IOException;
}
