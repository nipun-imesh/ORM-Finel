package lk.ijse.gdse.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class PatientsDTO {
   private String id;
   private String name;
   private String contact;
   private Date regDate;

   public PatientsDTO(String id, String name, String contact, Date regDate) {
       this.id = id;
       this.name = name;
       this.contact = contact;
       this.regDate = regDate;
   }

    public PatientsDTO(String id, String name, String contact, String date) {
       this.id = id;
       this.name = name;
       this.contact = contact;
       this.regDate = Date.valueOf(date);
    }


}
