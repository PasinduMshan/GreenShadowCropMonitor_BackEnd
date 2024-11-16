package lk.ijse.GreenShadowCropMonitor_BackEnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @OneToMany(mappedBy = "field")
    private List<CropEntity> crops;
}
