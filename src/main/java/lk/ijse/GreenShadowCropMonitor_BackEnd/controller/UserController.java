package lk.ijse.GreenShadowCropMonitor_BackEnd.controller;

import lk.ijse.GreenShadowCropMonitor_BackEnd.Service.UserService;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl.UserDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.exception.DataPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
