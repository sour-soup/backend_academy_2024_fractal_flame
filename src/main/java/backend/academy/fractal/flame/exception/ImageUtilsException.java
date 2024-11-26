package backend.academy.fractal.flame.exception;

public class ImageUtilsException extends RuntimeException {
    public ImageUtilsException(String message) {
        super(message);
    }

    public ImageUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}
