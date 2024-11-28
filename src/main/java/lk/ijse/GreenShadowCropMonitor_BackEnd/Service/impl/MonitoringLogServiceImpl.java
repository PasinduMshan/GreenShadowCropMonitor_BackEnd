package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.MonitoringLogService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.MonitoringLogDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.CropEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.MonitoringLogEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.FieldNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        MonitoringLogEntity saveLogDetail = monitoringLogDao.save(getMonitoringLogServiceEntity
                (monitoringLogServiceDTO));
        if (saveLogDetail == null) {
            throw new DataPersistException("Log Detail not saved!");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitorLog() {
        List<MonitoringLogEntity> entityList = monitoringLogDao.findAll();
        return mapping.toLogServicesDTOList(entityList);
    }

    @Override
    public MonitoringLogStatus getMonitorLog(String logCode) {
        if (monitoringLogDao.existsById(logCode)) {
            MonitoringLogEntity referenceById = monitoringLogDao.getReferenceById(logCode);
            return mapping.toLogServiceDTO(referenceById);
        } else {
            return new SelectedErrorStatus(2, "Monitor Log with id " + logCode + "not found!");
        }
    }

    @Override
    public void deleteMonitorLog(String logCode) {

    }

    @Override
    public void updateMonitorLog(String logCode, MonitoringLogDTO monitoringLogServiceDTO) {
        Optional<MonitoringLogEntity> monitoringLogEntity = monitoringLogDao.findById(logCode);
        if (!monitoringLogEntity.isPresent()) {
            throw new FieldNotFoundException("Monitoring Log Not Found!");
        } else {
            monitoringLogEntity.get().setLogDate(monitoringLogServiceDTO.getLogDate());
            monitoringLogEntity.get().setLogDetails(monitoringLogServiceDTO.getLogDetails());
            monitoringLogEntity.get().setObservedImage(monitoringLogServiceDTO.getObservedImage());
            monitoringLogEntity.get().setStaff(mapping.toStaffEntity(staffService.getStaffById(monitoringLogServiceDTO.
                    getStaffId())));
            monitoringLogEntity.get().setFields(mapping.toFieldEntity(fieldService.getFieldById(monitoringLogServiceDTO.
                    getFieldCode())));
            monitoringLogEntity.get().setCrop(cropService.toCropEntity(cropService.getCropById(monitoringLogServiceDTO.
                    getCropCode())));
        }
    }

    @Override
    public List<MonitoringLogEntity> getLogServicesEntityList(List<MonitoringLogDTO> LogServiceDTOS) {
        if (LogServiceDTOS == null || LogServiceDTOS.isEmpty()) {
            return Collections.emptyList();
        }
        List<MonitoringLogEntity> logServicesEntityList = new ArrayList<>();
        for (MonitoringLogDTO dto : LogServiceDTOS) {
            MonitoringLogEntity entity = getMonitoringLogServiceEntity(dto);
            logServicesEntityList.add(entity);
        }
        return logServicesEntityList;
    }

    public MonitoringLogEntity getMonitoringLogServiceEntity(MonitoringLogDTO monitoringLogServiceDTO) {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(monitoringLogServiceDTO.getStaffId()));
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldService.getFieldById(monitoringLogServiceDTO.getFieldCode()));
        CropEntity cropEntity = cropService.toCropEntity(cropService.getCropById(monitoringLogServiceDTO.getCropCode()));
        MonitoringLogEntity logServiceEntity = new MonitoringLogEntity();
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
