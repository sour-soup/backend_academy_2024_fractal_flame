package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;

/**
 * Functional interface for processing a {@link FractalImage}.
 * Implementations define specific image processing operations.
 */
@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
