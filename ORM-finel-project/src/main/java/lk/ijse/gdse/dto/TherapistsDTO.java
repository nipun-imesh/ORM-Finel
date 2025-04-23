package lk.ijse.gdse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter

public class TherapistsDTO {
    private String id;
    private String name;
    private String specialization;
    private String contact;
    private String programId;
    private String status;

    public TherapistsDTO(String id, String name, String specialization, String contact, String programId, String status) {

        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.programId = programId;
        this.status = status;
    }

    public TherapistsDTO(String id, String name, String specialization, String contact, String status) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.status = status;
    }

    public TherapistsDTO(String programId) {
        this.programId = programId;
    }


}
