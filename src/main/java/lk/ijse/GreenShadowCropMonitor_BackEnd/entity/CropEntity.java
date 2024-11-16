package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String cropCategory;
    private String cropSeason;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity fields;
    @OneToMany(mappedBy = "crop")
    private List<MonitoringLogServiceEntity> logServices;

}
