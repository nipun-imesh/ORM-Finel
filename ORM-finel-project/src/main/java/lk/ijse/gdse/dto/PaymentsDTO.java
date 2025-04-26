package lk.ijse.gdse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PaymentsDTO {

    private String id;
    private String paymentDate;
    private double amountDUE;
    private double amountPaid;
    private String sessionId;
    private String patientId;
    private String therapistId;
    private String programId;
    private String programAmount;


    public PaymentsDTO(String id, String paymentDate, double amountDUE, double amountPaid, String sessionId, String patientId, String therapistId, String programId, String programAmount) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amountDUE = amountDUE;
        this.amountPaid = amountPaid;
        this.sessionId = sessionId;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.programId = programId;
        this.programAmount = programAmount;
    }


    public PaymentsDTO(String programAmount, double amountDUE) {
        this.programAmount = programAmount;
        this.amountDUE = amountDUE;
    }

    public PaymentsDTO(String patientId, String patientName, String programId, String sessionId, String sessionDate, String paymentDate, String fee, String payDate, String notAvailable) {
    }
}
