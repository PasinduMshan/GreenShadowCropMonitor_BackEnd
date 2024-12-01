package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.VehicleService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.VehicleStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{vehicleCode}")
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable String vehicleCode) {
        String regexForVehicleID = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        var regexMatcher = regexPattern.matcher(vehicleCode);
        try {
            if (!regexMatcher.matches() && vehicleDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(vehicleCode, vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        String regexForVehicleID = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        var regexMatcher = regexPattern.matcher(vehicleCode);
        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "Vehicle Code Not Found");
        }
        return vehicleService.getVehicle(vehicleCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        String regexForVehicleID = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        var regexMatcher = regexPattern.matcher(vehicleCode);
        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
