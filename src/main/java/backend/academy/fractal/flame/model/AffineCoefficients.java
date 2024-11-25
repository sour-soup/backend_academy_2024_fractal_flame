package backend.academy.fractal.flame.model;

/**
 * Represents the coefficients of an affine transformation.
 */
public record AffineCoefficients(double a, double b, double c, double d, double e, double f) {
    /**
     * Checks if the affine transformation defined by these coefficients is compressive.
     *
     * @return {@code true} if the transformation is compressive, {@code false} otherwise.
     */
    public boolean isCompressive() {
        return (a * a + c * c) < 1 && (b * b + d * d) < 1
               && (a * a + b * b + c * c + d * d) - (a * d - b * c) * (a * d - b * c) < 1;
    }
}
