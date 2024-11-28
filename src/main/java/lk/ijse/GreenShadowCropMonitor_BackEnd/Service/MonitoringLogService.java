package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.MonitoringLogEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitorLog(MonitoringLogDTO monitoringLogServiceDTO) throws DataPersistException;
    List<MonitoringLogDTO> getAllMonitorLog();
    MonitoringLogStatus getMonitorLog(String logCode);
    void deleteMonitorLog(String logCode);
    void updateMonitorLog(String logCode, MonitoringLogDTO monitoringLogServiceDTO);
    List<MonitoringLogEntity> getLogServicesEntityList(List<MonitoringLogDTO> LogServiceDTOS);
}
