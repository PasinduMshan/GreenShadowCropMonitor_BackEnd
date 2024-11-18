package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleStatus {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffId;
}
