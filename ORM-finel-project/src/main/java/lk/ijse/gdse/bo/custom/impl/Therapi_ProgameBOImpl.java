package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapiProgameBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapiProgameDAO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;
import lk.ijse.gdse.entity.Therapy_Program;

import java.util.ArrayList;
import java.util.List;


public class Therapi_ProgameBOImpl implements TherapiProgameBO {
    TherapiProgameDAO therapiProgameDAO = (TherapiProgameDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIPROGAM);


    @Override
    public void save(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        therapiProgameDAO.save(new Therapy_Program(therapyProgramsDTO.getId(),therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(),therapyProgramsDTO.getFee()));
    }

    @Override
    public void delete(String id) throws Exception {
        therapiProgameDAO.delete(new Therapy_Program(id));
    }

    @Override
    public void update(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        therapiProgameDAO.update(new Therapy_Program(therapyProgramsDTO.getId(),therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(),therapyProgramsDTO.getFee()));
    }

    @Override
    public List<TherapyProgramsDTO> getAll() throws Exception{
        List<Therapy_Program> all = therapiProgameDAO.getAll();
        List<TherapyProgramsDTO> therapyProgramsDTOS = new ArrayList<>();
        for (Therapy_Program therapy_Program : all) {
            therapyProgramsDTOS.add(new TherapyProgramsDTO(therapy_Program.getId(),therapy_Program.getName(),therapy_Program.getDuration(),therapy_Program.getFee()));
        }
        return therapyProgramsDTOS;
    }
}

