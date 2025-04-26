package lk.ijse.gdse.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvoiceDTO {

    private String patientId;
    private String patientName;
    private String programName;
    private String sessionId;
    private String sessionDate;
    private String paymantDate;
    private double programFee;
    private String payDate;



}
