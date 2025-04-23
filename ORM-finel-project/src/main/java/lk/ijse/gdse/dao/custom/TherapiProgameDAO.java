package lk.ijse.gdse.dao.custom;


import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Therapy_Program;

import java.util.List;

public interface TherapiProgameDAO extends CrudDAO<Therapy_Program> {

    Therapy_Program get(String programId);

    Therapy_Program getProgameNaaneFEe(String value);

    String getId(String id);
}
