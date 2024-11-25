package backend.academy.fractal.flame.transform;

import backend.academy.fractal.flame.model.AffineCoefficients;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.awt.Color;

/**
 * Represents an affine transformation combined with a variation function
 * for generating fractal-like distortions. The transformation also holds
 * an associated color for rendering.
 */
public record VariatedAffineTransform(
    AffineCoefficients coefficients,
    Color color,
    Variation variation
) implements Transform {

    @Override
    public Point apply(Point point) {
        double x = coefficients.a() * point.x() + coefficients.b() * point.y() + coefficients.e();
        double y = coefficients.c() * point.x() + coefficients.d() * point.y() + coefficients.f();
        return variation.apply(new Point(x, y));
    }

    @Override
    public Color getColor() {
        return color;
    }
}
