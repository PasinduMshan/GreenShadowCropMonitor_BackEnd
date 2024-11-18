package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.StaffDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.StaffStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.StaffDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return List.of();
    }

    @Override
    public StaffStatus getStaff(String staffId) {
        return null;
    }

    @Override
    public void deleteStaff(String staffId) {

    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {

    }
}
