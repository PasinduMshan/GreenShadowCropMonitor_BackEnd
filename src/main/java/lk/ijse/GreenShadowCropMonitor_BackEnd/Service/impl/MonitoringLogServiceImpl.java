package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.MonitoringLogService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.MonitoringLogDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.CropEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.MonitoringLogServiceEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private Mapping mapping;
    @Autowired
    FieldService fieldService;
    @Autowired
    StaffService staffService;
    @Autowired
    CropService cropService;
    @Autowired
    MonitoringLogDao monitoringLogDao;

    @Override
    public void saveMonitorLog(MonitoringLogDTO monitoringLogServiceDTO) throws DataPersistException {
        MonitoringLogServiceEntity saveLogDetail = monitoringLogDao.save(getMonitoringLogServiceEntity
                (monitoringLogServiceDTO));
        System.out.println("dddd" + saveLogDetail);
        if (saveLogDetail == null) {
            throw new DataPersistException("Log Detail not saved!");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitorLog() {
        return List.of();
    }

    @Override
    public MonitoringLogStatus getMonitorLog(String logCode) {
        return null;
    }

    @Override
    public void deleteMonitorLog(String logCode) {

    }

    @Override
    public void updateMonitorLog(String logCode, MonitoringLogDTO monitoringLogServiceDTO) {

    }

    @Override
    public List<MonitoringLogServiceEntity> getLogServicesEntityList(List<MonitoringLogDTO> LogServiceDTOS) {
        if (LogServiceDTOS == null || LogServiceDTOS.isEmpty()) {
            return Collections.emptyList();
        }
        List<MonitoringLogServiceEntity> logServicesEntityList = new ArrayList<>();
        for (MonitoringLogDTO dto : LogServiceDTOS) {
            MonitoringLogServiceEntity entity = getMonitoringLogServiceEntity(dto);
            logServicesEntityList.add(entity);
        }
        return logServicesEntityList;
    }

    public MonitoringLogServiceEntity getMonitoringLogServiceEntity(MonitoringLogDTO monitoringLogServiceDTO) {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(monitoringLogServiceDTO.getStaffId()));
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldService.getFieldById(monitoringLogServiceDTO.getFieldCode()));
        CropEntity cropEntity = cropService.toCropEntity(cropService.getCropById(monitoringLogServiceDTO.getCropCode()));
        MonitoringLogServiceEntity logServiceEntity = new MonitoringLogServiceEntity();
        logServiceEntity.setLogCode(monitoringLogServiceDTO.getLogCode());
        logServiceEntity.setLogDate(monitoringLogServiceDTO.getLogDate());
        logServiceEntity.setLogDetails(monitoringLogServiceDTO.getLogDetails());
        logServiceEntity.setObservedImage(monitoringLogServiceDTO.getObservedImage());
        logServiceEntity.setStaff(staffEntity);
        logServiceEntity.setFields(fieldEntity);
        logServiceEntity.setCrop(cropEntity);
        return logServiceEntity;
    }
}
