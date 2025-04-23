package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.PaymentBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.dto.PaymentsDTO;
import lk.ijse.gdse.entity.Payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String getPaymentId(String id) {
        String lastId = paymentDAO.getId(id);
        if (lastId == null) {
            return "PAY001";
        } else {
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("PAY%03d", num);
        }
    }

    @Override
    public boolean addPayment(PaymentsDTO paymentsDTO) throws Exception {
        double programAmount = Double.parseDouble(paymentsDTO.getProgramAmount());
        double amountPaid = paymentsDTO.getAmountPaid();

        double amountDue = programAmount - amountPaid;

        paymentsDTO.setAmountDUE(amountDue);

        return paymentDAO.save(new Payments(
                paymentsDTO.getId(),
                paymentsDTO.getPaymentDate(),
                paymentsDTO.getAmountDUE(),
                paymentsDTO.getAmountPaid(),
                paymentsDTO.getSessionId(),
                paymentsDTO.getPatientId(),
                paymentsDTO.getTherapistId(),
                paymentsDTO.getProgramId(),
                paymentsDTO.getProgramAmount()
        ));
    }
}
