package lk.ijse.gdse.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsetTM {
    String id;
    String userName;
    String password;
    String role;

    public UsetTM(String id, String userName, String role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }
}
