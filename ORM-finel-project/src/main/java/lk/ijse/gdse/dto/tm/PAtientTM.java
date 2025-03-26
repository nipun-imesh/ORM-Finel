package lk.ijse.gdse.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@Setter
@Getter


public class PAtientTM {
    private String id;
    private String name;
    private String contact;
    private Date regDate;


    public PAtientTM(String id, String name, String contact, Date regDate) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.regDate = regDate;
    }
}
