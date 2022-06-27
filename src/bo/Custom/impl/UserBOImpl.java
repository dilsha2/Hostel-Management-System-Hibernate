package bo.Custom.impl;

import bo.Custom.UserBO;
import dao.Custom.UserDAO;
import dao.DAOFactory;
import dto.UserDTO;
import entity.User;

import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public User getUserData(String id) throws Exception {
        return userDAO.find(id);
    }

    @Override
    public List<User> loadAllUser() throws Exception {
        return userDAO.findAll();
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(new User(
                userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getPassword()
        ));
    }

}
