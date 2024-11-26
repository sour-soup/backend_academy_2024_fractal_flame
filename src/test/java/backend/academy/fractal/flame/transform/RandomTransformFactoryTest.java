package backend.academy.fractal.flame.transform;

import backend.academy.fractal.flame.model.AffineCoefficients;
import backend.academy.fractal.flame.transform.variation.Variation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

class RandomTransformFactoryTest {

    @Test
    @DisplayName("create Should return a transform with valid coefficients and random color")
    void create_ShouldReturnValidTransform() {
        // Arrange
        Variation mockVariation = mock(Variation.class);

        // Act
        Transform transform = RandomTransformFactory.create(mockVariation);

        // Assert
        assertThat(transform).isInstanceOf(VariatedAffineTransform.class);

        VariatedAffineTransform variatedAffineTransform = (VariatedAffineTransform) transform;
        AffineCoefficients coefficients = variatedAffineTransform.coefficients();

        assertThat(coefficients).isNotNull();
        assertThat(coefficients.isCompressive()).isTrue();

        assertThat(variatedAffineTransform.getColor()).isNotNull();
    }
}
