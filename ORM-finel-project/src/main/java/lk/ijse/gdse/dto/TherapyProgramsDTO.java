package lk.ijse.gdse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapyProgramsDTO {
    private String id;
    private String name;
    private String duration;
    private double fee;

    public TherapyProgramsDTO(String programName, double programFee) {
        this.name = programName;
        this.fee =  programFee;
    }
}
