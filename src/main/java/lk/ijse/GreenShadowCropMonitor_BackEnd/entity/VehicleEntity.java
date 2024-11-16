package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;

}
