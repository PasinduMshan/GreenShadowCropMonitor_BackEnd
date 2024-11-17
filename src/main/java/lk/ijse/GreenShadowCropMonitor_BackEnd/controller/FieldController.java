package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.FieldService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.FieldDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
        System.out.println("Received fieldName: " + fieldName);
        System.out.println("Received fieldLocation: " + fieldLocation);
        System.out.println("Received fieldSize: " + fieldSize);
        System.out.println("Received fieldImage01: " + fieldImage01.getOriginalFilename());
        System.out.println("Received fieldImage02: " + fieldImage02.getOriginalFilename());
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
}
