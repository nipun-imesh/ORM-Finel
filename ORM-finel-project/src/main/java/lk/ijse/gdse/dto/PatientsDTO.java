package lk.ijse.gdse.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PatientsDTO {
   private String id;
   private String name;
   private String contact;
   private Date regDate;
}
