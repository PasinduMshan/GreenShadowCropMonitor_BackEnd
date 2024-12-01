package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.EquipmentService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.EquipmentStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.StaffNotFoundException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{equipmentId}")
    public ResponseEntity<Void> updateEquipment(@RequestBody EquipmentDTO equipmentDTO, @PathVariable("equipmentId") String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        var regexMatcher = regexPattern.matcher(equipmentId);
        try {
            if (!regexMatcher.matches() && equipmentDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getEquipment(@PathVariable("equipmentId") String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        var regexMatcher = regexPattern.matcher(equipmentId);
        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "Equipment ID is not valid");
        }
        return equipmentService.getEquipment(equipmentId);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        var regexMatcher = regexPattern.matcher(equipmentId);
        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}