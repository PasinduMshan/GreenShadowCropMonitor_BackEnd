package lk.ijse.GreenShadowCropMonitor_BackEnd.util;

import java.util.Base64;

public class AppUtil {
    public static String imageFileToBase64(byte [] imageFile) {
        return Base64.getEncoder().encodeToString(imageFile);
    }
}
