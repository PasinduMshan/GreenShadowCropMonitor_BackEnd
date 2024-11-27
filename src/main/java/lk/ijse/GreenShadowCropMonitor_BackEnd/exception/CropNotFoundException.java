package lk.ijse.GreenShadowCropMonitor_BackEnd.exception;

public class CropNotFoundException extends RuntimeException {
    public CropNotFoundException() {}
    public CropNotFoundException(String message) {
        super(message);
    }
    public CropNotFoundException(String message, Throwable cause) {}
}
