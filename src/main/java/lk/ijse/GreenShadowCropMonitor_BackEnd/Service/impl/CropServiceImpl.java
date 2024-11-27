package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.EquipmentService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.CropDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.CropDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.MonitoringLogServiceDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.CropEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.MonitoringLogServiceEntity;
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
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    FieldService fieldService;
    @Autowired
    StaffService staffService;

    @Override
    public void saveCrop(CropDTO cropDTO) throws DataPersistException {
        FieldDTO field = fieldService.getFieldById(cropDTO.getFieldCode());
        FieldEntity fieldEntity = mapping.toFieldEntity(field);

        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropCode(cropDTO.getCropCode());
        cropEntity.setCropCommonName(cropDTO.getCropCommonName());
        cropEntity.setCropScientificName(cropDTO.getCropScientificName());
        cropEntity.setCropImage(cropDTO.getCropImage());
        cropEntity.setCropCategory(cropDTO.getCropCategory());
        cropEntity.setCropSeason(cropDTO.getCropSeason());
        cropEntity.setFields(fieldEntity);
        List<MonitoringLogServiceEntity> logServicesEntityList = getLogServicesEntityList(cropDTO.getLogServices());
        cropEntity.setLogServices(logServicesEntityList);
        CropEntity saveCrop = cropDao.save(cropEntity);
        if (saveCrop == null) {
            throw new DataPersistException("Crop not saved!");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return List.of();
    }

    @Override
    public EquipmentStatus getCrop(String cropCode) {
        return null;
    }

    @Override
    public void deleteCrop(String cropCode) {

    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {

    }

    @Override
    public CropDTO getCropById(String cropCode) {
        return null;
    }

    public List<MonitoringLogServiceEntity> getLogServicesEntityList(List<MonitoringLogServiceDTO> LogServiceDTOS) {
        if (LogServiceDTOS == null || LogServiceDTOS.isEmpty()) {
            return Collections.emptyList();
        }
        List<MonitoringLogServiceEntity> logServicesEntityList = new ArrayList<>();
        for (MonitoringLogServiceDTO dto : LogServiceDTOS) {
            MonitoringLogServiceEntity entity = getMonitoringLogServiceEntity(dto);
            logServicesEntityList.add(entity);
        }
        return logServicesEntityList;
    }

    public MonitoringLogServiceEntity getMonitoringLogServiceEntity(MonitoringLogServiceDTO monitoringLogServiceDTO) {
        MonitoringLogServiceEntity logServiceEntity = new MonitoringLogServiceEntity();
        logServiceEntity.setLogCode(monitoringLogServiceDTO.getLogCode());
        logServiceEntity.setLogDate(monitoringLogServiceDTO.getLogDate());
        logServiceEntity.setLogDetails(monitoringLogServiceDTO.getLogDetails());
        logServiceEntity.setObservedImage(monitoringLogServiceDTO.getObservedImage());
        logServiceEntity.setStaff(mapping.toStaffEntity(staffService.getStaffById(monitoringLogServiceDTO.getStaffId())));
        logServiceEntity.setFields(mapping.toFieldEntity(fieldService.getFieldById(monitoringLogServiceDTO.getFieldCode())));
        logServiceEntity.setCrop(mapping.toCropEntity(getCropById(monitoringLogServiceDTO.getCropCode())));
        return logServiceEntity;
    }
}
