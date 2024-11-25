package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Pixel;
import static java.lang.Math.max;

/**
 * Applies logarithmic and gamma correction to a {@link FractalImage}.
 * <p>
 * The logarithmic correction adjusts the pixel intensity on a logarithmic scale,
 * while the gamma correction balances the overall brightness of the image.
 * </p>
 */
public class LogGammaCorrectionProcessor implements ImageProcessor {
    private static final int MAXIMUM_COLOR_VALUE = 255;
    private final double gamma;

    public LogGammaCorrectionProcessor(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double maximumNormal = 0;
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                image.setPixel(x, y, applyLogCorrection(pixel));
                maximumNormal = max(maximumNormal, image.getPixel(x, y).normal());
            }
        }

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.getPixel(x, y);
                image.setPixel(x, y, applyGammaCorrection(pixel, maximumNormal));
            }
        }
    }

    private Pixel applyLogCorrection(Pixel pixel) {
        return new Pixel(pixel.r(), pixel.g(), pixel.b(), Math.log(pixel.normal()));
    }

    private Pixel applyGammaCorrection(Pixel pixel, double maximumNormal) {
        double normal = pixel.normal() / maximumNormal;
        int r = correctChannel(pixel.r(), normal);
        int g = correctChannel(pixel.g(), normal);
        int b = correctChannel(pixel.b(), normal);
        return new Pixel(r, g, b, pixel.normal());
    }

    private int correctChannel(int value, double normal) {
        double corrected = value * Math.pow(normal, 1.0 / gamma);
        return (int) Math.min(MAXIMUM_COLOR_VALUE, corrected);
    }
}
