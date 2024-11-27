package backend.academy.fractal.flame.cli.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

/**
 * Validates that a given integer value is positive.
 */
public class PositiveIntegerValidator implements IValueValidator<Integer> {

    /**
     * Validates the given integer value.
     *
     * @throws ParameterException if the value is not positive
     */
    @Override
    public void validate(String name, Integer value) throws ParameterException {
        if (value <= 0) {
            throw new ParameterException(
                String.format("Parameter %s must be a positive integer. Found: %d", name, value));
        }
    }
}
