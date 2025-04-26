package lk.ijse.gdse.config;

import lk.ijse.gdse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties")) {

            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load hibernate.properties", e);
        }

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        MetadataSources metadataSources = new MetadataSources(registry)
                .addAnnotatedClass(Users.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Therapy_Program.class)
                .addAnnotatedClass(Therapist_Program_Assign.class)
                .addAnnotatedClass(Therapists.class)
                .addAnnotatedClass(TherapySessions.class)
                .addAnnotatedClass(Payments.class)
                .addAnnotatedClass(Invoice.class);

        Metadata metadata = metadataSources.getMetadataBuilder().build();

        // Step 4: Create sessionFactory using metadata
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    // Singleton pattern to get an instance of FactoryConfiguration
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    // Get session from session factory
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
