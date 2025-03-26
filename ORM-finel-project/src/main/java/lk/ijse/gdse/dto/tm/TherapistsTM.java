package lk.ijse.gdse.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TherapistsTM {
    private String id;
    private String name;
    private String specialization;
    private String contact;
    private String status;
}
