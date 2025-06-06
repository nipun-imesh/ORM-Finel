module ORM.finel.project {
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    requires javafx.controls;

    requires jakarta.persistence;
    requires java.naming;
    requires static lombok;
    requires spring.security.crypto;
    requires annotations;
    requires org.apache.pdfbox;
    opens lk.ijse.gdse.bo to javafx.fxml;
    exports lk.ijse.gdse.bo;

    opens lk.ijse.gdse.controller to javafx.fxml;
    exports lk.ijse.gdse;
    opens lk.ijse.gdse.entity to org.hibernate.orm.core;
    exports lk.ijse.gdse.entity;
    opens lk.ijse.gdse.dto.tm;
}