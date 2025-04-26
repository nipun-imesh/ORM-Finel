package lk.ijse.gdse.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Invoice {

    @Id
    private String patientId;
    private String patientName;
    private String programName;
    private String sessionId;
    private String sessionDate;
    private String paymantDate;
    private double programFee;
    private String payDate;

    public Invoice(String patientId, String programName, String sessionId, String sessionDate, double programFee) {
        this.patientId = patientId;
        this.programName = programName;
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.programFee = programFee;
    }
}
