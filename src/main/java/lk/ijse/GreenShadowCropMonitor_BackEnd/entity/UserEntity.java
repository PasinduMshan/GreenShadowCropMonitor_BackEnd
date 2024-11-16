package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private Role role;
}
