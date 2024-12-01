package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.AuthenticationService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.JWTService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.UserDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.SignInDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.UserEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.AlreadyExistsException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.UserNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.secure.JWTAuthResponse;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;;

    @Override
    public JWTAuthResponse signUp(UserDTO userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = mapping.toUserEntity(userDto);
        if (userDao.existsById(userDto.getEmail())) throw new AlreadyExistsException("user already exists");
        userDao.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return JWTAuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public JWTAuthResponse signIn(SignInDTO signInDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword())
        );
        UserEntity userEntity = userDao.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(userEntity);
        return JWTAuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String token) {
        String email = jwtService.extractUserName(token);
        UserEntity userEntity = userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(userEntity);
        return JWTAuthResponse.builder().token(jwtToken).build();
    }
}
