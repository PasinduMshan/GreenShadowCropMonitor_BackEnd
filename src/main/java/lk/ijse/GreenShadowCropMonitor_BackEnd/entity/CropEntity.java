package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String cropCategory;
    private String cropSeason;
    @ManyToOne
    @JoinColumn(name = "fieldCode" , nullable = false)
    private FieldEntity fields;
    @OneToMany(mappedBy = "crop" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonitoringLogServiceEntity> logServices;

}
