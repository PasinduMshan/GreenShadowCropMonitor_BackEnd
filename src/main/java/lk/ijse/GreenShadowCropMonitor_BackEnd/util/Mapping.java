package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.StaffDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.EquipmentEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.VehicleEntity;
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
}
