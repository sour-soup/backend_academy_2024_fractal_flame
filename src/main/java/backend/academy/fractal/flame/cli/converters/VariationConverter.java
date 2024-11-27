package backend.academy.fractal.flame.cli.converters;

import backend.academy.fractal.flame.model.VariationType;
import backend.academy.fractal.flame.transform.variation.Variation;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.util.Arrays;

/**
 * Converts a string value into a {@link Variation}.
 * <p>
 * The conversion is based on {@link VariationType}, and an exception is thrown for unsupported values.
 */
public class VariationConverter implements IStringConverter<Variation> {

    /**
     * Converts the given string to a {@link Variation}.
     *
     * @throws ParameterException if the value is invalid
     */
    @Override
    public Variation convert(String value) {
        try {
            return VariationType.valueOf(value.toUpperCase()).create();
        } catch (IllegalArgumentException e) {
            throw new ParameterException(
                String.format("Invalid value '%s' for Variation. Supported values are: %s",
                    value, Arrays.toString(VariationType.values())), e);
        }
    }
}
