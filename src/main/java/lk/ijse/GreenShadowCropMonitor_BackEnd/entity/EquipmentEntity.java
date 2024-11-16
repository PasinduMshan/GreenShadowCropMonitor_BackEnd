package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipments")
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    @OneToOne
    private StaffEntity staff;
    @OneToOne
    private FieldEntity fields;
}
