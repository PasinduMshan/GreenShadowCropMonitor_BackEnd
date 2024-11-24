package lk.ijse.GreenShadowCropMonitor_BackEnd.Service.impl;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.VehicleService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dao.VehicleDao;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.VehicleEntity;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        VehicleEntity saveVehicle = new VehicleEntity(
                vehicleDTO.getVehicleCode(),
                vehicleDTO.getLicensePlateNumber(),
                vehicleDTO.getVehicleCategory(),
                vehicleDTO.getFuelType(),
                vehicleDTO.getStatus(),
                vehicleDTO.getRemarks(),
                staffEntity
        );
        if (saveVehicle == null) {
            throw new DataPersistException("Vehicle Not Saved!");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return List.of();
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        return null;
    }

    @Override
    public void deleteVehicle(String vehicleCode) {

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {

    }

    @Override
    public VehicleDTO getVehicleById(String vehicleCode) {
        return null;
    }
}
