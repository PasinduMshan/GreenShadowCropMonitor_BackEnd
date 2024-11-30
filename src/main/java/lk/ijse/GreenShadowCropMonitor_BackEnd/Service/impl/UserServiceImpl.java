package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.UserService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.UserDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.UserStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.UserEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.UserNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) throws DataPersistException {
        UserEntity save = userDao.save(mapping.toUserEntity(userDTO));
        if (save == null) {
            throw new DataPersistException("User not saved!");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.toUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String email) {
        return null;
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public void updateUser(String email, UserDTO userDTO) {
        Optional<UserEntity> userEntity = userDao.findById(email);
        if (!userEntity.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        } else {
            userEntity.get().setEmail(userDTO.getEmail());
            userEntity.get().setPassword(userDTO.getPassword());
            userEntity.get().setRole(userDTO.getRole());
        }
    }
}
