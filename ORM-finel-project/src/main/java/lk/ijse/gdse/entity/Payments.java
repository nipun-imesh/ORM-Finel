package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payments {

    @Id
    private String id;
    private String paymentDate;
    private double amountDUE;
    private double amountPaid;
    private String sessionId;
    private String patientId;
    private String therapistId;
    private String programId;
    private String programAmount;
}
