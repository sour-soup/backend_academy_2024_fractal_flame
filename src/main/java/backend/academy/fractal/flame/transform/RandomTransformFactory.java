package backend.academy.fractal.flame.transform;

import backend.academy.fractal.flame.model.AffineCoefficients;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.awt.Color;
import static backend.academy.fractal.flame.utils.ThreadRandomUtils.getRandomDouble;
import static backend.academy.fractal.flame.utils.ThreadRandomUtils.getRandomFloat;

/**
 * A factory for creating random affine transformations combined with a specified variation.
 * The transformations generated are guaranteed to be compressive, ensuring fractal convergence.
 */
public final class RandomTransformFactory {
    private RandomTransformFactory() {
    }

    /**
     * Creates a random transformation with a specified variation.
     * The generated transformation includes randomly generated compressive
     * affine coefficients and a random color.
     */
    public static Transform create(Variation variation) {
        AffineCoefficients coefficients;
        do {
            coefficients = generateRandomCoefficients();
        } while (!coefficients.isCompressive());

        Color color = generateRandomColor();
        return new VariatedAffineTransform(coefficients, color, variation);
    }

    private static AffineCoefficients generateRandomCoefficients() {
        return new AffineCoefficients(
            randomCoefficient(),
            randomCoefficient(),
            randomCoefficient(),
            randomCoefficient(),
            randomCoefficient(),
            randomCoefficient()
        );
    }

    private static double randomCoefficient() {
        return getRandomDouble(-1, 1);
    }

    /**
     * Generates a random color using the HSB (hue-saturation-brightness) color model.
     * The color will have full saturation and brightness with a random hue.
     */
    private static Color generateRandomColor() {
        float hue = getRandomFloat();
        return Color.getHSBColor(hue, 1f, 1f);
    }
}
