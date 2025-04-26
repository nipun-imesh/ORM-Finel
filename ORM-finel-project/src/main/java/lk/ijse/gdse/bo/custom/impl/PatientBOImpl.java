package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PatientBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.entity.Patient;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public boolean save(PatientsDTO patientsDTO) throws Exception {
        Session session = null;
        return patientDAO.save(new Patient(patientsDTO.getId(),patientsDTO.getName(),patientsDTO.getContact(),patientsDTO.getRegDate()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return patientDAO.delete(String.valueOf(new Patient(id)));
    }

    @Override
    public boolean update(PatientsDTO patientsDTO) throws Exception {
        return patientDAO.update(new Patient(patientsDTO.getId(),patientsDTO.getName(),patientsDTO.getContact(),patientsDTO.getRegDate()));
    }

    @Override
    public List<PatientsDTO>  getAll() throws Exception {
        List<PatientsDTO> users = new ArrayList<>();
        List<Patient> all = patientDAO.getAll();
        for (Patient patient: all) {
            users.add(new PatientsDTO(patient.getId(),patient.getName(), patient.getContact(), patient.getRegDate()));
        }
        return users;
    }

    @Override
    public String getPatientId() throws Exception {
        String lastId = patientDAO.getPatientId();
        if (lastId == null) {
            return "PA001";
        } else {
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("PA%03d", num);
        }
    }

    @Override
    public String getCurrentPatientId(String patientId) {
        return patientDAO.getCurrentPatientId(patientId);
    }
}
