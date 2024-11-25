package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.Transform;
import java.util.List;

/**
 * A renderer that processes each sample sequentially in a consistent manner.
 * This implementation generates a fractal image by applying the transformations and symmetries
 * in a straightforward loop for each sample.
 */
public class ConsistentRenderer extends AbstractRenderer {

    /**
     * Renders a fractal image on the canvas by sequentially processing each sample.
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
        for (int sample = 0; sample < samples; sample++) {
            processSample(canvas, world, transforms, iterationsPerSample, symmetry);
        }
    }
}
