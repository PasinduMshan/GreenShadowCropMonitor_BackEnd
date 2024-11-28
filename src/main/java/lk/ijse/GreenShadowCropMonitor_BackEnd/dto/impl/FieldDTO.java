package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldStatus {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double fieldSize;
    private String fieldImage01;
    private String fieldImage02;
    private List<CropDTO> crops;
    private List<MonitoringLogDTO> logServices;
}
