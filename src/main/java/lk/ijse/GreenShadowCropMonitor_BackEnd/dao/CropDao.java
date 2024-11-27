package lk.ijse.GreenShadowCropMonitor_BackEnd.dao;

import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDao extends JpaRepository<CropEntity, String> {
}
