package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.Transform;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A renderer that processes samples in parallel to utilize multiple CPU cores.
 * This implementation generates a fractal image by applying transformations and symmetries
 * on separate threads to improve performance.
 */
public class ParallelRenderer extends AbstractRenderer {

    /**
     * Renders a fractal image on the canvas by processing each sample in parallel.
     */
    @Override
    protected void doRender(
        FractalImage canvas,
        Rectangle world,
        List<Transform> transforms,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        try (ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors)) {
            for (int i = 0; i < samples; i++) {
                executorService.execute(() -> {
                    processSample(canvas, world, transforms, iterationsPerSample, symmetry);
                });
            }
        }
    }
}
