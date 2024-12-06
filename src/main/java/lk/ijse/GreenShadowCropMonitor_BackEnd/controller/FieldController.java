package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestParam("fieldImage01") MultipartFile fieldImage01,
            @RequestParam("fieldImage02") MultipartFile fieldImage02
            ) {
        String base64FieldImage01 = "";
        String base64FieldImage02 = "";
        try {
            base64FieldImage01 = AppUtil.imageFileToBase64(fieldImage01.getBytes());
            base64FieldImage02 = AppUtil.imageFileToBase64(fieldImage02.getBytes());
            var buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setFieldLocation(fieldLocation);
            buildFieldDTO.setFieldSize(fieldSize);
            buildFieldDTO.setFieldImage01(base64FieldImage01);
            buildFieldDTO.setFieldImage02(base64FieldImage02);
            fieldService.saveField(buildFieldDTO);
            System.out.println("Saved field : " + buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{fieldCode}")
    public ResponseEntity<Void> updateField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestParam("fieldImage01") MultipartFile fieldImage01,
            @RequestParam("fieldImage02") MultipartFile fieldImage02,
            @PathVariable("fieldCode") String fieldCode
    ) {
        String base64FieldImage01 = "";
        String base64FieldImage02 = "";
        try {
            base64FieldImage01 = AppUtil.imageFileToBase64(fieldImage01.getBytes());
            base64FieldImage02 = AppUtil.imageFileToBase64(fieldImage02.getBytes());
            var buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setFieldLocation(fieldLocation);
            buildFieldDTO.setFieldSize(fieldSize);
            buildFieldDTO.setFieldImage01(base64FieldImage01);
            buildFieldDTO.setFieldImage02(base64FieldImage02);
            fieldService.updateField(fieldCode ,buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getField(@PathVariable("fieldCode") String fieldCode) {
        return fieldService.getField(fieldCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
