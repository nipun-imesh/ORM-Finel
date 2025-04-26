package lk.ijse.gdse.bo;


import lk.ijse.gdse.bo.custom.impl.*;
import lk.ijse.gdse.dao.custom.impl.TherapiSassionDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
    USER, PATIENT,THERAPIPROGRAM,THERAPIST,THERAPIST_PROGRAM,THERAPISASSION,PAYMENT,INVOICE
    }

    public SuperBO getBO( BOType type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            case PATIENT:
                return new PatientBOImpl();
            case THERAPIPROGRAM:
                return  new Therapi_ProgameBOImpl();
            case THERAPIST:
                return new TherapistBOImpl();
            case THERAPISASSION:
                return new TherapisassionBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case INVOICE:
                return new InvoiceBOImpl();
            default:
                return null;
        }
    }
}
