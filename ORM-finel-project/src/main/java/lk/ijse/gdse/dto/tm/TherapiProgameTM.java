package lk.ijse.gdse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TherapiProgameTM {
    private String id;
    private String name;
    private String duration;
    private int fee;

}
