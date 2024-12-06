package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.CropStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropStatus {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage01;
    private String cropCategory;
    private String cropSeason;
    private String fieldCode;
}
