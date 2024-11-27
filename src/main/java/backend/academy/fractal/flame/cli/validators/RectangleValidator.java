package backend.academy.fractal.flame.cli.validators;

import backend.academy.fractal.flame.model.Rectangle;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

/**
 * Validates that a {@link Rectangle} has positive width and height.
 */
public class RectangleValidator implements IValueValidator<Rectangle> {

    /**
     * Validates the given {@link Rectangle}.
     *
     * @throws ParameterException if the rectangle has non-positive width or height
     */
    @Override
    public void validate(String name, Rectangle rectangle) throws ParameterException {
        if (rectangle.width() <= 0 || rectangle.height() <= 0) {
            throw new ParameterException(
                String.format("Parameter %s must have positive width and height. Found: %s", name, rectangle)
            );
        }
    }
}
