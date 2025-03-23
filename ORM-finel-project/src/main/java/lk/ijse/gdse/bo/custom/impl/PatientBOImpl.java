package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PatientBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.entity.Patient;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public boolean save(PatientsDTO patientsDTO) throws Exception {
        return patientDAO.save(new Patient(patientsDTO.getId(),patientsDTO.getName(),patientsDTO.getContact(),patientsDTO.getRegDate()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return patientDAO.delete(new Patient(id));
    }
}
