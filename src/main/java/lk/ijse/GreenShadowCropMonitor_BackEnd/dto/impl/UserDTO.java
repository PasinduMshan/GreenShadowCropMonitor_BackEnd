package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.UserStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserStatus {
    private String email;
    private String password;
    private Role role;
}
