package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor

@Getter
@Setter
public class Patient {
    @Id
    private String id;
    private String name;
    private String contact;
    private Date regDate;

    public Patient(String id) {
        this.id = id;
    }

    public Patient(String id, String name, String contact, Date regDate) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.regDate = regDate;
    }
}
