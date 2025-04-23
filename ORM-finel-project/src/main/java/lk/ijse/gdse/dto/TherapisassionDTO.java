package lk.ijse.gdse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TherapisassionDTO {

    private String sessionid;
    private String patientId;
    private String patientName;
    private String therapistId;
    private String programId;
    private String programName;
    private double programFee;
    private String sessinDate;
    private double downPayment;

    public TherapisassionDTO(String patientId, String patientName, String programId, String programName, double fee, String therapistId) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.programId = programId;
        this.programName = programName;
        this.programFee = fee;
        this.therapistId = therapistId;
    }

    public TherapisassionDTO(String programName, double programFee) {
        this.programName = programName;
        this.programFee = programFee;
    }


    public TherapisassionDTO(String programName, String programFee) {
        this.sessionid = programName;
        this.patientId = programFee;
    }

    public TherapisassionDTO(String patientId, String patientName, String programId, String programName, double programFee, String therapistId, String sessionDate) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.programId = programId;
        this.programName = programName;
        this.programFee = programFee;
        this.therapistId = therapistId;
        this.sessinDate = sessionDate;
    }

    public TherapisassionDTO(String sessionId, String patientId, String programId, String therapistId, String sessionDate, String patientName, String programName, double v) {
        this.sessionid = sessionId;
        this.patientId = patientId;
        this.programId = programId;
        this.therapistId = therapistId;
        this.sessinDate = sessionDate;
        this.patientName = patientName;
        this.programName = programName;
        this.programFee =  v;
    }


    public TherapisassionDTO(String sessionId, String patientId, String patientName, String programId, String programName, double programFee, String therapistId, String sessionDate) {

        this.sessionid = sessionId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.programId = programId;
        this.programName = programName;
        this.programFee = programFee;
        this.therapistId = therapistId;
        this.sessinDate = sessionDate;
    }

    public TherapisassionDTO(String sessionId, String patientId, String programId, String therapistId, String sessionDate) {
        this.sessionid = sessionId;
        this.patientId = patientId;
        this.programId = programId;
        this.therapistId = therapistId;
        this.sessinDate = sessionDate;
    }

    public TherapisassionDTO(String therapistId) {
        this.therapistId = therapistId;
    }


    public TherapisassionDTO(double programFee, String sessionId, String sessionDate) {
        this.programFee = programFee;
        this.sessionid = sessionId;
        this.sessinDate = sessionDate;
    }
}

