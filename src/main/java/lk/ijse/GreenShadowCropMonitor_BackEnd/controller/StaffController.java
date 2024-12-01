package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.StaffService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.StaffStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.StaffDTO;
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
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    StaffService staffService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = {"/{staffId}"})
    public ResponseEntity<Void> updateStaff(@RequestBody StaffDTO staffDTO, @PathVariable String staffId) {
        String regexForStaffID = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        var regexMatcher = regexPattern.matcher(staffId);
        try {
            if (!regexMatcher.matches() && staffDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staffId, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @GetMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getStaff(@PathVariable("staffId") String staffId) {
        String regexForStaffID = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        var regexMatcher = regexPattern.matcher(staffId);
        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "Staff ID is not valid");
        }
        return staffService.getStaff(staffId);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staffId) {
        String regexForStaffID = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        var regexMatcher = regexPattern.matcher(staffId);
        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
