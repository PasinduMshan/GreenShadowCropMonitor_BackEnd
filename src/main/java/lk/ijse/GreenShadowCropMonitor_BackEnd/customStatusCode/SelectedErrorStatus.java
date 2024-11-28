package lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus, StaffStatus, VehicleStatus, EquipmentStatus, CropStatus,
        MonitoringLogStatus {
    private int statusCode;
    private String statusMessage;
}
