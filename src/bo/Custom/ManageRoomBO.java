package bo.Custom;


import dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO  {
    boolean add(RoomDTO roomDTO) throws Exception;
    List<RoomDTO> loadAllStudent() throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(String id)throws Exception;
}
