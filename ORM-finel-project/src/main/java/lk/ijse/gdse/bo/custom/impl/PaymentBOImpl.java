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
            int num = Integer.parseInt(lastId.substring(3));
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

    @Override
    public Double getAmountDueByProgramId(String programId) {
        return (Double) paymentDAO.getAmountDueByProgramId(programId);
    }

    @Override
    public boolean deletePayment(String text) throws Exception {
        return paymentDAO.delete(text);
    }

    @Override
    public List<PaymentsDTO> getAll() throws Exception {
        List<Payments> payments = paymentDAO.getAll();
        List<PaymentsDTO> dtoList = new ArrayList<>();

        for (Payments p : payments) {
            dtoList.add(new PaymentsDTO(
                    p.getId(),
                    p.getPaymentDate(),
                    p.getAmountDUE(),
                    p.getAmountPaid(), p.getSessionId(), p.getPatientId(), p.getTherapistId(), p.getProgramId(), p.getProgramAmount()
            ));
        }

        return dtoList;
    }

}
