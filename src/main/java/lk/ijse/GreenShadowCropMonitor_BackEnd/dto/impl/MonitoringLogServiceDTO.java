package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogServiceDTO implements SuperDTO {
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private String fieldCode;
}
