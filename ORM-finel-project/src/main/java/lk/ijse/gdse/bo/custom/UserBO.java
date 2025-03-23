package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.UsersDTO;
import lk.ijse.gdse.entity.Users;

import java.util.List;


public interface UserBO extends SuperBO {
    boolean save(UsersDTO users) throws Exception;
    List<UsersDTO> getAll() throws Exception;
    UsersDTO searchRole(UsersDTO usersDto) throws Exception;
    boolean update(UsersDTO usersDTO) throws Exception;
    String generateNewUserIdByRole(String role);
    void delete(String text) throws Exception;

}
