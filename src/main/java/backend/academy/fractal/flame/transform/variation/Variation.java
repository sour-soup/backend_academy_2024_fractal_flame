package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;
import java.util.function.Function;

/**
 * Represents a variation function applied to a {@link Point}.
 * A variation is a type of transformation used in fractal generation,
 * often producing non-linear distortions or effects.
 */
public interface Variation extends Function<Point, Point> {
}
