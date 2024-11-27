package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.CropDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestParam("cropCommonName") String cropCommonName,
            @RequestParam("cropScientificName") String cropScientificName,
            @RequestParam("cropCategory") String cropCategory,
            @RequestParam("cropSeason") String cropSeason,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropImage") MultipartFile cropImage
    ) {
        String base64Image = "";
        try {
            base64Image = AppUtil.imageFileToBase64(cropImage.getBytes());
            String cropCode = AppUtil.generateCropCode();
            var buildCropDTO = new CropDTO();
            buildCropDTO.setCropCode(cropCode);
            buildCropDTO.setCropCommonName(cropCommonName);
            buildCropDTO.setCropScientificName(cropScientificName);
            buildCropDTO.setCropImage(base64Image);
            buildCropDTO.setCropCategory(cropCategory);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{cropCode}")
    public ResponseEntity<Void> updateCrop(
            @RequestParam("cropCommonName") String cropCommonName,
            @RequestParam("cropScientificName") String cropScientificName,
            @RequestParam("cropCategory") String cropCategory,
            @RequestParam("cropSeason") String cropSeason,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropImage") MultipartFile cropImage,
            @PathVariable("cropCode") String cropCode
    ){
        String base64Image = "";
        try {
            base64Image = AppUtil.imageFileToBase64(cropImage.getBytes());
            var buildCropDTO = new CropDTO();
            buildCropDTO.setCropCode(cropCode);
            buildCropDTO.setCropCommonName(cropCommonName);
            buildCropDTO.setCropScientificName(cropScientificName);
            buildCropDTO.setCropImage(base64Image);
            buildCropDTO.setCropCategory(cropCategory);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);
            cropService.updateCrop(cropCode, buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
