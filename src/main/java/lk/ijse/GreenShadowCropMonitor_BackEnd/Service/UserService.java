package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.UserStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO) throws DataPersistException;
    List<UserDTO> getAllUsers();
    UserStatus getUser(String email);
    void deleteUser(String email);
    void updateUser(String email,UserDTO userDTO);
    UserDetailsService userDetailsService();
}
