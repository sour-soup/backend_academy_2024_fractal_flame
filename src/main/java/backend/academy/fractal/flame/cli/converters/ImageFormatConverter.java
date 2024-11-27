package backend.academy.fractal.flame.cli.converters;

import backend.academy.fractal.flame.model.ImageFormat;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.util.Arrays;

/**
 * Converts a string value into an {@link ImageFormat}.
 * <p>
 * The conversion is case-insensitive, and an exception is thrown if the value does not match any supported format.
 */
public class ImageFormatConverter implements IStringConverter<ImageFormat> {

    /**
     * Converts the given string to an {@link ImageFormat}.
     *
     * @throws ParameterException if the value is invalid
     */
    @Override
    public ImageFormat convert(String value) throws ParameterException {
        try {
            return ImageFormat.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParameterException(
                String.format("Invalid value '%s' for ImageFormat. Supported values are: %s",
                    value, Arrays.toString(ImageFormat.values())), e);
        }
    }
}
