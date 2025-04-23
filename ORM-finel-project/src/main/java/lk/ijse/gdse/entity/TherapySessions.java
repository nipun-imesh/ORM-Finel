package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TherapySessions {

    @Id
    private String sessionId;
    private String sessionDate;
    private String patientId;
    private String patientName;
    private String therapistId;
    private String programId;
    private String programName;
    private double programFee;


    public TherapySessions(String sessionid, String patientId, String patientName, String programId, String programName, double programFee, String therapistId, String sessinDate) {
        this .sessionId = sessionid;
        this.patientId = patientId;
        this.patientName = patientName;
        this.programId = programId;
        this.programName = programName;
        this.programFee = programFee;
        this.therapistId = therapistId;
        this.sessionDate = sessinDate;
    }

    public TherapySessions(String sessionId) {
        this.sessionId = sessionId;
    }
}


