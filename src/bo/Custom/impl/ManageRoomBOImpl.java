package bo.Custom.impl;

import bo.Custom.ManageRoomBO;
import dao.Custom.RoomDAO;
import dao.Custom.impl.RoomDAOImpl;
import dao.DAOFactory;
import dto.RoomDTO;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class ManageRoomBOImpl implements ManageRoomBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {

        return roomDAO.add(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public List<RoomDTO> loadAllStudent() throws Exception {
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO> roomsDto = new ArrayList<>();

        for (Room room : all) {
            roomsDto.add(new RoomDTO(room.getRoom_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty()
            ));
        }
        return roomsDto;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

}
