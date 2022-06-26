package bo.Custom.impl;

import bo.Custom.ReservationBO;
import dao.Custom.QueryDAO;
import dao.Custom.RoomDAO;
import dao.DAOFactory;
import entity.CustomEntity;
import entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    @Override
    public List getRoomIds() throws IOException {
        return roomDAO.getRoomIds();
    }

    @Override
    public Room getRoomData(String id) throws Exception {

        return roomDAO.find(id);
    }

    @Override
    public List<CustomEntity> loadAllStudentDetails(String id) throws Exception {


        List<CustomEntity> studentDetails = queryDAO.getStudentDetails(id);

        ArrayList<CustomEntity> entityArrayList = new ArrayList<>();

        for (CustomEntity studentDetail : studentDetails) {
            entityArrayList.add(studentDetail);
        }
        return entityArrayList;
    }
}
