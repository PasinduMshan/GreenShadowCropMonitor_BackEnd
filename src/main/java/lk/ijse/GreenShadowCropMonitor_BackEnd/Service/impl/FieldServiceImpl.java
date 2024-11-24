package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.FieldDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.FieldNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) throws DataPersistException {
        FieldEntity savedField = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (savedField == null) {
            throw new DataPersistException("Field not saved!");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        List<FieldEntity> allFields = fieldDao.findAll();
        return mapping.toFieldDTOList(allFields);
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        } else {
            return new SelectedErrorStatus(2, "Field with id " + fieldCode + "not found!");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> existedField = fieldDao.findById(fieldCode);
        if (!existedField.isPresent()) {
            throw new FieldNotFoundException("Field with id " + fieldCode + " not Found");
        } else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> tempField = fieldDao.findById(fieldCode);
        if (!tempField.isPresent()) {
            throw new FieldNotFoundException("Field Not Found");
        } else {
            tempField.get().setFieldName(fieldDTO.getFieldName());
            tempField.get().setFieldLocation(fieldDTO.getFieldLocation());
            tempField.get().setFieldSize(fieldDTO.getFieldSize());
            tempField.get().setFieldImage01(fieldDTO.getFieldImage01());
            tempField.get().setFieldImage02(fieldDTO.getFieldImage02());
        }
    }

    @Override
    public FieldDTO getFieldById(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        } else {
            throw new FieldNotFoundException("Field Not Found");
        }
    }

}
