package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapiSassionTM {
    private String sessionid;
    private String patientId;
    private String patientName;
    private String therapistId;
    private String programId;
    private String programName;
    private double programFee;
    private String sessinDate;
    private double downPayment;

    public TherapiSassionTM(String sessionid, String patientId, String therapistId, String programId, String sessinDate) {
        this.sessionid = sessionid;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.programId = programId;
        this.sessinDate = sessinDate;
    }
}
