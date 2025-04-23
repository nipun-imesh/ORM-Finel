package lk.ijse.gdse.config;

import lk.ijse.gdse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Users.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Therapy_Program.class)
                .addAnnotatedClass(Therapist_Program_Assign.class)
                .addAnnotatedClass(Therapists.class)
                .addAnnotatedClass(TherapySessions.class)
                .addAnnotatedClass(Payments.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ?
                factoryConfiguration =
                        new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
