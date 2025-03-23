package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.UsersDTO;
import lk.ijse.gdse.entity.Users;

import java.util.List;

public interface UserDAO extends CrudDAO<Users> {
    String getLastUserIdByRole(String rolePrefix);
}
