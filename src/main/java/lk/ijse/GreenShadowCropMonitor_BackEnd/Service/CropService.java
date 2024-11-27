package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.CropDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO) throws DataPersistException;
    List<CropDTO> getAllCrops();
    EquipmentStatus getCrop(String cropCode);
    void deleteCrop(String cropCode);
    void updateCrop(String cropCode,CropDTO cropDTO);
    CropDTO getCropById(String cropCode);
}
