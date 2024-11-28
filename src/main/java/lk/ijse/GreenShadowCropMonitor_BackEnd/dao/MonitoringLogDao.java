package lk.ijse.GreenShadowCropMonitor_BackEnd.dao;

import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringLogDao extends JpaRepository<MonitoringLogEntity, String> {
}
