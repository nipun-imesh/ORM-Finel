package lk.ijse.gdse.dto.tm;

import java.time.LocalDate;

public class PatientDTO {
    private int id;
    private String name;
    private String contact;
    private LocalDate regDate;

    public PatientDTO(int id, String name, String contact, LocalDate regDate) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.regDate = regDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}