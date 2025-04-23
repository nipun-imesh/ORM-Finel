package lk.ijse.gdse.dao;

import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.entity.*;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(T t) throws Exception;
    List<T> getAll() throws Exception;
    Users search(String name) throws Exception;

}
