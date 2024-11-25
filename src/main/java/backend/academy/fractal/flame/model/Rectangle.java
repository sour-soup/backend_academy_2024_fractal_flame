package backend.academy.fractal.flame.model;

import static backend.academy.fractal.flame.utils.ThreadRandomUtils.getRandomDouble;

/**
 * Represents a rectangular area in a 2D coordinate system.
 * Used for defining the boundaries of the fractal's world space.
 */
public record Rectangle(double x, double y, double width, double height) {
    /**
     * Generates a random point within the rectangle.
     * The x-coordinate will be between {@code x} and {@code x + width}.
     * The y-coordinate will be between {@code y} and {@code y + height}.
     */
    public Point createRandomPoint() {
        return new Point(
            getRandomDouble(x, x + width),
            getRandomDouble(y, y + height)
        );
    }
}
