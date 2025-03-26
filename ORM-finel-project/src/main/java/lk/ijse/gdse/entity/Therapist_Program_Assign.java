package lk.ijse.gdse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Therapist_Program_Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long assignId;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapists therapist;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Therapy_Program program;


    public Therapist_Program_Assign(Therapists therapists, Therapy_Program therapyProgram) {
    }
}
