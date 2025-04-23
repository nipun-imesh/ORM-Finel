package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Payments;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payments> {

    String getId(String id);
}
