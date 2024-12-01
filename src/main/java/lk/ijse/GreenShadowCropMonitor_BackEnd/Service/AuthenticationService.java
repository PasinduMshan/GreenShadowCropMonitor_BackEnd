package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.SignInDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.secure.JWTAuthResponse;

public interface AuthenticationService {
    JWTAuthResponse signUp(UserDTO userDto);
    JWTAuthResponse signIn(SignInDTO signInDto);
    JWTAuthResponse refreshToken(String token);
}
