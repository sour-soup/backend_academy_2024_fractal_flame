package backend.academy.fractal.flame.cli.converters;

import backend.academy.fractal.flame.model.Rectangle;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * Converts a string value into a {@link Rectangle}.
 * <p>
 * The expected format is "x,y,width,height", where all components are valid doubles.
 */
public class RectangleConverter implements IStringConverter<Rectangle> {

    /**
     * Converts the given string to a {@link Rectangle}.
     *
     * @throws ParameterException if the value is invalid or in an incorrect format
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Rectangle convert(String value) {
        if (value == null || value.isBlank()) {
            throw new ParameterException("Rectangle value cannot be null or empty. Expected format: x,y,width,height.");
        }

        String[] parts = value.split(",");
        if (parts.length != 4) {
            throw new ParameterException(
                String.format("Invalid Rectangle format '%s'. Expected format: x,y,width,height.", value)
            );
        }

        try {
            double x = Double.parseDouble(parts[0].trim());
            double y = Double.parseDouble(parts[1].trim());
            double width = Double.parseDouble(parts[2].trim());
            double height = Double.parseDouble(parts[3].trim());

            if (width <= 0 || height <= 0) {
                throw new ParameterException(
                    String.format("Invalid Rectangle dimensions '%s'. Width and height must be positive.", value)
                );
            }

            return new Rectangle(x, y, width, height);
        } catch (NumberFormatException e) {
            throw new ParameterException(
                String.format("Invalid Rectangle values '%s'. All components must be valid numbers.", value), e);
        }
    }
}
