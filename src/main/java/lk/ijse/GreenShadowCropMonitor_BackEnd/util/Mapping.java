package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.*;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;


    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }
    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }
    public List<FieldDTO> toFieldDTOList(List<FieldEntity> fieldEntityList) {
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDTO>>() {}.getType());
    }

    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }
    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }
    public List<StaffDTO> toStaffDTOList(List<StaffEntity> staffEntityList) {
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setEquipmentId(equipmentEntity.getEquipmentId());
        equipmentDTO.setEquipmentName(equipmentEntity.getEquipmentName());
        equipmentDTO.setEquipmentType(equipmentEntity.getEquipmentType());
        equipmentDTO.setStatus(equipmentEntity.getStatus());
        equipmentDTO.setStaffId(equipmentEntity.getStaff().getStaffId());
        equipmentDTO.setFieldCode(equipmentEntity.getFields().getFieldCode());
        return equipmentDTO;
    }
    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity> equipmentEntityList) {
        List<EquipmentDTO> dtoList = new ArrayList<>();
        for (EquipmentEntity entity : equipmentEntityList) {
            EquipmentDTO dto = toEquipmentDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleCode(vehicleEntity.getVehicleCode());
        vehicleDTO.setLicensePlateNumber(vehicleEntity.getLicensePlateNumber());
        vehicleDTO.setVehicleCategory(vehicleEntity.getVehicleCategory());
        vehicleDTO.setFuelType(vehicleEntity.getFuelType());
        vehicleDTO.setStatus(vehicleEntity.getStatus());
        vehicleDTO.setRemarks(vehicleEntity.getRemarks());
        vehicleDTO.setStaffId(vehicleEntity.getStaff().getStaffId());
        return vehicleDTO;
    }

    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntityList) {
        List<VehicleDTO> dtoList = new ArrayList<>();
        for (VehicleEntity entity : vehicleEntityList) {
            VehicleDTO dto = toVehicleDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CropDTO toCropDTO(CropEntity cropEntity) {
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropCode(cropEntity.getCropCode());
        cropDTO.setCropCommonName(cropEntity.getCropCommonName());
        cropDTO.setCropScientificName(cropEntity.getCropScientificName());
        cropDTO.setCropImage(cropEntity.getCropImage());
        cropDTO.setCropCategory(cropEntity.getCropCategory());
        cropDTO.setCropSeason(cropEntity.getCropSeason());
        cropDTO.setFieldCode(cropEntity.getFields().getFieldCode());
        return cropDTO;
    }

    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntityList) {
        List<CropDTO> dtoList = new ArrayList<>();
        for (CropEntity entity : cropEntityList) {
            CropDTO dto = toCropDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<MonitoringLogDTO> toLogServicesDTOList(List<MonitoringLogEntity> logEntities) {
        List<MonitoringLogDTO> logServicesDTOList = new ArrayList<>();
        for (MonitoringLogEntity entity : logEntities) {
            MonitoringLogDTO logServiceDTO = toLogServiceDTO(entity);
            logServicesDTOList.add(logServiceDTO);
        }
        return logServicesDTOList;
    }

    public MonitoringLogDTO toLogServiceDTO(MonitoringLogEntity logEntity) {
        MonitoringLogDTO dto = new MonitoringLogDTO();
        dto.setLogCode(logEntity.getLogCode());
        dto.setLogDate(logEntity.getLogDate());
        dto.setLogDetails(logEntity.getLogDetails());
        dto.setObservedImage(logEntity.getObservedImage());
        dto.setStaffId(logEntity.getStaff().getStaffId());
        dto.setFieldCode(logEntity.getFields().getFieldCode());
        dto.setCropCode(logEntity.getCrop().getCropCode());
        return dto;
    }

    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> toUserDTOList(List<UserEntity> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDTO>>() {}.getType());
    }
}
