package bo.Custom;


import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO extends SuperBO {
    boolean add(RoomDTO roomDTO) throws Exception;
    List<RoomDTO> loadAllStudent() throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(String id)throws Exception;
}
