package lk.ijse.gdse.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lk.ijse.gdse.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Users {
    @Id
    String id;
    String username;
    String password;
    String role;


    public Users( String id,String userName, String password, String role) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.role = role;
    }

    public Users(String id) {
        this.id = id;
    }


}


