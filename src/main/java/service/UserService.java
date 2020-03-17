package service;

import model.User;
import service.dto.UserDTO;

public interface UserService {
    int insertUser(User user);

    boolean checkUsername(String username);

    boolean checkPassword(String username, String password);

    int changePass(String username, String password);

    UserDTO getUserByUserId(Integer userId);

    UserDTO getUserByUsername(String username);

    int deleteUser(String username, String password);

    int updateUser(User user);

    int selectRowCount();

    String login(String username, String password);
}