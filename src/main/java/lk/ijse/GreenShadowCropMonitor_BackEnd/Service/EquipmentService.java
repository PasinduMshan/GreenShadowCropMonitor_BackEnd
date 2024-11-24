package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO) throws DataPersistException;
    List<EquipmentDTO> getAllEquipment();
    EquipmentStatus getEquipment(String equipmentID);
    void deleteEquipment(String equipmentID);
    void updateEquipment(String equipmentID,EquipmentDTO equipmentDTO);
}
