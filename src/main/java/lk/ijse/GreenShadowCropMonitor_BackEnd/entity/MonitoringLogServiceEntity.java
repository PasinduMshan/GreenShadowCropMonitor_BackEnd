package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "monitoring_log_service")
public class MonitoringLogServiceEntity {
    @Id
    private String logCode;
    private Date logDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity fields;
    @ManyToOne
    @JoinColumn(name = "cropCode", nullable = false)
    private CropEntity crop;
}
