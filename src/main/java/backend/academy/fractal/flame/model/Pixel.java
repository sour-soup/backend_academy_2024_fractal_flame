package backend.academy.fractal.flame.model;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a single pixel in the fractal image with RGB color components and a density-normalization factor.
 */
@Getter
@AllArgsConstructor
public class Pixel {
    private int r;
    private int g;
    private int b;
    private double normal;

    /**
     * Updates the pixel's color by blending the current color with the provided color.
     * Also increments the density-normalization factor.
     */
    public synchronized void update(Color color) {
        this.r = blendChannel(this.r, color.getRed());
        this.g = blendChannel(this.g, color.getGreen());
        this.b = blendChannel(this.b, color.getBlue());
        this.normal += 1;
    }

    private int blendChannel(int original, int newValue) {
        return (original + newValue) / 2;
    }
}
