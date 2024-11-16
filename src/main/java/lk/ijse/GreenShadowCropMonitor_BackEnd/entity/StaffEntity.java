package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Gender;
import lk.ijse.GreenShadowCropMonitor_BackEnd.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Enum<Gender> gender;
    private Date joinDate;
    private Date DOB;
    private String address01;
    private String address02;
    private String address03;
    private String address04;
    private String address05;
    private String contactNo;
    private String email;
    private Enum<Role> role;

}
