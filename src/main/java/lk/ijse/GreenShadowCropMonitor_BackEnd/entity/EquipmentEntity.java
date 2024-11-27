package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "equipments")
public class EquipmentEntity {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity fields;
}
