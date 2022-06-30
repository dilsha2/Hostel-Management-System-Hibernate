package bo.Custom.impl;

import bo.Custom.RegisterStudentBO;
import dao.Custom.ReservationDAO;
import dao.Custom.RoomDAO;
import dao.Custom.StudentDAO;
import dao.DAOFactory;
import dto.RoomReservationDTO;
import entity.Room;
import entity.RoomReservation;
import entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterStudentBOImpl implements RegisterStudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReservationDAO roomReservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM_RESERVATION);

    @Override
    public List getStudentIds() throws IOException {

        return studentDAO.getStudentIds();

    }

    @Override
    public List getRoomIds() throws IOException {

        return roomDAO.getRoomIds();
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return studentDAO.find(id);
    }

    @Override
    public Room getRoom(String id) throws Exception {
        return roomDAO.find(id);
    }

    @Override
    public String generateNewReservationID() throws SQLException, ClassNotFoundException, IOException {

        return roomReservationDAO.generateNewId();
    }

    @Override
    public boolean addReservation(RoomReservationDTO reservationDTO) throws Exception {
        return roomReservationDAO.add(new RoomReservation(
                reservationDTO.getRes_id(),
                reservationDTO.getDate(),
                new Student(reservationDTO.getStudent_id()),
                new Room(reservationDTO.getRoom_id()),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public String generateRoomAvailableStatus(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomReservationDAO.generateRoomAvailableStatus(id);
    }

}
