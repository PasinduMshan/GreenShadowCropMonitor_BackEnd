package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.CropService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.customStatusCode.SelectedErrorStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.CropStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.CropDTO;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/{cropCode}")
    public CropStatus getCrop(@PathVariable("cropCode") String cropCode) {
        String regexForCropID = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropID);
        var regexMatcher = regexPattern.matcher(cropCode);
        if (!regexMatcher.matches()) {
            return new SelectedErrorStatus(1, "Crop ID is not valid");
        }
        return cropService.getCrop(cropCode);
    }

    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        String regexForCropID = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropID);
        var regexMatcher = regexPattern.matcher(cropCode);
        try {
            if (!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
