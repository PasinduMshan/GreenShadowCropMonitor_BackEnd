package lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus {
    private int statusCode;
    private String statusMessage;
}
