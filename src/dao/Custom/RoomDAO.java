package dao.Custom;

import dao.CrudDAO;
import entity.Room;


import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {

        List getRoomIds() throws IOException;
}
