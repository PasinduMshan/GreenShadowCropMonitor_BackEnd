package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @Autowired
    FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestParam("fieldImage01") MultipartFile fieldImage01,
            @RequestParam("fieldImage02") MultipartFile fieldImage02
            ) {
        String base64FieldImage01 = "";
        String base64FieldImage02 = "";
        try {
            byte[] bytesFieldImage01 = fieldImage01.getBytes();
            byte[] bytesFieldImage02 = fieldImage02.getBytes();
            base64FieldImage01 = AppUtil.fieldImage01ToBase64(bytesFieldImage01);
            base64FieldImage02 = AppUtil.fieldImage02ToBase64(bytesFieldImage02);
            String fieldCode = AppUtil.generateFieldCode();
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
            byte[] bytesFieldImage01 = fieldImage01.getBytes();
            byte[] bytesFieldImage02 = fieldImage02.getBytes();
            base64FieldImage01 = AppUtil.fieldImage01ToBase64(bytesFieldImage01);
            base64FieldImage02 = AppUtil.fieldImage02ToBase64(bytesFieldImage02);

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getField(@PathVariable("fieldCode") String fieldCode) {
        String regexForFieldID = "^FIELD-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForFieldID);
        var regexMatcher = regexPattern.matcher(fieldCode);
        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "Field ID is not valid");
        }
        return fieldService.getField(fieldCode);
    }
}
