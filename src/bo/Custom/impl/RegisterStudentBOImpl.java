package bo.Custom.impl;

import bo.Custom.RegisterStudentBO;
import dao.Custom.RoomDAO;
import dao.Custom.StudentDAO;
import dao.Custom.impl.ReservationDAOImpl;
import dao.Custom.impl.RoomDAOImpl;
import dao.Custom.impl.StudentDAOImpl;
import dto.RoomReservationDTO;
import entity.Room;
import entity.RoomReservation;
import entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterStudentBOImpl implements RegisterStudentBO {
    @Override
    public List getStudentIds() throws IOException {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.getStudentIds();

    }

    @Override
    public List getRoomIds() throws IOException {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.getRoomIds();
    }

    @Override
    public Student getStudent(String id) throws Exception {
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.find(id);
    }

    @Override
    public Room getRoom(String id) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.find(id);
    }

    @Override
    public String generateNewReservationID() throws SQLException, ClassNotFoundException, IOException {
        ReservationDAOImpl roomReservationDAO = new ReservationDAOImpl();
        return roomReservationDAO.generateNewId();
    }

    @Override
    public boolean addReservation(RoomReservationDTO reservationDTO) throws Exception {
        ReservationDAOImpl roomReservationDAO = new ReservationDAOImpl();
        return roomReservationDAO.add(new RoomReservation(
                reservationDTO.getRes_id(),
                reservationDTO.getDate(),
                new Student(reservationDTO.getStudent_id()) ,
                new Room(reservationDTO.getRoom_id()),
                reservationDTO.getStatus()
        ));
    }
}
