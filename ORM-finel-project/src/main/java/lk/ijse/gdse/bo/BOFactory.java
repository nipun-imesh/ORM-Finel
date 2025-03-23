package lk.ijse.gdse.bo;

import lk.ijse.gdse.bo.custom.impl.UserBOImpl;
import lk.ijse.gdse.bo.custom.impl.PatientBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        USER, PATIENT
    }

    public SuperBO getBO( BOType type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            case PATIENT:
                return new PatientBOImpl();
            default:
                return null;
        }
    }
}
