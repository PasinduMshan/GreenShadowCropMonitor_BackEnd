package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.StaffDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
