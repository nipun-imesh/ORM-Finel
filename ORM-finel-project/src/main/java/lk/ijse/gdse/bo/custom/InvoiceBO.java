package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.InvoiceDTO;

public interface InvoiceBO extends SuperBO {
    void save(InvoiceDTO invoiceDTO) throws Exception;
}
