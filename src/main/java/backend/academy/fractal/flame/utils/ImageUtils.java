package backend.academy.fractal.flame.utils;

import backend.academy.fractal.flame.exception.ImageUtilsException;
import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.ImageFormat;
import backend.academy.fractal.flame.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 * Utility class for image-related operations such as saving fractal images.
 */
public final class ImageUtils {
    private ImageUtils() {
    }

    /**
     * Saves the given fractal image to a file.
     *
     * @param fractalImage the fractal image to save
     * @param path         the path where the image will be saved
     * @param format       the format in which the image should be saved (PNG, JPEG, BMP)
     * @throws ImageUtilsException if an error occurs while saving the image
     */
    public static void saveFractalImage(FractalImage fractalImage, Path path, ImageFormat format) {
        int width = fractalImage.width();
        int height = fractalImage.height();
        Pixel[][] grid = fractalImage.grid();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = grid[y][x];
                Color color = new Color(pixel.r(), pixel.g(), pixel.b());
                image.setRGB(x, y, color.getRGB());
            }
        }

        try {
            ImageIO.write(image, format.name(), path.toFile());
        } catch (IOException e) {
            throw new ImageUtilsException(
                "Failed to write image to file: %s".formatted(path.toAbsolutePath()), e);
        }
    }
}
