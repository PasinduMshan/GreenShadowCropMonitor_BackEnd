package lk.ijse.GreenShadowCropMonitor_BackEnd.dao;

import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity, String> {

}
