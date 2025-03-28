package lk.ijse.gdse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Therapists {
    @Id
    private String id;
    private String name;
    private String specialization;
    private String contact;
    private String status;

    @OneToMany(mappedBy = "therapist")
    private List<Therapist_Program_Assign> therapist_programs;

    public Therapists(String id) {
        this.id = id;
    }

    public Therapists(String id, String name, String specialization, String contact, String status) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.status = status;
    }
}
