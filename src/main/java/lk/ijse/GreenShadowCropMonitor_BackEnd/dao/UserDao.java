package lk.ijse.GreenShadowCropMonitor_BackEnd.dao;

import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
