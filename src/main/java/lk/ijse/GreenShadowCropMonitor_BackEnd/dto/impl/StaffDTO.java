package lk.ijse.GreenShadowCropMonitor_BackEnd.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.StaffStatus;
import lk.ijse.GreenShadowCropMonitor_BackEnd.dto.SuperDTO;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Gender;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date joinDate;
    private Date dateOfBirth;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String contactNo;
    private String email;
    private Role role;
}
