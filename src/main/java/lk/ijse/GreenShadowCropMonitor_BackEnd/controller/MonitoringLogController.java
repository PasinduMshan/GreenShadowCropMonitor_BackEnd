package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.MonitoringLogService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/monitoring_log")
@CrossOrigin
public class MonitoringLogController {
    @Autowired
    MonitoringLogService monitoringLogService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitorLogDetails(
            @RequestParam("logCode") String logCode,
            @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropCode") String cropCode,
            @RequestParam("observedImage")MultipartFile observedImage
            ) {
        String base64Image = "";
        try {
            base64Image = AppUtil.imageFileToBase64(observedImage.getBytes());
            var buildLogDTO = new MonitoringLogDTO();
            buildLogDTO.setLogCode(logCode);
            buildLogDTO.setLogDate(logDate);
            buildLogDTO.setLogDetails(logDetails);
            buildLogDTO.setStaffId(staffId);
            buildLogDTO.setFieldCode(fieldCode);
            buildLogDTO.setCropCode(cropCode);
            buildLogDTO.setObservedImage(base64Image);
            monitoringLogService.saveMonitorLog(buildLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{logCode}")
    public ResponseEntity<Void> updateMonitorLogDetails(
            @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropCode") String cropCode,
            @RequestParam("observedImage")MultipartFile observedImage,
            @PathVariable("logCode") String logCode
    ) {
        String base64Image = "";
        try {
            base64Image = AppUtil.imageFileToBase64(observedImage.getBytes());
            var logDTO = new MonitoringLogDTO();
            logDTO.setLogCode(logCode);
            logDTO.setLogDate(logDate);
            logDTO.setLogDetails(logDetails);
            logDTO.setStaffId(staffId);
            logDTO.setFieldCode(fieldCode);
            logDTO.setCropCode(cropCode);
            logDTO.setObservedImage(base64Image);
            monitoringLogService.updateMonitorLog(logCode,logDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllMonitorLogDetails() {
        return monitoringLogService.getAllMonitorLog();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping(value = "/{logCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getMonitorLogDetails(@PathVariable("logCode") String logCode) {
        return monitoringLogService.getMonitorLog(logCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteMonitorLogDetails(@PathVariable("logCode") String logCode) {
        try {
            monitoringLogService.deleteMonitorLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


