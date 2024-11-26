package backend.academy.fractal.flame.transform;

import backend.academy.fractal.flame.model.AffineCoefficients;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.awt.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class VariatedAffineTransformTest {

    @Test
    @DisplayName("apply Should used mocked coefficients and variation")
    void apply_ShouldUsedMockedCoefficientsAndVariation() {
        // Arrange
        AffineCoefficients coefficients = mock(AffineCoefficients.class);
        Variation variation = mock(Variation.class);
        when(coefficients.a()).thenReturn(1.0);
        when(coefficients.b()).thenReturn(1.0);
        when(coefficients.c()).thenReturn(1.0);
        when(coefficients.d()).thenReturn(1.0);
        when(coefficients.e()).thenReturn(1.0);
        when(coefficients.f()).thenReturn(1.0);

        Point inputPoint = new Point(1.0, 1.0);
        Point affinePoint = new Point(3.0, 3);
        Point expectedPoint = new Point(4.0, 4.0);
        when(variation.apply(affinePoint)).thenReturn(expectedPoint);

        VariatedAffineTransform transform = new VariatedAffineTransform(coefficients, Color.BLACK, variation);

        // Act
        Point actualPoint = transform.apply(inputPoint);

        // Assert
        verify(coefficients).a();
        verify(coefficients).b();
        verify(coefficients).c();
        verify(coefficients).d();
        verify(coefficients).e();
        verify(coefficients).f();
        verify(variation).apply(affinePoint);
        assertThat(actualPoint).isEqualTo(expectedPoint);
    }

    @Test
    @DisplayName("getColor Should return correct color")
    void getColor_ShouldReturnCorrectColor() {
        // Arrange
        AffineCoefficients coefficients = mock(AffineCoefficients.class);
        Color expectedColor = Color.RED;
        Variation variation = mock(Variation.class);

        VariatedAffineTransform transform = new VariatedAffineTransform(coefficients, expectedColor, variation);

        // Act
        Color actualColor = transform.getColor();

        // Assert
        assertThat(actualColor).isEqualTo(expectedColor);
        verifyNoInteractions(coefficients, variation);
    }
}
