package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private String staffId;
    private String fieldCode;
}
