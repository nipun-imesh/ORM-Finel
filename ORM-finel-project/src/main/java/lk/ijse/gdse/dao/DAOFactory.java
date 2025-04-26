package lk.ijse.gdse.dao;

import lk.ijse.gdse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
    USER,PATIENT,THERAPIPROGAM,THERAPIST,THERAPIST_PROGRAM_ASSIGN,THERAPISASSION,PAYMENT,INVOICE
    }
    public SuperDAO getDAO(DAOType type) {
        switch(type) {
            case USER:
                return new UserDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            case THERAPIPROGAM:
                return new TherapiPragameDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            case THERAPISASSION:
                return new TherapiSassionDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case INVOICE:
                return new InvoiceDAOImpl();
                default:
                return null;
        }
    }
}
