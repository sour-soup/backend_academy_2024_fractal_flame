package backend.academy.fractal.flame.cli.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

/**
 * Validates that a given double value is positive.
 */
public class PositiveDoubleValidator implements IValueValidator<Double> {

    /**
     * Validates the given double value.
     *
     * @throws ParameterException if the value is not positive
     */
    @Override
    public void validate(String name, Double value) throws ParameterException {
        if (value <= 0) {
            throw new ParameterException(
                String.format("Parameter %s must be a positive double. Found: %.2f", name, value));
        }
    }
}
