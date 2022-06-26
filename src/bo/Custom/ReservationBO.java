package bo.Custom;

import bo.SuperBO;
import entity.CustomEntity;
import entity.Room;

import java.io.IOException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    List getRoomIds() throws IOException;

    Room getRoomData(String id)throws Exception;

    List<CustomEntity> loadAllStudentDetails(String id) throws Exception;
}
