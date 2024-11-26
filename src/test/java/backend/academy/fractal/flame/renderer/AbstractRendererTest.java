package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.Transform;
import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AbstractRendererTest {

    private static class TestRenderer extends AbstractRenderer {
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

    private final TestRenderer testRenderer = new TestRenderer();

    @Test
    @DisplayName("Renderer should process samples")
    void shouldProcessSamples() {
        FractalImage mockCanvas = mock(FractalImage.class);
        Rectangle mockWorld = mock(Rectangle.class);
        Transform mockTransform = mock(Transform.class);

        when(mockWorld.createRandomPoint()).thenReturn(new Point(0, 0));
        when(mockWorld.cropToScreen(any(Point.class), anyDouble(), anyDouble()))
            .thenReturn(new Point(0, 0));
        when(mockTransform.apply(any(Point.class))).thenReturn(new Point(0, 0));
        when(mockTransform.getColor()).thenReturn(Color.RED);

        List<Transform> transforms = List.of(mockTransform);
        int samples = 10;
        int iterationsPerSample = 20;
        int symmetry = 2;

        // Act
        testRenderer.doRender(mockCanvas, mockWorld, transforms, samples, iterationsPerSample, symmetry);

        // Assert
        verify(mockWorld, times(samples)).createRandomPoint();
        verify(mockTransform, atLeast(samples * iterationsPerSample)).apply(any(Point.class));
        verify(mockTransform, atLeast(samples * iterationsPerSample * symmetry)).getColor();
    }

}
