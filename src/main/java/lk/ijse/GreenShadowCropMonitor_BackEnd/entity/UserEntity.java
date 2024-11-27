package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
