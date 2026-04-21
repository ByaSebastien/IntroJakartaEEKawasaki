package be.bstorm.introjakartaee.models;

import be.bstorm.introjakartaee.models.enums.UserRole;
import lombok.*;

@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Getter
    private Integer id;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private UserRole role;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
