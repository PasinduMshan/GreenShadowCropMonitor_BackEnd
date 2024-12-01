package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.AuthenticationService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.SignInDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.AlreadyExistsException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.secure.JWTAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<JWTAuthResponse> signUp(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(userDTO));
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignInDTO signInDTO) {
        return ResponseEntity.ok(authenticationService.signIn(signInDTO));
    }

    @PostMapping("/refresh/{token}")
    public ResponseEntity<JWTAuthResponse> refreshToken(@PathVariable("token") String token){
        return ResponseEntity.ok(authenticationService.refreshToken(token));
    }
}
