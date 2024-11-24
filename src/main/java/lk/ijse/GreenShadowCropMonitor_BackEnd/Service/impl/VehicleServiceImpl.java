package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.VehicleService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.VehicleDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.EquipmentEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.VehicleEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.VehicleNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    StaffService staffService;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) throws DataPersistException {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(vehicleDTO.getStaffId()));
        VehicleEntity saveVehicle = vehicleDao.save(new VehicleEntity(
                vehicleDTO.getVehicleCode(),
                vehicleDTO.getLicensePlateNumber(),
                vehicleDTO.getVehicleCategory(),
                vehicleDTO.getFuelType(),
                vehicleDTO.getStatus(),
                vehicleDTO.getRemarks(),
                staffEntity
        ));
        if (saveVehicle == null) {
            throw new DataPersistException("Vehicle Not Saved!");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> vehicleEntityList = vehicleDao.findAll();
        return mapping.toVehicleDTOList(vehicleEntityList);
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        if (vehicleDao.existsById(vehicleCode)) {
            VehicleEntity vehicleEntity = vehicleDao.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(vehicleEntity);
        } else {
            return new SelectedErrorStatus(2, "Vehicle with id " + vehicleCode + " Not Found!");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        StaffEntity staffEntity = mapping.toStaffEntity(staffService.getStaffById(vehicleDTO.getStaffId()));
        Optional<VehicleEntity> vehicleEntity = vehicleDao.findById(vehicleCode);
        if (!vehicleEntity.isPresent()) {
            throw new VehicleNotFoundException("Vehicle Not Found!");
        } else {
            vehicleEntity.get().setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            vehicleEntity.get().setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicleEntity.get().setFuelType(vehicleDTO.getFuelType());
            vehicleEntity.get().setStatus(vehicleDTO.getStatus());
            vehicleEntity.get().setRemarks(vehicleDTO.getRemarks());
            vehicleEntity.get().setStaff(staffEntity);
        }
    }

    @Override
    public VehicleDTO getVehicleById(String vehicleCode) {
        return null;
    }
}
