package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.StaffDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.StaffStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.StaffDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.StaffNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) throws DataPersistException {
        StaffEntity saveStaff = staffDao.save(mapping.toStaffEntity(staffDTO));
        if (saveStaff == null) {
            throw new DataPersistException("Staff Save Failed");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffEntity> allStaff = staffDao.findAll();
        return mapping.toStaffDTOList(allStaff);
    }

    @Override
    public StaffStatus getStaff(String staffId) {
        if (staffDao.existsById(staffId)) {
            StaffEntity selectedStaff = staffDao.getReferenceById(staffId);
            return mapping.toStaffDTO(selectedStaff);
        } else {
            return new SelectedErrorStatus(2, "Staff with id " + staffId + " not found!");
        }
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> existedStaff = staffDao.findById(staffId);
        if (!existedStaff.isPresent()) {
            throw new StaffNotFoundException("Staff with id " + staffId + " not found!");
        } else {
            staffDao.deleteById(staffId);
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> staffEntity = staffDao.findById(staffId);
        if (!staffEntity.isPresent()) {
            throw new StaffNotFoundException("Staff Not Found");
        } else {
            staffEntity.get().setFirstName(staffDTO.getFirstName());
            staffEntity.get().setLastName(staffDTO.getLastName());
            staffEntity.get().setDesignation(staffDTO.getDesignation());
            staffEntity.get().setGender(staffDTO.getGender());
            staffEntity.get().setJoinDate(staffDTO.getJoinDate());
            staffEntity.get().setDateOfBirth(staffDTO.getDateOfBirth());
            staffEntity.get().setAddress01(staffDTO.getAddress01());
            staffEntity.get().setAddress02(staffDTO.getAddress02());
            staffEntity.get().setAddress03(staffDTO.getAddress03());
            staffEntity.get().setAddress04(staffDTO.getAddress04());
            staffEntity.get().setAddress05(staffDTO.getAddress05());
            staffEntity.get().setEmail(staffDTO.getEmail());
            staffEntity.get().setRole(staffDTO.getRole());
        }
    }

    @Override
    public StaffDTO getStaffById(String staffId) {
        if (staffDao.existsById(staffId)) {
            StaffEntity selectedStaff = staffDao.getReferenceById(staffId);
            return mapping.toStaffDTO(selectedStaff);
        } else {
            throw new StaffNotFoundException("Staff Not Found");
        }
    }
}
