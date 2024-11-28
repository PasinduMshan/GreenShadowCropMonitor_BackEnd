package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.CropDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.CropStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.CropDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.CropEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.CropNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.FieldNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Autowired
    FieldService fieldService;

    @Override
    public void saveCrop(CropDTO cropDTO) throws DataPersistException {
        CropEntity saveCrop = cropDao.save(toCropEntity(cropDTO));
        if (saveCrop == null) {
            throw new DataPersistException("Crop not saved!");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> cropEntities = cropDao.findAll();
        return mapping.toCropDTOList(cropEntities);
    }

    @Override
    public CropStatus getCrop(String cropCode) {
        if (cropDao.existsById(cropCode)) {
            CropEntity cropEntity = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(cropEntity);
        } else {
            return new SelectedErrorStatus(2, "Crop with id " + cropCode + "not found!");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> entity = cropDao.findById(cropCode);
        if (!entity.isPresent()) {
            throw new FieldNotFoundException("Crop with id " + cropCode + " not Found");
        } else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        FieldDTO field = fieldService.getFieldById(cropDTO.getFieldCode());
        FieldEntity fieldEntity = mapping.toFieldEntity(field);

        Optional<CropEntity> cropEntity = cropDao.findById(cropCode);
        if (!cropEntity.isPresent()) {
            throw new CropNotFoundException("Crop not found!");
        } else {
            cropEntity.get().setCropCommonName(cropDTO.getCropCommonName());
            cropEntity.get().setCropScientificName(cropDTO.getCropScientificName());
            cropEntity.get().setCropImage(cropDTO.getCropImage());
            cropEntity.get().setCropCategory(cropDTO.getCropCategory());
            cropEntity.get().setCropSeason(cropDTO.getCropSeason());
            cropEntity.get().setFields(fieldEntity);
        }
    }

    @Override
    public CropDTO getCropById(String cropCode) {
        if (cropDao.existsById(cropCode)) {
            CropEntity cropEntity = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(cropEntity);
        } else {
            throw new FieldNotFoundException("Field Not Found");
        }
    }

    @Override
    public CropEntity toCropEntity(CropDTO cropDTO) {
        FieldDTO field = fieldService.getFieldById(cropDTO.getFieldCode());
        FieldEntity fieldEntity = mapping.toFieldEntity(field);
        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropCode(cropDTO.getCropCode());
        cropEntity.setCropCommonName(cropDTO.getCropCommonName());
        cropEntity.setCropScientificName(cropDTO.getCropScientificName());
        cropEntity.setCropImage(cropDTO.getCropImage());
        cropEntity.setCropCategory(cropDTO.getCropCategory());
        cropEntity.setCropSeason(cropDTO.getCropSeason());
        cropEntity.setFields(fieldEntity);
        cropEntity.setLogServices(new ArrayList<>());
        return cropEntity;
    }

}
