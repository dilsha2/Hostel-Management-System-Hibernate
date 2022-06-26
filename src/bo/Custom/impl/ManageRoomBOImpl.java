package bo.Custom.impl;

import bo.Custom.ManageRoomBO;
import dao.Custom.RoomDAO;
import dao.Custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class ManageRoomBOImpl implements ManageRoomBO {
    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.add(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public List<RoomDTO> loadAllStudent() throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO> roomsDto= new ArrayList<>();

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
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.delete(id);
    }

}
