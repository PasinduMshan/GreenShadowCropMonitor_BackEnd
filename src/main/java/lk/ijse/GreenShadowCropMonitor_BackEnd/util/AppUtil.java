package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String imageFileToBase64(byte [] imageFile) {
        return Base64.getEncoder().encodeToString(imageFile);
    }
    public static String generateFieldCode() {
        return "FIELD-"+ UUID.randomUUID();
    }
    public static String generateStaffCode() {
        return "STAFF-"+ UUID.randomUUID();
    }
    public static String generateEquipmentCode() {
        return "EQUIPMENT-"+ UUID.randomUUID();
    }
    public static String generateVehicleCode() {
        return "VEHICLE-"+ UUID.randomUUID();
    }
    public static String generateCropCode() {
        return "CROP-"+ UUID.randomUUID();
    }
    public static String generateMonitorLogCode() {
        return "LOG-"+ UUID.randomUUID();
    }
}
