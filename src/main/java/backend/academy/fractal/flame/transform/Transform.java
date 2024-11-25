package backend.academy.fractal.flame.transform;

import backend.academy.fractal.flame.model.Point;
import java.awt.Color;

/**
 * Represents a transformation that can be applied to a point in a 2D space.
 * Each transformation also has an associated color used for rendering.
 */
public interface Transform {
    Point apply(Point point);

    Color getColor();
}
