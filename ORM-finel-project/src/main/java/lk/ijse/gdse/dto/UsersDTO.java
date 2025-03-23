package lk.ijse.gdse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UsersDTO {
    String id;
    String userName;
    String password;
    String role;

    public UsersDTO(String username, String password, String role) {
        this.userName = username;
        this.password = password;
        this.role = role;
    }

    public UsersDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UsersDTO(String id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
