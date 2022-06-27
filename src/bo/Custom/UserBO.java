package bo.Custom;

import bo.SuperBO;
import dto.UserDTO;
import entity.User;

import java.util.List;

public interface UserBO extends SuperBO {
    User getUserData(String id) throws Exception;

    List<User> loadAllUser() throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;
}