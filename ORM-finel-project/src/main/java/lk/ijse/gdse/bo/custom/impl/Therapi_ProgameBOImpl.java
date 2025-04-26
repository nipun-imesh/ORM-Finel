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
    public boolean save(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        return therapiProgameDAO.save(new Therapy_Program(therapyProgramsDTO.getId(),therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(), (int) therapyProgramsDTO.getFee()));

    }

    @Override
    public boolean delete(String id) throws Exception {
         return therapiProgameDAO.delete(id);
    }

    @Override
    public boolean update(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        return therapiProgameDAO.update(new Therapy_Program(therapyProgramsDTO.getId(),therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(), (int) therapyProgramsDTO.getFee()));
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

    @Override
    public TherapyProgramsDTO getProgameNameAndFee(String value) {
        Therapy_Program therapyProgram = therapiProgameDAO.getProgameNaaneFEe(value);
        TherapyProgramsDTO therapyProgramsDTO = new TherapyProgramsDTO();
        therapyProgramsDTO.setId(therapyProgram.getId());
        therapyProgramsDTO.setName(therapyProgram.getName());
        therapyProgramsDTO.setFee(therapyProgram.getFee());
        therapyProgramsDTO.setDuration(therapyProgram.getDuration());

        return therapyProgramsDTO;
    }

    @Override
    public String getProgameId(String id) {
        String lastId = therapiProgameDAO.getId(id);
        if (lastId == null) {
            return "THP001";
        } else {
            int num = Integer.parseInt(lastId.substring(3));
            num++;
            return String.format("THP%03d", num);
        }
    }
}

