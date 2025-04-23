package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PaymentsDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    String getPaymentId(String id);

    boolean addPayment(PaymentsDTO paymentsDTO) throws Exception;

}
