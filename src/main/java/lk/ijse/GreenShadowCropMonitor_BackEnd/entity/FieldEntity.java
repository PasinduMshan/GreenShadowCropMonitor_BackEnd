package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private List<MonitoringLogServiceEntity> logServices;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentEntity> equipments;
}
