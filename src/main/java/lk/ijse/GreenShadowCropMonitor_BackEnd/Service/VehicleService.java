package lk.ijse.GreenShadowCropMonitor_BackEnd.Service;

import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO) throws DataPersistException;
    List<VehicleDTO> getAllVehicles();
    VehicleStatus getVehicle(String vehicleCode);
    void deleteVehicle(String vehicleCode);
    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);
}
