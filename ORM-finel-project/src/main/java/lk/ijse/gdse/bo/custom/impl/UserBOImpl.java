package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.dto.UsersDTO;
import lk.ijse.gdse.entity.Users;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean save(UsersDTO users) throws Exception {
       return userDAO.save(new Users( users.getId(),users.getUserName(), users.getPassword(),users.getRole()));
    }

    @Override
    public List<UsersDTO> getAll() throws Exception {
        List<UsersDTO> users = new ArrayList<>();
        List<Users> all = userDAO.getAll();
        for (Users user: all) {
            users.add(new UsersDTO(user.getId(),user.getUsername(), user.getPassword(), user.getRole()));
        }
        return users;
    }

    @Override
    public UsersDTO searchRole(UsersDTO usersDto) throws Exception {
        Users user = userDAO.search(usersDto.getUserName());
        if (user != null && BCrypt.checkpw(usersDto.getPassword(), user.getPassword())) {
            return new UsersDTO(user.getUsername(),user.getPassword(),user.getRole());
        } else {
            return null;
        }
    }

    @Override
    public boolean update(UsersDTO users) throws Exception {
        return userDAO.update(new Users( users.getId(),users.getUserName(), users.getPassword(),users.getRole()));
    }


    @Override
    public String generateNewUserIdByRole(String role) {
        String prefix = "";
        if (role.equalsIgnoreCase("admin")) {
            prefix = "AD";
        } else if (role.equalsIgnoreCase("reception")) {
            prefix = "RE";
        }

        String lastId = userDAO.getLastUserIdByRole(prefix);

        if (lastId != null) {
            int lastNumber = Integer.parseInt(lastId.substring(2));
            int newNumber = lastNumber + 1;
            return String.format("%s%03d", prefix, newNumber);
        } else {
            return prefix + "001";
        }
    }

    @Override
    public void delete(String text) throws Exception {
        userDAO.delete(String.valueOf(new Users(text)));
    }




}
