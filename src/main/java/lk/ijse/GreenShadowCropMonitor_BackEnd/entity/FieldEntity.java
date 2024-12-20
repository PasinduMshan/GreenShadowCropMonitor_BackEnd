package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fields")
public class FieldEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double fieldSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage01;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage02;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogEntity> logServices;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentEntity> equipments;
}
