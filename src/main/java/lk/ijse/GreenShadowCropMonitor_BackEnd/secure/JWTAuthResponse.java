package lk.ijse.GreenShadowCropMonitor_BackEnd.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTAuthResponse implements Serializable {
    private String token;
}


