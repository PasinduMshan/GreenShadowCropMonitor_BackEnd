package lk.ijse.GreenShadowCropMonitor_BackEnd.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException() {}
    public VehicleNotFoundException(String message) {
        super(message);
    }
    public VehicleNotFoundException(String message, Throwable cause) {}
}
