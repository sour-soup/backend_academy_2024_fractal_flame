package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.RandomTransformFactory;
import backend.academy.fractal.flame.transform.Transform;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.awt.Color;
import java.util.List;
import static backend.academy.fractal.flame.utils.ThreadRandomUtils.getRandomElement;

/**
 * Abstract class for rendering fractals with different variations and transformations.
 * The rendering process involves applying a series of transformations and symmetries
 * to a set of points within a rectangular world space.
 */
public abstract class AbstractRenderer implements Renderer {
    // The number of normalization steps to skip before rendering the final points.
    protected static final int NORMALIZATION_STEPS = 20;

    /**
     * Renders a fractal image on the given canvas with a set of variations.
     *
     * @param canvas The canvas to render the fractal on.
     * @param world The rectangular world space within which the fractal is generated.
     * @param variations The list of variations to apply during the fractal generation.
     * @param samples The number of random samples to process.
     * @param iterationsPerSample The number of iterations to perform for each sample.
     * @param symmetry The number of symmetrical transformations to apply.
     */
    @Override
    public final void render(
        FractalImage canvas,
        Rectangle world,
        List<Variation> variations,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        List<Transform> transforms = variations.stream()
            .map(RandomTransformFactory::create)
            .toList();

        doRender(canvas, world, transforms, samples, iterationsPerSample, symmetry);
    }

    /**
     * Abstract method to be implemented by subclasses for the actual rendering logic.
     * This method is responsible for iterating over the given transformations and rendering
     * the fractal based on them.
     */
    protected abstract void doRender(
        FractalImage canvas,
        Rectangle world,
        List<Transform> transforms,
        int samples,
        int iterationsPerSample,
        int symmetry
    );

    protected void processSample(
        FractalImage canvas,
        Rectangle world,
        List<Transform> transforms,
        int iterationsPerSample,
        int symmetry
    ) {
        Point point = world.createRandomPoint();

        for (int iteration = -NORMALIZATION_STEPS; iteration < iterationsPerSample; iteration++) {
            Transform transform = getRandomElement(transforms);
            point = transform.apply(point);

            if (iteration < 0) {
                continue;
            }

            for (int i = 0; i < symmetry; i++) {
                Point symmetricPoint = applySymmetry(point, i, symmetry);
                processHit(canvas, world, symmetricPoint, transform.getColor());
            }
        }
    }

    private void processHit(
        FractalImage canvas,
        Rectangle world,
        Point point,
        Color color
    ) {
        Point screenPoint = world.cropToScreen(point, canvas.width(), canvas.height());
        if (isValidScreenPoint(screenPoint, canvas.width(), canvas.height())) {
            int x = (int) screenPoint.x();
            int y = (int) screenPoint.y();
            canvas.grid()[x][y].update(color);
        }
    }

    private Point applySymmetry(Point point, int symmetryIndex, int totalSymmetry) {
        double angle = 2 * Math.PI * symmetryIndex / totalSymmetry;
        double x = Math.cos(angle) * point.x() - Math.sin(angle) * point.y();
        double y = Math.sin(angle) * point.x() + Math.cos(angle) * point.y();
        return new Point(x, y);
    }

    private boolean isValidScreenPoint(Point point, int width, int height) {
        int x = (int) point.x();
        int y = (int) point.y();
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
