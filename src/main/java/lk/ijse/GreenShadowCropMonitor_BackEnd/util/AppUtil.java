package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String fieldImage01ToBase64(byte [] fieldImage01) {
        return Base64.getEncoder().encodeToString(fieldImage01);
    }
    public static String fieldImage02ToBase64(byte [] fieldImage02) {
        return Base64.getEncoder().encodeToString(fieldImage02);
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
}
