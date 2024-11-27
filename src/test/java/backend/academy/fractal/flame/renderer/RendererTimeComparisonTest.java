package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.variation.LinearVariation;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Log4j2
public class RendererTimeComparisonTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    @Disabled("Performance tests are disabled by default")
    @DisplayName("Measure performance of renderer")
    public void measurePerformanceOfRenderers(
        String rendererName,
        Renderer renderer,
        int width,
        int height,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        Rectangle world = new Rectangle(-2, -2, 4, 4);

        List<Variation> variations = List.of(
            new LinearVariation(),
            new LinearVariation(),
            new LinearVariation(),
            new LinearVariation(),
            new LinearVariation()
        );

        FractalImage image = FractalImage.createCanvas(width, height);

        long startTime = System.currentTimeMillis();
        renderer.render(image, world, variations, samples, iterationsPerSample, symmetry);
        long endTime = System.currentTimeMillis();

        log.info("{}\t (Image:{} x {}, Samples: {}, Iterations: {}, Symmetry: {}) execution time: {}ms",
            rendererName, width, height, samples, iterationsPerSample, symmetry, endTime - startTime);
    }

    private static Stream<Object[]> provideParameters() {
        return Stream.of(
            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 800, 800, 1000, 500, 2},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 800, 800, 1000, 500, 2},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1000, 1000, 2000, 1000, 3},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1000, 1000, 2000, 1000, 3},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1000, 1000, 2000, 10000, 1},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1000, 1000, 2000, 10000, 1},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1000, 1000, 10000, 10000, 1},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1000, 1000, 10000, 10000, 1},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1500, 1500, 10000, 10000, 1},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1500, 1500, 10000, 10000, 1},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1000, 1000, 100000, 100, 1},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1000, 1000, 100000, 100, 1},

            new Object[] {"ConsistentRenderer", new ConsistentRenderer(), 1000, 1000, 1000000, 100, 1},
            new Object[] {"ParallelRenderer", new ParallelRenderer(), 1000, 1000, 1000000, 100, 1}

        );
    }
}
