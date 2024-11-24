package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.EquipmentService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.EquipmentDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.EquipmentEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.FieldEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.EquipmentNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.StaffNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Autowired
    StaffService staffService;
    @Autowired
    FieldService fieldService;


    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) throws DataPersistException {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(equipmentDTO.getStaffId()));
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldService.getFieldById(equipmentDTO.getFieldCode()));
        EquipmentEntity saveEquipments = equipmentDao.save(new EquipmentEntity(
                equipmentDTO.getEquipmentId(),
                equipmentDTO.getEquipmentName(),
                equipmentDTO.getEquipmentType(),
                equipmentDTO.getStatus(),
                staffEntity,
                fieldEntity
        ));
        if (saveEquipments == null) {
            throw new DataPersistException("Equipment saved failed!");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findAll();
        return mapping.toEquipmentDTOList(equipmentEntities);
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentID) {
        if (equipmentDao.existsById(equipmentID)) {
            EquipmentEntity equipmentEntity = equipmentDao.getReferenceById(equipmentID);
            return mapping.toEquipmentDTO(equipmentEntity);
        } else {
            return new SelectedErrorStatus(2, "Equipment with id " + equipmentID + "not found!");
        }
    }

    @Override
    public void deleteEquipment(String equipmentID) {
        Optional<EquipmentEntity> equipmentEntity = equipmentDao.findById(equipmentID);
        if (!equipmentEntity.isPresent()) {
            throw new EquipmentNotFoundException("Equipment with id " + equipmentID + " not found!");
        } else {
            equipmentDao.deleteById(equipmentID);
        }
    }

    @Override
    public void updateEquipment(String equipmentID, EquipmentDTO equipmentDTO) {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(equipmentDTO.getStaffId()));
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldService.getFieldById(equipmentDTO.getFieldCode()));

        Optional<EquipmentEntity> equipmentEntity = equipmentDao.findById(equipmentID);
        if (!equipmentEntity.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found!");
        } else {
            equipmentEntity.get().setEquipmentName(equipmentDTO.getEquipmentName());
            equipmentEntity.get().setEquipmentType(equipmentDTO.getEquipmentType());
            equipmentEntity.get().setStatus(equipmentDTO.getStatus());
            equipmentEntity.get().setStaff(staffEntity);
            equipmentEntity.get().setFields(fieldEntity);
        }
    }
}
