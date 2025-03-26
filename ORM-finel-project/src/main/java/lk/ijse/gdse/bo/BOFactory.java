package lk.ijse.gdse.bo;


import lk.ijse.gdse.bo.custom.impl.Therapi_ProgameBOImpl;
import lk.ijse.gdse.bo.custom.impl.TherapistBOImpl;
import lk.ijse.gdse.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse.bo.custom.impl.PatientBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        USER, PATIENT,THERAPIPROGRAM,THERAPIST,THERAPIST_PROGRAM
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
            default:
                return null;
        }
    }
}
