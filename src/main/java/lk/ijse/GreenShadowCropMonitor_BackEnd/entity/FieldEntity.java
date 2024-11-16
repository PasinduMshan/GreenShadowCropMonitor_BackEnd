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
    private Point fieldLocation;
    private Double fieldSize;
    private String fieldImage01;
    private String fieldImage02;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "fields", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogServiceEntity> logServices;
}
