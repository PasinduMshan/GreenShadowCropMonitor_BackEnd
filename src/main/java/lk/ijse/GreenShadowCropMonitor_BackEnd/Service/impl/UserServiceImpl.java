package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.UserService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.UserDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.UserStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.UserEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return List.of();
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

    }
}
