package dao.Custom.impl;

import dao.Custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String room_id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, room_id);
        session.delete(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String rid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, rid);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Room> roomList = null;
        Query sessionQuery = session.createQuery("from Room ");
        roomList = sessionQuery.list();
        transaction.commit();
        session.close();
        return roomList;
    }
    @Override
    public List getRoomIds() throws IOException {
        List<String> rmIds = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query sessionQuery = session.createQuery("SELECT room_id FROM Room");
        rmIds=sessionQuery.list();
        transaction.commit();
        session.close();
        return rmIds;
    }
}
