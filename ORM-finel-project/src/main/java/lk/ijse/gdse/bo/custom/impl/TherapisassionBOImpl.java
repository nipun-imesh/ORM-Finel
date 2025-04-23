package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapisassionBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapiassionDAO;
import lk.ijse.gdse.dto.PaymentsDTO;
import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.dto.TherapistsDTO;
import lk.ijse.gdse.entity.Payments;
import lk.ijse.gdse.entity.Therapists;
import lk.ijse.gdse.entity.TherapySessions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TherapisassionBOImpl implements TherapisassionBO {

    TherapiassionDAO therapiassionDAO = (TherapiassionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPISASSION);


    @Override
    public List<TherapisassionDTO> therapiSassionGetUiData() throws Exception {
        List<TherapySessions> uiAllData = therapiassionDAO.getAll();
        List<TherapisassionDTO> therapySassionDTOS = new ArrayList<>();

        for (TherapySessions therapySessions : uiAllData) {
            therapySassionDTOS.add(new TherapisassionDTO(therapySessions.getPatientId(),therapySessions.getPatientName(),therapySessions.getProgramId(),therapySessions.getProgramName(),therapySessions.getProgramFee(),therapySessions.getTherapistId(),therapySessions.getSessionDate()));
        }

        return therapySassionDTOS;
    }

    @Override
    public String getPatientName(String id) {
       return therapiassionDAO.getPatientName(id);
    }

    @Override
    public String getTherapiSassionId(String id) {
        String lastId = therapiassionDAO.getId(id); // e.g., "THS003"
        if (lastId == null) {
            return "THS001";
        } else {
            try {
                String numericPart = lastId.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(numericPart);
                num++;
                return String.format("THS%03d", num);
            } catch (NumberFormatException e) {
                System.err.println("Invalid lastId format: " + lastId);
                return "THS001"; // fallback default
            }
        }
    }

    @Override
    public boolean savetherapisassion(TherapisassionDTO therapisassionDTO) throws Exception {
        return therapiassionDAO.save(new TherapySessions(
                therapisassionDTO.getSessionid(),
                therapisassionDTO.getPatientId(),
                therapisassionDTO.getPatientName(),
                therapisassionDTO.getProgramId(),
                therapisassionDTO.getProgramName(),
                therapisassionDTO.getProgramFee(),
                therapisassionDTO.getTherapistId(),
                therapisassionDTO.getSessinDate()));
    }

    @Override
    public List<TherapisassionDTO> getAll() throws Exception {
       List<TherapySessions> all = therapiassionDAO.getAll();
       List<TherapisassionDTO> therapySassionTMs = new ArrayList<>();
       for (TherapySessions therapySessions : all) {
           therapySassionTMs.add(new TherapisassionDTO(therapySessions.getSessionId(),therapySessions.getPatientId(),therapySessions.getPatientName(),therapySessions.getProgramId(),therapySessions.getProgramName(),therapySessions.getProgramFee(),therapySessions.getTherapistId(),therapySessions.getSessionDate()));
       }
       return therapySassionTMs;
    }

    @Override
    public boolean delete(String sessionId) throws Exception {
        return therapiassionDAO.delete(new TherapySessions(sessionId));
    }

    @Override
    public boolean updateTherapisassion(TherapisassionDTO therapisassionDTO) throws Exception {
        return therapiassionDAO.update(new TherapySessions(therapisassionDTO.getSessionid(),therapisassionDTO.getPatientId(),therapisassionDTO.getPatientName(),therapisassionDTO.getProgramId(),therapisassionDTO.getProgramName(),therapisassionDTO.getProgramFee(),therapisassionDTO.getTherapistId(),therapisassionDTO.getSessinDate()));
    }

    @Override
    public List<TherapisassionDTO> TherapistID(String patientId) {
        List<TherapisassionDTO> list = new ArrayList<>();
        List<TherapySessions> sessions = therapiassionDAO.getTherapistId(patientId);

        for (TherapySessions session : sessions) {
            list.add(new TherapisassionDTO(session.getTherapistId()));
        }
        return list;
    }

    @Override
    public List<TherapistsDTO> getProgramsByTherapistId(String therapistId) {
        List<TherapistsDTO> list = new ArrayList<>();
        List<String> sessions = therapiassionDAO.getProgramsByTherapistId(therapistId);

        // Use Set to avoid duplicates
        Set<String> uniquePrograms = new HashSet<>(sessions);

        for (String programId : uniquePrograms) {
            list.add(new TherapistsDTO(programId));
            System.out.println(programId + " <- unique program ID");
        }

        return list;
    }

    @Override
    public List<TherapisassionDTO> getProgramPaymentInfo(String programId) {
        List<TherapisassionDTO> list = new ArrayList<>();
        List<TherapySessions> payments = therapiassionDAO.getProgramPaymentInfo(programId);

        for (TherapySessions payment : payments) {
            list.add(new TherapisassionDTO(payment.getProgramFee(),payment.getSessionId(),payment.getSessionDate()));
        }
        return list;
    }
}

