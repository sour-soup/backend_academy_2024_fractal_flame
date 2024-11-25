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

    /**
     * Crops a given point to fit within the screen coordinates.
     * The point is scaled relative to the rectangle's dimensions and mapped to the screen's width and height.
     *
     * @param point        The point to be cropped.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     * @return A new point with coordinates adjusted to the screen size.
     */
    public Point cropToScreen(Point point, double screenWidth, double screenHeight) {
        double screenX = (point.x() - x) / this.width * screenWidth;
        double screenY = (point.y() - y) / this.height * screenHeight;
        return new Point(screenX, screenY);
    }
}
