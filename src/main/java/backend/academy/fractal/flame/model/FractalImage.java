package backend.academy.fractal.flame.model;

/**
 * Represents a canvas for rendering fractal images.
 * Each pixel is represented by a {@link Pixel} object, allowing for detailed color and alpha channel manipulation.
 * The canvas is defined by a 2D grid of {@link Pixel}s with specified dimensions.
 */
public record FractalImage(Pixel[][] grid, int width, int height) {

    /**
     * Creates a blank {@code FractalImage} canvas with the specified dimensions.
     * All pixels in the canvas are initialized with black color (R:0, G:0, B:0) and alpha 0.
     *
     * @param width  the width of the canvas, must be positive.
     * @param height the height of the canvas, must be positive.
     * @return a new {@code FractalImage} with the specified dimensions.
     * @throws IllegalArgumentException if {@code width} or {@code height} is not positive.
     */
    public static FractalImage createCanvas(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive integers.");
        }

        Pixel[][] grid = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Pixel(0, 0, 0, 0);
            }
        }
        return new FractalImage(grid, width, height);
    }

    public Pixel getPixel(int x, int y) {
        return grid[x][y];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        grid[x][y] = pixel;
    }
}
