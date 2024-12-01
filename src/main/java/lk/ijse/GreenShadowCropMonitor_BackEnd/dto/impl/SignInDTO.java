package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO {
    private String email;
    private String password;
}

