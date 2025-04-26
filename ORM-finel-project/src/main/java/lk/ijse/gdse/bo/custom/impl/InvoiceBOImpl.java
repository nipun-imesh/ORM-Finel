package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.InvoiceBO;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.InvoceDAO;
import lk.ijse.gdse.dto.InvoiceDTO;
import lk.ijse.gdse.entity.Invoice;

import java.util.List;

public class InvoiceBOImpl implements InvoiceBO {

    InvoceDAO invoiceDAO = (InvoceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVOICE);

    @Override
    public void save(InvoiceDTO invoiceDTO) throws Exception {
        invoiceDAO.save(new Invoice(invoiceDTO.getPatientId(),invoiceDTO.getProgramName(),invoiceDTO.getSessionId(),invoiceDTO.getSessionDate(),invoiceDTO.getProgramFee()));
    }
}
