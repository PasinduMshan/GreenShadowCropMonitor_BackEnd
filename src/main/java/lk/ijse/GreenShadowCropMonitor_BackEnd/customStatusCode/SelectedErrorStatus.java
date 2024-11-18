package lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.StaffStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus, StaffStatus, VehicleStatus {
    private int statusCode;
    private String statusMessage;
}
