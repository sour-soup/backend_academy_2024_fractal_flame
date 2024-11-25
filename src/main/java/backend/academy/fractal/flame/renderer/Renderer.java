package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.util.List;

/**
 * Interface representing a renderer for generating fractal images.
 * The rendering process involves applying a series of transformations and symmetries
 * to a set of points within a rectangular world space.
 */
public interface Renderer {

    /**
     * Renders a fractal image on the given canvas with a set of variations.
     *
     * @param canvas              The canvas to render the fractal on.
     * @param world               The rectangular world space within which the fractal is generated.
     * @param variations          The list of variations to apply during the fractal generation.
     * @param samples             The number of random samples to process.
     * @param iterationsPerSample The number of iterations to perform for each sample.
     * @param symmetry            The number of symmetrical transformations to apply.
     */
    void render(
        FractalImage canvas,
        Rectangle world,
        List<Variation> variations,
        int samples,
        int iterationsPerSample,
        int symmetry
    );
}
