package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO) throws DataPersistException;
    List<FieldDTO> getAllFields();
    FieldStatus getField(String fieldCode);
    void deleteField(String fieldCode);
    void updateField(String fieldCode,FieldDTO fieldDTO);
    FieldDTO getFieldById(String fieldCode);
}
