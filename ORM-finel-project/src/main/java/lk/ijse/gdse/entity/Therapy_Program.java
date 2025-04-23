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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Therapy_Program {
    @Id
    private String id;
    private String name;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program")
    private List<Therapist_Program_Assign> therapist_programs;

    public Therapy_Program(String id) {
        this.id = id;
    }

    public Therapy_Program(String id, String name, String duration, int fee) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
}
